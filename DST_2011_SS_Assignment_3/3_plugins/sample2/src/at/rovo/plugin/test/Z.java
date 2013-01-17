package at.rovo.plugin.test;

import dst3.depinj.Component;
import dst3.depinj.ComponentId;
import dst3.depinj.Inject;

@Component
public class Z
{
	@ComponentId
	private Long id;
	@Inject
	private Singleton singleton;
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Long getId()
	{
		return this.id;
	}
	
	public void setSingleton(Singleton singleton)
	{
		this.singleton = singleton;
	}
	
	public Singleton getSingleton()
	{
		return this.singleton;
	}
	
	public void output()
	{
		System.out.print("Z --> "); this.singleton.output();
		System.out.println("Z: "+id);
	}
}
