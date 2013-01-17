package at.rovo.plugin.test;

import dst3.depinj.Component;
import dst3.depinj.ComponentId;
import dst3.depinj.ScopeType;

@Component(scope=ScopeType.SINGLETON)
public class Singleton
{
	@ComponentId
	private Long id;
	
	public Long getId()
	{
		return this.id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public void output()
	{
		System.out.println("Singleton: "+id);
	}
}
