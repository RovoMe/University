package dst1.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Admin extends Person
{
	@Transient
	private static final long serialVersionUID = 2556743888441813055L;
	private List<Cluster> clusters;
	
	public Admin()
	{
		
	}
	
	public Admin(String firstName, String lastName, Address address)
	{
		super(firstName, lastName, address);
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="administrator")
	public List<Cluster> getClusters()
	{
		return this.clusters;
	}
	
	public void setClusters(List<Cluster> clusters)
	{
		this.clusters = clusters;
	}
}
