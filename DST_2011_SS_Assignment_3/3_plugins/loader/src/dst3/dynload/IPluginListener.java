package dst3.dynload;

public interface IPluginListener 
{
	public void pluginLoaded(String pluginName);
	public void pluginRemoved(String pluginName);
	public void exception(Exception e);
}
