package dst3.ejb.entity;

import java.util.List;
import javax.persistence.*;

@Entity
public class Admin extends Person
{
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="admin")
	private List<Cluster> clusters;
	
	public Admin()
	{
		
	}
	
	public Admin(String firstName, String lastName, Address address)
	{
		super(firstName, lastName, address);
	}
	
	public List<Cluster> getClusters()
	{
		return this.clusters;
	}
	
	public void setClusters(List<Cluster> clusters)
	{
		this.clusters = clusters;
	}
}
