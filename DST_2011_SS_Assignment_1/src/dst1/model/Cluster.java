package dst1.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Cluster implements Serializable 
{
	@Transient
	private static final long serialVersionUID = -5492932461098209835L;
	
	private long id;
	private String name;
	private Date lastService;
	private Date nextService;
	private Grid grid;
	private List<Computer> computers;
	private Admin admin;
	private List<Cluster> clusters;
	
	public Cluster()
	{
		
	}
	
	public Cluster(String name, Admin admin, Grid grid, List<Computer> computers)
	{
		this.setName(name);
		this.setAdministrator(admin);
		this.setGrid(grid);
		this.setComputers(computers);
	}
	
	public Cluster(String name, Admin admin, Grid grid, List<Computer> computers, List<Cluster> clusters)
	{
		this.setName(name);
		this.setAdministrator(admin);
		this.setGrid(grid);
		this.setComputers(computers);
		this.setClusters(clusters);
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
	
	public Date getDateOfLastService()
	{
		return this.lastService;
	}
	
	public void setDateOfLastService(Date lastService)
	{
		this.lastService = lastService;
	}
	
	public Date getDateOfNextService()
	{
		return this.nextService;
	}
	
	public void setDateOfNextService(Date nextService)
	{
		this.nextService = nextService;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="GRID_ID")
	public Grid getGrid()
	{
		return this.grid;
	}

	public void setGrid(Grid grid)
	{
		this.grid = grid;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="CLUSTER_ID")
	public List<Computer> getComputers()
	{
		return this.computers;
	}
	
	public void setComputers(List<Computer> computers)
	{
		this.computers = computers;
	}

	@ManyToOne(optional=false)
	@JoinColumn(name="ADMIN_ID")
	public Admin getAdministrator()
	{
		return admin;
	}
	
	public void setAdministrator(Admin admin)
	{
		this.admin = admin;
	}
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public List<Cluster> getClusters()
	{
		return clusters;
	}
	
	public void setClusters(List<Cluster> clusters)
	{
		this.clusters = clusters;
	}
}
