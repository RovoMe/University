package dst3.dynload;

public class Task implements Runnable
{
	private IPluginExecutable plugin;
	
	public Task(IPluginExecutable plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public void run()
	{
		System.out.println("[Task::run] Starting execution for "+plugin);
		try
		{
			Thread.sleep(10*1000); // sleep 10 seconds
			this.plugin.execute();
			Thread.sleep(5*1000); // sleep further 5 seconds
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("[Task:run] finished executing for "+plugin);
	}
}
