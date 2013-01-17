package dst2.ejb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long jobId;
	private TaskStatus status;
	private String ratedBy;
	private TaskComplexity complexity;
	
	public long getId()
	{
		return this.id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getJobId()
	{
		return this.jobId;
	}
	
	public void setJobId(long jobId)
	{
		this.jobId = jobId;
	}
	
	public TaskStatus getTaskStatus()
	{
		return this.status;
	}
	
	public void setTaskStatus(TaskStatus status)
	{
		this.status = status;
	}
	
	public String getRatedBy()
	{
		return this.ratedBy;
	}
	
	public void setRatedBy(String ratedBy)
	{
		this.ratedBy = ratedBy;
	}
	
	public TaskComplexity getTaskComplexity()
	{
		return this.complexity;
	}
	
	public void setTaskComplexity(TaskComplexity complexity)
	{
		this.complexity = complexity;
	}
}
