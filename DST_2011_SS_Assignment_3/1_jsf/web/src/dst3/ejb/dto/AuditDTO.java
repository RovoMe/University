package dst3.ejb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuditDTO implements Serializable
{
	private static final long serialVersionUID = 7121639568793021664L;
	private long id;
	private String myClass;
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
	
	public String getMyClass()
	{
		return this.myClass;
	}
	
	public void setMyClass(String myClass)
	{
		if (myClass == null)
			myClass = "";
		this.myClass = myClass;
	}
	
	public String getMethod()
	{
		return this.method;
	}
	
	public void setMethod(String method)
	{
		if (method==null)
			method = "";
		this.method = method;
	}
	
	public List<ParameterDTO> getParameters()
	{
		return this.parameters;
	}
	
	public void setParameters(List<ParameterDTO> parameters)
	{
		if (parameters == null)
		{
			parameters = new ArrayList<ParameterDTO>();
			parameters.add(new ParameterDTO());
		}
		this.parameters = parameters;
	}
	
	public Date getTime()
	{
		return this.time;
	}
	
	public void setTime(Date time)
	{
		if (time == null)
			time = new Date();
		this.time = time;
	}
	
	public String getFailure()
	{
		return this.failure;
	}
	
	public void setFailure(String failure)
	{
		if (failure == null)
			failure = "";
		this.failure = failure;
	}
	
	public String getValue()
	{
		return this.value;
	}
	
	public void setValue(String value)
	{
		if (value == null)
			value = "";
		this.value = value;
	}
}
