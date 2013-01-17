package dst1.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import dst1.validator.CPUs;

public class Computer implements Serializable
{
	private static final long serialVersionUID = -2904397984773879672L;
	private long id;
	private String name;
	private int cpus;
	private String location;
	private Date creation;
	private Date lastUpdate;
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
	
	@Size(min=5, max=25)
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@CPUs(min=4, max=8)
	public int getCPUs()
	{
		return this.cpus;
	}
	
	public void setCPUs(int cpus)
	{
		this.cpus = cpus;
	}
	
	@Pattern(regexp="[A-Z]{3}-[A-Z]{3}@[0-9]{4}[0-9]*")
	public String getLocation()
	{
		return this.location;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	@Past
	public Date getCreationDate()
	{
		return this.creation;
	}
	
	public void setCreationDate(Date creation)
	{
		this.creation = creation;
	}
	
	@Past
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
}
