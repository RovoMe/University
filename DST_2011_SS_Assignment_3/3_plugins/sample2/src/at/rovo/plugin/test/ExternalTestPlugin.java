package at.rovo.plugin.test;

import dst3.dynload.IPluginExecutable;


public class ExternalTestPlugin implements IPluginExecutable
{
	@Override
	public void execute()
	{
        try 
        {
			Thread.sleep(20*1000);
		} 
        catch (InterruptedException e) 
        {
			e.printStackTrace();
		}
		
		System.out.println("");
		System.out.println("Hello from an external test plug-in!");
		System.out.println();
		System.out.println("This plug-in requires class dst3.dynload.sample.D to be loaded!");
		System.out.println();
		
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		try
		{
			X x = new X();
			x.output();
		}
		catch (Exception ex)
		{
			System.err.println(ex.getLocalizedMessage());
		}
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

	}
}
