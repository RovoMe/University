package dst1.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Environment implements Serializable
{
	@Transient
	private static final long serialVersionUID = 8718960985154103149L;
	private long id;
	private String workflow = null;
	private List<String> params = null;
	
	public Environment()
	{
		
	}
	
	public Environment(String workflow, List<String> params)
	{
		this.setWorkflow(workflow);
		this.setParameters(params);
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
	
	public String getWorkflow()
	{
		return this.workflow;
	}
	
	public void setWorkflow(String workflow)
	{
		this.workflow = workflow;
	}
	
	@ElementCollection
	@OrderColumn
	public List<String> getParameters()
	{
		return this.params;
	}
	
	public void setParameters(List<String> params)
	{
		this.params = params;
	}
}
