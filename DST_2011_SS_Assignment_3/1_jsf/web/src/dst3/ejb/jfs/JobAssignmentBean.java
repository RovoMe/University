package dst3.ejb.jfs;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import dst3.ejb.dto.TempJobsDTO;
import dst3.ejb.entity.Grid;
import javax.annotation.PostConstruct;

@ManagedBean(name="assignJob")
@RequestScoped
public class JobAssignmentBean implements Serializable
{
	private static final long serialVersionUID = 4312044717374529728L;

	private int numJobs;	
	private boolean submittable = false;
    private List<TempJobsDTO> tmpJobList = null;
	
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
	
    @PostConstruct
    public void init()
    {
        // http://stackoverflow.com/questions/2090033/why-jsf-calls-getters-multiple-times
        System.out.println(">>>>> get temporary job list for user "+loginBean.getUserName());
        this.tmpJobList = new ArrayList<TempJobsDTO>();
        int amountOfJobs = 0;
        for (Grid grid : this.jobManagerBean.getAvailableGrids())
        {
            TempJobsDTO tempJobs = new TempJobsDTO();
            tempJobs.setGridId(grid.getId());
            int _numJobs = this.jobManagerBean.getAmountOfTemporaryStoredJobsForGrid(grid.getId());
            amountOfJobs += _numJobs;
            tempJobs.setNumTempJobs(_numJobs);
            tmpJobList.add(tempJobs);
        }
        if (amountOfJobs > 0)
            this.submittable = true;
        else
        	this.submittable = false;
    }
    
	public int getNumJobs()
	{
		return this.numJobs;
	}
	
	public void setNumJobs(int numJobs)
	{
		this.numJobs = numJobs;
	}
	
	public boolean getSubmittable()
	{
		return this.submittable;
	}
	
	public void setSubmittable(boolean submittable)
	{
		this.submittable = submittable;
	}
	
	public void setJobManagerBean(JobManagerBean jobManager)
	{
		this.jobManagerBean = jobManager;
	}
	
	public void setMessage(MessageBean message)
	{
		this.message = message;
	}
	
	// returns a list of currently stored temporary jobs
	public List<TempJobsDTO> getTempJobsList()
	{
		return this.tmpJobList;
	}
	
	// removes all currently stored jobs for a certain grid
	public String doRemove()
	{
        System.out.println(">>>>> removing temporary stored job for "+loginBean.getUserName());
		FacesContext fc = FacesContext.getCurrentInstance();
		ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m");
		String sId = fc.getExternalContext().getRequestParameterMap().get(bundle.getString("assignment_param"));
		this.jobManagerBean.removeAllJobsForGrid(new Long(sId));
		
        String template = bundle.getString("assignment_removeMsg");
		String resolved = MessageFormat.format(template,sId);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, resolved , null);
	    fc.addMessage(message.getMessage().getClientId(), msg);
	    this.init();
		return bundle.getString("assignment_page");
	}
	
	public String doAssign()
	{
        System.out.println(">>>>> assigning temporary stored jobs for "+loginBean.getUserName());
		FacesContext fc = FacesContext.getCurrentInstance();
		ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m"); 
		
		try
		{
			this.jobManagerBean.submitJobs();
			message.setText(bundle.getString("assignment_success"), 
					null, 
					FacesMessage.SEVERITY_INFO);
			return bundle.getString("home_page");
		}
		catch (Exception e)
		{
			message.setText(e.getLocalizedMessage(), null, FacesMessage.SEVERITY_ERROR);
			return null;
		}
	}
}
