package at.rovo.plugin.test;

import dst3.depinj.Component;
import dst3.depinj.ComponentId;

@Component
public class Y extends Z
{
	@ComponentId
	private Long id;
	
	public void setId(Long id)
	{
		super.setId(id);
		this.id = id;
	}
	
	public Long getId()
	{
		return this.id;
	}

	public void output()
	{
		System.out.print("Y == ");	super.output();
		System.out.print("Y ==> ");	this.getSingleton().output();
		System.out.println("Y: "+id);
	}
}
