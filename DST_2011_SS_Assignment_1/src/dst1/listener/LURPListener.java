package dst1.listener;

import java.util.Hashtable;
import javax.persistence.*;

public class LURPListener
{
	private static LURPListener instance = new LURPListener();
	
	private long numCreate = 0L;
	private long numLoad = 0L;
	private long numUpdate = 0L;
	private long numDelete = 0L;
	private long totalTime = 0L;
	
	private boolean debug = false;
	
	private Hashtable<Integer, Long> timeTable = new Hashtable<Integer, Long>();
	
	public static LURPListener getInstance(boolean debug)
	{
		instance.debug = debug;
		return instance;
	}
	
	public static LURPListener getInstance()
	{
		return instance;
	}
	
	@PrePersist
	public synchronized void preCreate(Object o)
	{
		if (getInstance().debug)
			System.out.println("PRE-CREATE: "+o.toString());
		getInstance().timeTable.put(o.hashCode(),System.currentTimeMillis() );
	}
	
	@PostPersist
	public synchronized void postCreate(Object o)
	{ 
		getInstance().totalTime += (System.currentTimeMillis() - getInstance().timeTable.get(o.hashCode())); 
		getInstance().numCreate++;
		if (getInstance().debug)
			System.out.println("POST-CREATE: "+o.toString());
	}
	
	@PostLoad
	public synchronized void postLoad(Object o)
	{
		if (getInstance().debug)
			System.out.println("POST-LOAD: "+o.toString());
		getInstance().numLoad++;
	}
	
	@PreUpdate
	public synchronized void preUpdate(Object o)
	{
		if (getInstance().debug)
			System.out.println("PRE-UPDATE: "+o.toString());
	}
	
	@PostUpdate
	public synchronized void postUpdate(Object o)
	{
		if (getInstance().debug)
			System.out.println("POST-UPDATE: "+o.toString());
		getInstance().numUpdate++;
	}
	
	@PreRemove
	public synchronized void preRemove(Object o)
	{
		if (getInstance().debug)
			System.out.println("PRE-REMOVE: "+o.toString());
	}
	
	@PostRemove
	public synchronized void postRemove(Object o)
	{
		getInstance().numDelete++;
		if (getInstance().debug)
			System.out.println("POST-REMOVE: "+o.toString());
	}
	
	public void getStats()
	{
		System.out.println("Load-Operations: "+numLoad);
		System.out.println("Update-Operations: "+numUpdate);
		System.out.println("Remove-Operations: "+numDelete);
		System.out.println();
		System.out.println("Persist operations: "+numCreate);
		System.out.println("Overall time to persist: "+totalTime+" ms");
		System.out.println("Average time to persist: "+((float)totalTime/(float)numCreate)+" ms");
	}
}
