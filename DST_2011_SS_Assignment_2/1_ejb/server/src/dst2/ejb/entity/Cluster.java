package dst2.ejb.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Cluster
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastService;
	@Temporal(TemporalType.TIMESTAMP)
	private Date nextService;
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="GRID_ID")
	private Grid grid;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="CLUSTER_ID")
	private List<Computer> computers;
	@ManyToOne(optional=false)
	@JoinColumn(name="ADMIN_ID")
	private Admin admin;
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
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
	
	public Grid getGrid()
	{
		return this.grid;
	}

	public void setGrid(Grid grid)
	{
		this.grid = grid;
	}
	
	public List<Computer> getComputers()
	{
		return this.computers;
	}
	
	public void setComputers(List<Computer> computers)
	{
		this.computers = computers;
	}

	public Admin getAdministrator()
	{
		return admin;
	}
	
	public void setAdministrator(Admin admin)
	{
		this.admin = admin;
	}
	
	public List<Cluster> getClusters()
	{
		return clusters;
	}
	
	public void setClusters(List<Cluster> clusters)
	{
		this.clusters = clusters;
	}
}
