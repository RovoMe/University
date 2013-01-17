package dst2.ejb.entity;

import java.util.List;
import javax.persistence.*;

@Entity
public class Environment
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String workflow = null;
	@ElementCollection
	@OrderColumn
	private List<String> params = null;
	
	public Environment()
	{
		
	}
	
	public Environment(String workflow, List<String> params)
	{
		this.setWorkflow(workflow);
		this.setParameters(params);
	}

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
	
	public List<String> getParameters()
	{
		return this.params;
	}
	
	public void setParameters(List<String> params)
	{
		this.params = params;
	}
}
