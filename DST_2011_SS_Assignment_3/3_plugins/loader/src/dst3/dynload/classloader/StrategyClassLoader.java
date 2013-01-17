package dst3.dynload.classloader;

import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;

public class StrategyClassLoader extends ClassLoader implements IClassLoaderStrategy
{
	private List<IClassLoaderStrategy> loaders = new ArrayList<IClassLoaderStrategy>();
	
	public StrategyClassLoader()
	{
		this(StrategyClassLoader.class.getClassLoader(), null);
	}
	
	public StrategyClassLoader(IClassLoaderStrategy[] loaders)
	{
		this(StrategyClassLoader.class.getClassLoader(), loaders);
	}
	
	public StrategyClassLoader(ClassLoader loader)
	{
		this(loader, null);
	}
	
	public StrategyClassLoader(ClassLoader parent, IClassLoaderStrategy[] loaders )
	{
		super(parent);
		if (loaders != null)
			for (IClassLoaderStrategy loader : loaders)
				this.loaders.add(loader);
	}
	
	public void addLoader(IClassLoaderStrategy strategy)
	{
		this.loaders.add(strategy);
	}
	
	public List<IClassLoaderStrategy> getLoaders()
	{
		return loaders;
	}
	
	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException
	{
		byte[] classBytes = findClassBytes(className);
		if (classBytes == null)
			throw new ClassNotFoundException();
		return defineClass(className, classBytes, 0, classBytes.length);
	}

	@Override
	public byte[] findClassBytes(String className)
	{
		byte[] classBytes = null;
		for (IClassLoaderStrategy strategy : loaders)
		{
			classBytes = strategy.findClassBytes(className);
			if (classBytes != null)
				return classBytes;
		}
		return classBytes;
	}

	@Override
	public URL findResourceURL(String resourceName)
	{
		URL resource = null;
		for (IClassLoaderStrategy strategy : loaders)
		{
			resource = strategy.findResourceURL(resourceName);
			if (resource != null)
				return resource;
		}
		return resource;
	}

	@Override
	public Enumeration<URL> findResourcesEnum(String resourceName)
	{
		Enumeration<URL> enumerationEnum = null;
		for (IClassLoaderStrategy strategy : loaders)
		{
			enumerationEnum = strategy.findResourcesEnum(resourceName);
			if (enumerationEnum != null)
				return enumerationEnum;
		}
		return enumerationEnum;
	}

	@Override
	public String findLibraryPath(String libraryName)
	{
		String libPath = null;
		for (IClassLoaderStrategy strategy : loaders)
		{
			libPath = strategy.findLibraryPath(libraryName);
			if (libPath != null)
				return libPath;
		}
		return libPath;
	}
}
