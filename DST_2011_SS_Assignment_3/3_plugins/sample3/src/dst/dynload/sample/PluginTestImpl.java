package dst.dynload.sample;

import java.util.Random;
import dst3.dynload.IPluginExecutable;

public class PluginTestImpl implements IPluginExecutable
{
	private Random random;
	
	public PluginTestImpl()
	{
		this.random = new Random();
	}

	@Override
	public void execute()
	{
		waiting((int)(random.nextFloat()*10));
		System.out.println("Hello from "+this.getClass().getCanonicalName()+"\n"+
				"\tclassLoader: "+this.getClass().getClassLoader()+"\n"+
				"\tthread: "+Thread.currentThread().getId());
	}
	
	 private static void waiting (int n)
	 {
		 long t0, t1;
		 t0 =  System.currentTimeMillis();
		 do
		 {
			 t1 = System.currentTimeMillis();
		 }
		 while (t1 - t0 < n*1000); 
	 }
}
