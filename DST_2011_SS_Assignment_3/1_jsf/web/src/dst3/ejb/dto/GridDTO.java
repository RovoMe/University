package dst3.ejb.dto;

import java.io.Serializable;

public class GridDTO implements Serializable
{
	private static final long serialVersionUID = -5591407883456134694L;
	private long id;
	private String name;
	private String location;
	private double costPerCPUMinute;
	private int availableCPUs;
	
	public GridDTO()
	{
		
	}
	
	public GridDTO(long id, String name, String location, double costPerCPUMinute, int availableCPUs)
	{
		this.id = id;
		this.name = name;
		this.location = location;
		this.costPerCPUMinute = costPerCPUMinute;
		this.availableCPUs = availableCPUs;
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
	
	public double getCostPerCPUMinute()
	{
		return this.costPerCPUMinute;
	}
	
	public void setCostPerCPUMinute(double costPerCPUMinute)
	{
		this.costPerCPUMinute = costPerCPUMinute;
	}
	
	public int getAvailableCPUs()
	{
		return this.availableCPUs;
	}
	
	public void setAvailableCPUs(int availableCPUs)
	{
		this.availableCPUs = availableCPUs;
	}
	
	public String toString()
	{
		return "{id: "+id+ " | name: "+name+ " | location: "+location+" | costPerCPUMinute: "+costPerCPUMinute+" | availableCPUs: "+availableCPUs+"}";
	}
}
