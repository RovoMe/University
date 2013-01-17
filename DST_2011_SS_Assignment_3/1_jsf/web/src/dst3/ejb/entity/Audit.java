package dst3.ejb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Audit 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String myClass;
	private String method;
	@ElementCollection
	private List<Parameter> parameters;
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	@Column(columnDefinition="TEXT")
	private String failure;
	@Column(columnDefinition="TEXT")
	private String value;
	
	public Audit()
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
		this.myClass = myClass;
	}
	
	public String getMethod()
	{
		return this.method;
	}
	
	public void setMethod(String method)
	{
		this.method = method;
	}
	
	public List<Parameter> getParameters()
	{
		return this.parameters;
	}
	
	public void setParameters(List<Parameter> parameters)
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
