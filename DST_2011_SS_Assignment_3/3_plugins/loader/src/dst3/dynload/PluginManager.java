package dst3.dynload;

import java.io.File;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import dst3.dynload.classloader.PluginLoaderStrategy;
import dst3.dynload.classloader.StrategyClassLoader;
import dst3.dynload.filemonitor.FileAction;
import dst3.dynload.filemonitor.FileMonitor;
import dst3.dynload.filemonitor.IDirectoryChangeListener;
import dst3.depinj.ClassFinder;
import dst3.dynload.classloader.InjectionLoaderStrategy;

public class PluginManager implements IPluginExecutor, IDirectoryChangeListener
{
	private static PluginManager me = null;
	private FileMonitor fileMonitor;
	private Set<IPluginListener> listeners;
	private ThreadPoolExecutor tpe;
	private StrategyClassLoader singletonClassLoader;
	private Map<WeakReference<File>, StrategyClassLoader> plugins;
	private PluginLoaderStrategy pluginStrategy = new PluginLoaderStrategy();
	private InjectionLoaderStrategy injectionStrategy;
	
	private PluginManager()
	{
		this.fileMonitor = new FileMonitor(1000);
		this.fileMonitor.addListener(this);
		this.listeners = new CopyOnWriteArraySet<IPluginListener>();
		this.plugins = new HashMap<WeakReference<File>, StrategyClassLoader>();
		
		this.singletonClassLoader = new StrategyClassLoader(this.getClass().getClassLoader());
		this.injectionStrategy = new InjectionLoaderStrategy(this.pluginStrategy);
		this.singletonClassLoader.addLoader(injectionStrategy);
	}
	
	public synchronized static PluginManager getInstance()
	{
		if (me == null)
			me = new PluginManager();
		return me;
	}
	
	/**
	 * <p>Adds a listener to an internal List to objects who get notified when a
	 * plug-in has been loaded or removed.</p>
	 * @param listener The object who wants to be notified when a plug-in get loaded
	 *                 or removed from the system.
	 */
	public void addPluginListener(IPluginListener listener)
	{
		this.listeners.add(listener);
	}
	
	/**
	 * <p>Removes a listener from the internal list of objects who get notified
	 * when a plug-in has been loaded or removed.</p>
	 * @param listener The object who wants to stop being notified when a plug-in
	 *                 gets loaded or removed from the system.
	 * @return Returns true if the listener could be removed from the list of 
	 *         notified objects, false otherwise.
	 */
	public boolean removePluginListener(IPluginListener listener)
	{
		boolean removed = false;
		if (this.listeners.contains(listener))
			removed = this.listeners.remove(listener);
		return removed;
	}
	
	@Override
	public void monitor(File dir) 
	{
		if (dir == null)
			throw new IllegalArgumentException("Invalid directory supplied: directory is null");
		if (!dir.isDirectory())
		{
			throw new IllegalArgumentException(dir.getAbsolutePath()+" is not a directory");
		}
		this.fileMonitor.addPluginDir(dir);
	}

	@Override
	public void stopMonitoring(File dir) 
	{
		if (dir == null)
			throw new IllegalArgumentException("Invalid directory supplied: directory is null");
		if (!dir.isDirectory())
			throw new IllegalArgumentException(dir.getAbsolutePath()+" is not a directory");
		this.fileMonitor.removeFile(dir);
	}

	@Override
	public void start() 
	{
		this.tpe = new ThreadPoolExecutor(5, 15, 50000L, TimeUnit.MILLISECONDS,
		        new LinkedBlockingQueue<Runnable>());
		this.fileMonitor.start();		
	}

	@Override
	public void stop() 
	{
		if (this.tpe != null)
		{
			this.tpe.shutdown();
			while (!tpe.isTerminated())
			{
				
			}
			System.out.println("[PluginManager:stop] stoped execution");
		}
		if (this.fileMonitor != null)
			this.fileMonitor.stop();
	}

	@Override
	public void fileChanged(File file, FileAction fileAction) 
	{
		switch (fileAction)
		{
			case FILE_CREATED:
			case FILE_MODIFIED:		
				ClassFinder.getInstance().addToClassPath(file.getAbsolutePath());
				List<String> foundFiles = ClassFinder.scanJarFileForClasses(file);
				try
				{
					this.pluginStrategy.setClassPath(file.toURI().toURL());
					this.injectionStrategy.setJarFile(file);
				}
				catch (MalformedURLException e2)
				{
					e2.printStackTrace();
				}
				// Create a new class-loader for this plug-in
				StrategyClassLoader pluginLoader = new StrategyClassLoader(this.singletonClassLoader);
				// load all classes for this plug-in
				for (String className : foundFiles)
					this.loadPlugin(className, pluginLoader);
				// store the class loader of the plug-in so that if the
				// file gets deleted (or the last strong reference 
				// (=created with new...) is removed) the class loader
				// gets garbage collected too.
				// problem here might be, that singletons if there are any,
				// do not get unloaded so there will always be a reference to
				// the jar file and the plug-in class loader never unloaded ...
				WeakReference<File> ref = new WeakReference<File>(file);
				this.plugins.put(ref, pluginLoader);
				
				// now run through all files and look if there is a plug-in to load
				for (String className : foundFiles)
				{
					Class<?> pluginClass;
					try
					{
						pluginClass = pluginLoader.loadClass(className);
						System.out.println("loaded class: "+pluginClass.getName()+" with classloader: "+pluginClass.getClassLoader());
						if (pluginClass != null && IPluginExecutable.class.isAssignableFrom(pluginClass))
						{
							System.out.println("[PluginManager::fileChanged] Found plugin: "+pluginClass.getName());
							// notify listeners
							for (IPluginListener listener : listeners)
								listener.pluginLoaded(pluginClass.getName());
							try
							{
								// start the plug-in
								IPluginExecutable plugin = (IPluginExecutable)pluginClass.newInstance();
								tpe.execute(new Task(plugin));
							}
							catch (InstantiationException e)
							{
								e.printStackTrace();
							}
							catch (IllegalAccessException e)
							{
								e.printStackTrace();
							}
						}
					}
					catch (ClassNotFoundException e1)
					{
						e1.printStackTrace();
					}
				}
				break;
			case FILE_DELETED:
				break;
		}
	}
	
	/**
	 * <p>Loads a plug-in whose .jar or .zip-file got already loaded
	 * into the systems cache.</p>
	 * @param pluginName Full name of the plug-in to load.
	 */
	protected Class<?> loadPlugin(String pluginName, StrategyClassLoader loader)
	{
		try
		{
			// Check if the class to load is a singleton
			// if so, use a single class-loader therefore
			// if not, use a class-loader which is a child of
			// the singleton class-loader so that a lookup
			// for the singleton of every class loaded by
			// a child class-loader will return this singleton
			Class<?> result = null;
			if (this.injectionStrategy.isSingleton(pluginName))
			{
//				System.out.println("[PluginManager.loadPlugin] Using Singleton-ClassLoader for "+pluginName);
				result = this.singletonClassLoader.loadClass(pluginName);
			}
			else
			{
//				System.out.println("[PluginManager.loadPlugin] Using plugin-specific-ClassLoader for "+pluginName);
				loader.addLoader(this.injectionStrategy);
				// URLClassLoader pluginLoader = URLClassLoader.newInstance(new URL[] {jarFile.toURI().toURL()});
				result = loader.loadClass(pluginName);
			}
			
			return result;
		}
		catch (Exception e)
		{
			System.err.println(e.getLocalizedMessage());
		}
		return null;
	}
}
