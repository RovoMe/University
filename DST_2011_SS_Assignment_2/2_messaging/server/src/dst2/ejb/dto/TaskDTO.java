package dst2.ejb.dto;

import java.io.Serializable;

public class TaskDTO implements Serializable
{
	private static final long serialVersionUID = -2077324677511339268L;

	private long id;
	private long jobId;
	private String status;
	private String ratedBy;
	private String complexity;
	
	public TaskDTO()
	{
		
	}
	
	public TaskDTO(long id, long jobId, String status, String ratedBy, String complexity)
	{
		this.id = id;
		this.jobId = jobId;
		this.status = status;
		this.ratedBy = ratedBy;
		this.complexity = complexity;
	}
	
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
	
	public String getTaskStatus()
	{
		return this.status;
	}
	
	public void setTaskStatus(String status)
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
	
	public String getTaskComplexity()
	{
		return this.complexity;
	}
	
	public void setTaskComplexity(String complexity)
	{
		this.complexity = complexity;
	}
	
	@Override
	public String toString()
	{
		return  "Task {Id: "+id+" | JobId: "+jobId+" | TaskStatus: "+status+
			" | RatedBy: "+ratedBy+" | TaskComplexity: "+complexity+"}";
	}
}