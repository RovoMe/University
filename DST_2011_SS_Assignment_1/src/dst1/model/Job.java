package dst1.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Job implements Serializable
{
	@Transient
	private static final long serialVersionUID = 8098896330773465381L;
	private long id;
	private	int numCPUs;
	private int executionTime;
	private boolean isPaid = false;
	private Environment environment;
	private User user;
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
	
	@Transient
	public int getNumCPUs()
	{
		return this.numCPUs;
	}
	
	public void setNumCPUs(int numCPUs)
	{
		this.numCPUs = numCPUs;
	}

	@Transient
	public int getExecutionTime()
	{
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
	
	@OneToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="ENVIRONMENT_ID")
	public Environment getEnvironment()
	{
		return this.environment;
	}
	
	public void setEnvironment(Environment environment)
	{
		this.environment = environment;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY, optional=false)
	public User getUser()
	{
		return this.user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	@OneToOne(mappedBy="job")
	public Execution getExecution()
	{
		return this.execution;
	}
	
	public void setExecution(Execution execution)
	{
		this.execution = execution;
	}
}
