package dst2.ejb.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Execution
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;
	@Enumerated(EnumType.STRING)
	private JobStatus status;
	@OneToOne(cascade={CascadeType.ALL}, optional=false)
	@JoinColumn(name="JOB_ID")
	private Job job;
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="COMPUTER_EXECUTION", 
		joinColumns=@JoinColumn(name="EXECUTION_ID", referencedColumnName="ID"), 
		inverseJoinColumns=@JoinColumn(name="COMPUTER_ID", referencedColumnName="ID")
	)
	private List<Computer> computers;
	
	public Execution()
	{
		
	}
	
	public Execution(Date start, Date end, JobStatus status, Job job, List<Computer> computers)
	{
		this.setStartTime(start);
		this.setEndTime(end);
		this.setJobStatus(status);
		this.setJob(job);
		this.setComputers(computers);
	}
	
	public long getId()
	{
		return this.id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public Date getStartTime()
	{
		return this.start;
	}
	
	public void setStartTime(Date start)
	{
		this.start = start;
	}
	
	public Date getEndTime()
	{
		return this.end;
	}
	
	public void setEndTime(Date end)
	{
		this.end = end;
	}

	public JobStatus getJobStatus()
	{
		return this.status;
	}
	
	public void setJobStatus(JobStatus status)
	{
		this.status = status;
	}
	
	public Job getJob()
	{
		return this.job;
	}
	
	public void setJob(Job job)
	{
		this.job = job;
	}
	
	public List<Computer> getComputers()
	{
		return this.computers;
	}
	
	public void setComputers(List<Computer> computers)
	{
		this.computers = computers;
	}
}
