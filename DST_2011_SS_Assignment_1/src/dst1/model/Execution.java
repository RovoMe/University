package dst1.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Execution implements Serializable
{
	@Transient
	private static final long serialVersionUID = 2157455299130771884L;
	private long id;
	private Date start;
	private Date end;
	private JobStatus status;
	private Job job;
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
	
	@Enumerated(EnumType.STRING)
	public JobStatus getJobStatus()
	{
		return this.status;
	}
	
	public void setJobStatus(JobStatus status)
	{
		this.status = status;
	}
	
	@OneToOne(cascade={CascadeType.ALL}, optional=false)
	@JoinColumn(name="JOB_ID")
	public Job getJob()
	{
		return this.job;
	}
	
	public void setJob(Job job)
	{
		this.job = job;
	}
	
	@ManyToMany
	@JoinTable(name="COMPUTER_EXECUTION", 
		joinColumns=@JoinColumn(name="EXECUTION_ID", referencedColumnName="ID"), 
		inverseJoinColumns=@JoinColumn(name="COMPUTER_ID", referencedColumnName="ID")
	)
	public List<Computer> getComputers()
	{
		return this.computers;
	}
	
	public void setComputers(List<Computer> computers)
	{
		this.computers = computers;
	}
}
