package dst3.ejb.entity;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

@Entity
public class Grid
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private String location;
	private BigDecimal costPerCPUMinute;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="grid")
	private List<Cluster> cluster;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="grid")
	private List<Membership> members;
	
	public Grid()
	{
		
	}
	
	public Grid(String name, String locatin, BigDecimal costperCPUMinute)
	{
		this.setName(name);
		this.setLocation(locatin);
		this.setCostPerCPUMinute(costperCPUMinute);
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
	
	public String getLocation()
	{
		return this.location;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public BigDecimal getCostPerCPUMinute()
	{
		return this.costPerCPUMinute;
	}
	
	public void setCostPerCPUMinute(BigDecimal costPerCPUMinute)
	{
		this.costPerCPUMinute = costPerCPUMinute;
	}
	
	public List<Cluster> getClusters()
	{
		return this.cluster;
	}

	public void setClusters(List<Cluster> clusters)
	{
		this.cluster = clusters;
	}
	
	public List<Membership> getMembers()
	{
		return this.members;
	}
	
	public void setMembers(List<Membership> members)
	{
		this.members = members;
	}
}
