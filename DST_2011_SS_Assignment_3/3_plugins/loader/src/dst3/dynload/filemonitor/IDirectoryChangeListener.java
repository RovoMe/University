package dst3.dynload.filemonitor;

import java.io.File;


public interface IDirectoryChangeListener
{
	/**
	 * <p><code>fileChanged()</code> is called when a file 
	 * inside a directory is changed</p>
	 * @param file File which was either modified, deleted or created
	 * @param changeAction Determines what happened with the file. 
	 */
	public void fileChanged(File file, FileAction changeAction);
}
