package dst3.dynload.filemonitor;

import java.util.*;
import java.io.File;

/**
 * Class for monitoring changes in disk files. Usage: 1. Implement the
 * IDirectoryChangeListener interface. 2. Create a FileMonitor instance. 3. Add the
 * file(s)/directory(ies) to listen for. fileChanged() will be called when a
 * monitored file is created, deleted or its modified time changes.
 */
public class FileMonitor
{
	private Timer timer;
	private Map<File, Long> files;
	private Set<IDirectoryChangeListener> listeners;
	private long pollingInterval = 1000;
	private Set<File> pluginDirList;
	
	public FileMonitor(long pollingInterval)
	{
		this.files = new HashMap<File, Long>();
		this.listeners = new HashSet<IDirectoryChangeListener>();
		this.pollingInterval = pollingInterval;
		this.timer = new Timer(true);
		this.pluginDirList = new HashSet<File>();
	}
	
	public FileMonitor(long pollingInterval, File dir)
	{
		this(pollingInterval);
		
		if (dir == null)
			throw new IllegalArgumentException("Invalid directory supplied: directory is null");
		if (!dir.isDirectory())
			throw new IllegalArgumentException(dir.getAbsolutePath()+" is not a directory");
		this.pluginDirList.add(dir);
	}
	
	public void addPluginDir(File dir)
	{
		if (dir == null)
			throw new IllegalArgumentException("Invalid directory supplied: directory is null");
		if (!dir.isDirectory())
			throw new IllegalArgumentException(dir.getAbsolutePath()+" is not a directory");
		
		this.pluginDirList.add(dir);
	}
	
	public void removePluginDir(File dir)
	{
		if (dir == null)
			throw new IllegalArgumentException("Invalid directory supplied: directory is null");
		if (!dir.isDirectory())
			throw new IllegalArgumentException(dir.getAbsolutePath()+" is not a directory");
		
		if (pluginDirList.contains(dir))
			this.pluginDirList.remove(dir);
	}
	
	public Set<File> getPluginDirs()
	{
		return this.pluginDirList;
	}
	
	public void setPollingInterval(long pollingInterval)
	{
		this.pollingInterval = pollingInterval;
	}
	
	public long getPollingInterval()
	{
		return this.pollingInterval;
	}
	
	public void start()
	{
		this.timer.schedule(
				new FileMonitorNotifier(
						this.files, 
						this.listeners, 
						this.pluginDirList), 
				0, 
				this.pollingInterval);
	}

	public void stop()
	{
		timer.cancel();
	}

	public void addFile(File file)
	{
		if (!files.containsKey(file))
		{
			long modifiedTime = file.exists() ? file.lastModified() : -1;
			files.put(file, new Long(modifiedTime));
		}
	}

	public void removeFile(File file)
	{
		files.remove(file);
	}

	public void addListener(IDirectoryChangeListener listener)
	{
		// Don't add if its already there
		for (IDirectoryChangeListener _listener : listeners)
			if (_listener == listener)
				return;
		
		listeners.add(listener);
	}

	public void removeListener(IDirectoryChangeListener listener)
	{
		for (IDirectoryChangeListener listener_ : listeners)
		{
			if (listener_ == listener)
			{
				listeners.remove(listener);
				break;
			}
		}
	}
}