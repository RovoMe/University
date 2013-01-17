package dst3.dynload.filemonitor;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

public class FileMonitorNotifier extends TimerTask
{
	private Map<File, Long> files = null;
	private Collection<IDirectoryChangeListener> listeners = null;
	private Set<File> directories = null;
	
	public FileMonitorNotifier(Map<File, Long> files, Collection<IDirectoryChangeListener> listener, Set<File> directories)
	{
		this.files = files;
		this.listeners = listener;
		this.directories = directories;
	}
	
	public void run()
	{
		// Loop over the registered files and see which have changed.
		// Use a copy of the list in case listener wants to alter the
		// list within its fileChanged method.
		Collection<File> files = new ArrayList<File>(this.files.keySet());

		for (File file : files)
		{
			if (!file.exists())
			{
				notifyListener(file, FileAction.FILE_DELETED);
				this.files.remove(file);
			}
			else
			{
				long lastModifiedTime = this.files.get(file);
				long newModifiedTime = file.exists() ? file.lastModified() : -1;
	
				// Chek if file has changed
				if (newModifiedTime != lastModifiedTime)
				{
					// Register new modified time
					this.files.put(file, new Long(newModifiedTime));
					notifyListener(file, FileAction.FILE_MODIFIED);
				}
			}
		}
		
		// Fetch all files in the specified directories and 
		// store them in a Collection
		Collection<File> curFileList = new ArrayList<File>();
		for (File dir : this.directories)
		{
			File[] curFiles = dir.listFiles(new JarFilter());
			for (File file : curFiles)
				curFileList.add(file);
		}
		// Remove those files that have already been added
		// and therefore are being watched
		curFileList.removeAll(files);
		// curFileList now contains all newly created files
		for (File newFile : curFileList)
		{
			this.files.put(newFile, newFile.lastModified());
			notifyListener(newFile, FileAction.FILE_CREATED);
		}
	}
	
	private void notifyListener(File file, FileAction fileAction)
	{
		for (IDirectoryChangeListener listener : this.listeners)
			listener.fileChanged(file, fileAction);
	}
}