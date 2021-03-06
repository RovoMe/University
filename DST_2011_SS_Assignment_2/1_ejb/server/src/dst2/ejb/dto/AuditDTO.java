package dst2.ejb.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AuditDTO implements Serializable
{
	private static final long serialVersionUID = 7121639568793021664L;
	private long id;
	private String method;
	private List<ParameterDTO> parameters;
	private Date time;
	private String failure;
	private String value;
	
	public AuditDTO()
	{
		
	}
	
	public long getId()
	{
		return this.id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getMethod()
	{
		return this.method;
	}
	
	public void setMethod(String method)
	{
		this.method = method;
	}
	
	public List<ParameterDTO> getParameters()
	{
		return this.parameters;
	}
	
	public void setParameters(List<ParameterDTO> parameters)
	{
		this.parameters = parameters;
	}
	
	public Date getTime()
	{
		return this.time;
	}
	
	public void setTime(Date time)
	{
		this.time = time;
	}
	
	public String getFailure()
	{
		return this.failure;
	}
	
	public void setFailure(String failure)
	{
		this.failure = failure;
	}
	
	public String getValue()
	{
		return this.value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
}
