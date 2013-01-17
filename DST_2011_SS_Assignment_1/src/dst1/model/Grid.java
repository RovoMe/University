package dst1.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

@Entity
public class Grid implements Serializable
{
	@Transient
	private static final long serialVersionUID = -1175972702104297836L;
	private long id;
	private String name;
	private String location;
	private BigDecimal costPerCPUMinute;
	private List<Cluster> cluster;
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
	
	@Id
	@GeneratedValue
	public long getId()
	{
		return this.id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	@Column(unique=true)
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
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="grid")
	public List<Cluster> getClusters()
	{
		return this.cluster;
	}

	public void setClusters(List<Cluster> clusters)
	{
		this.cluster = clusters;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="grid")
	public List<Membership> getMembers()
	{
		return this.members;
	}
	
	public void setMembers(List<Membership> members)
	{
		this.members = members;
	}
}
