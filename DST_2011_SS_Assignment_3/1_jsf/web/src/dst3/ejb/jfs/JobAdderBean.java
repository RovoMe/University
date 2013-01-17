package dst3.ejb.jfs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import dst3.ejb.exceptions.NotEnoughCPUsAvailableException;

@ManagedBean(name="addJob")
@ViewScoped
public class JobAdderBean implements Serializable
{
	private static final long serialVersionUID = 3501512730165720818L;
	
	private long gridId;
	private int numCPUs = 0;
	private String workflow;
	private String param1;
	private String param2;
	private String param3;
	private String param4;
	private String param5;
	
	@ManagedProperty(value="#{jobManager}")
	private JobManagerBean jobManagerBean;
	@ManagedProperty(value="#{login}")
    private LoginBean loginBean;
	@ManagedProperty(value="#{message}")
	private MessageBean message;
    
    public void setLoginBean(LoginBean loginBean)
    {
        this.loginBean = loginBean;
    }
    
	public long getGridId()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		String sId = fc.getExternalContext().getRequestParameterMap().get("id");
		if (sId != null)
		{
			long id = new Long(sId);
			if (id != 0)
				this.gridId = id;
		}
		return this.gridId;
	}
	
	public void setGridId(long gridId)
	{
		this.gridId = gridId;
	}
	
	public int getNumberOfCPUs()
	{
		return this.numCPUs;
	}
	
	public void setNumberOfCPUs(int numCPUs)
	{
		this.numCPUs = numCPUs;
	}
	
	public String getWorkflow()
	{
		return this.workflow;
	}
	
	public void setWorkflow(String workflow)
	{
		this.workflow = workflow;
	}
	
	public String getParam1()
	{
		return this.param1;
	}
	
	public void setParam1(String param)
	{
		this.param1 = param;
	}
	
	public String getParam2()
	{
		return this.param2;
	}
	
	public void setParam2(String param)
	{
		this.param2 = param;
	}
	
	public String getParam3()
	{
		return this.param3;
	}
	
	public void setParam3(String param)
	{
		this.param3 = param;
	}
	
	public String getParam4()
	{
		return this.param4;
	}
	
	public void setParam4(String param)
	{
		this.param4 = param;
	}
	
	public String getParam5()
	{
		return this.param5;
	}
	
	public void setParam5(String param)
	{
		this.param5 = param;
	}	
	
	public void setJobManagerBean(JobManagerBean jobBean)
	{
		this.jobManagerBean = jobBean;
	}
	
	public void setMessage(MessageBean message)
	{
		this.message = message;
	}
	
	// resets the values of the input-elements
	public String doReset()
	{
        System.out.println(">>>>> reseting form for "+loginBean.getUserName());
		this.numCPUs = 0;
		this.workflow = "";
		this.param1 = "";
		this.param2 = "";
		this.param3 = "";
		this.param4 = "";
		this.param5 = "";
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m"); 
		return bundle.getString("job_page")+"?id="+this.gridId;
	}
	
	// called upon submitting a new job
	// completeness of the data gets checked via validators
	public String addJob()
	{		
        System.out.println(">>>>> Adding Job for "+loginBean.getUserName());
		FacesContext fc = FacesContext.getCurrentInstance();
		ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m"); 
		
		try
		{
			// add those parameters which have been defined
			List<String> params = new ArrayList<String>();
			if (this.param1 != null && !this.param1.equals(""))
				params.add(this.param1);
			if (this.param2 != null && !this.param2.equals(""))
				params.add(this.param2);
			if (this.param3 != null && !this.param3.equals(""))
				params.add(this.param3);
			if (this.param4 != null && !this.param4.equals(""))
				params.add(this.param4);
			if (this.param5 != null && !this.param5.equals(""))
				params.add(this.param5);
			
			// as we use validators for grid-id and numCPUs we should
			// be safe for calling the addJob-method
			this.jobManagerBean.addJob(gridId, numCPUs, workflow, params);
			
			message.setText(bundle.getString("job_success"), 
					null,
					FacesMessage.SEVERITY_INFO);
		    this.doReset();
			
		    return bundle.getString("grid_page");
		}
		catch (IllegalArgumentException e)
		{
			message.setText(e.getLocalizedMessage(), 
					null,
					FacesMessage.SEVERITY_ERROR);
			// as we use HTTP-GET to set the id of the grid in the form
			// we have to provide the gridId in the call of the job.xhtml-page
			// again
			return bundle.getString("job_page")+"?id="+this.gridId;
		}
		catch (NotEnoughCPUsAvailableException e)
		{
            System.err.println(">>>>> not enough CPUs available for "+loginBean.getUserName());
            message.setText(e.getLocalizedMessage(), 
            		null,
            		FacesMessage.SEVERITY_ERROR);
			return bundle.getString("job_page")+"?id="+this.gridId;
		}
	}
}
