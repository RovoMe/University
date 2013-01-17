package dst3.ejb.dto;

import java.io.Serializable;
import java.util.Date;

public class JobDTO implements Serializable
{
	private static final long serialVersionUID = -5563109531749302224L;
	private long id;
	private Date startTime;
	private Date endTime;
	private String userName;
	
	public JobDTO()
	{
		
	}
	
	public JobDTO(long id, Date startTime, Date endTime, String userName)
	{
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.userName = userName;
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
		return this.startTime;
	}
	
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}
	
	public Date getEndTime()
	{
		return this.endTime;
	}
	
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
}
