package dst3.ejb.entity;

import java.util.List;
import javax.persistence.*;

@Entity
public class Job
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Transient
	private	int numCPUs;
	@Transient
	private int executionTime;
	private boolean isPaid = false;
	@Version
    @Column(name="OPTLOCK")
    private int version;
	@OneToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="ENVIRONMENT_ID")
	private Environment environment;
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY, optional=false)
	private User user;
	@OneToOne(mappedBy="job")
	private Execution execution;
	
	public Job()
	{
		
	}
	
	public Job(boolean isPaid, Environment env, User user)
	{
		this.setPaid(isPaid);
		this.setEnvironment(env);
		this.setUser(user);
	}
	
	public long getId()
	{
		return this.id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public int getNumCPUs()
	{
		List<Computer> computers = execution.getComputers();
		for (Computer comp : computers)
			this.numCPUs += comp.getCPUs();
		return this.numCPUs;
	}
	
	public void setNumCPUs(int numCPUs)
	{
		this.numCPUs = numCPUs;
	}

	public int getExecutionTime()
	{
		if (this.execution.getEndTime() != null && 
				this.execution.getStartTime() != null)
		{
			Long execTime = this.execution.getEndTime().getTime()-
				this.execution.getStartTime().getTime();
			this.executionTime = execTime.intValue();
		}
		else
			this.executionTime = 0;
		return this.executionTime;
	}
	
	public void setExecutionTime(int executionTime)
	{
		this.executionTime = executionTime;
	}
	
	public boolean isPaid()
	{
		return this.isPaid;
	}
	
	public void setPaid(boolean isPaid)
	{
		this.isPaid = isPaid;
	}
	
	protected int getVersion() 
	{ 
		return version; 
	}
	
	protected void setVersion(int version) 
	{ 
		this.version = version; 
	}
	
	public Environment getEnvironment()
	{
		return this.environment;
	}
	
	public void setEnvironment(Environment environment)
	{
		this.environment = environment;
	}
	
	public User getUser()
	{
		return this.user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public Execution getExecution()
	{
		return this.execution;
	}
	
	public void setExecution(Execution execution)
	{
		this.execution = execution;
	}
}
