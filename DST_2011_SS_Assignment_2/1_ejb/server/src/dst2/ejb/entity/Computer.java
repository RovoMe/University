package dst2.ejb.entity;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import dst2.ejb.validation.CPUs;
import javax.persistence.Column;
import javax.persistence.Version;

public class Computer
{
	private long id;
	@Size(min=5, max=25)
	private String name;
	@CPUs(min=4, max=8)
	private int cpus;
	@Pattern(regexp="[A-Z]{3}-[A-Z]{3}@[0-9]{4}[0-9]*")
	private String location;
	@Past
	private Date creation;
	@Past
	private Date lastUpdate;
	@Version
    @Column(name="OPTLOCK")
    private int version;
	private Cluster cluster;
	private List<Execution> execution;
	
	public Computer() 
	{
		
	}
	
	public Computer(String name, int cpus, String location)
	{
		this.setName(name);
		this.setCPUs(cpus);
		this.setLocation(location);
	}
	
	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getCPUs()
	{
		return this.cpus;
	}
	
	public void setCPUs(int cpus)
	{
		this.cpus = cpus;
	}
	
	public String getLocation()
	{
		return this.location;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public Date getCreationDate()
	{
		return this.creation;
	}
	
	public void setCreationDate(Date creation)
	{
		this.creation = creation;
	}
	
	public Date getLastUpdate()
	{
		return this.lastUpdate;
	}
	
	public void setLastUpdate(Date lastUpdate)
	{
		this.lastUpdate = lastUpdate;
	}
	
	public Cluster getCluster()
	{
		return this.cluster;
	}
	
	public void setCluster(Cluster cluster)
	{
		this.cluster = cluster;
	}
	
	public List<Execution> getExecution()
	{
		return this.execution;
	}
	
	public void setExecution(List<Execution> execution)
	{
		this.execution = execution;
	}
	
	protected int getVersion() 
	{ 
		return version; 
	}
	
	protected void setVersion(int version) 
	{ 
		this.version = version; 
	}
}
