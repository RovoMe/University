package dst3.ejb.jfs;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import dst3.ejb.entity.Grid;
import dst3.ejb.exceptions.EmptyJobListException;
import dst3.ejb.exceptions.NotEnoughCPUsAvailableException;
import dst3.ejb.exceptions.UserNotLoggedInException;
import dst3.ejb.interfaces.JobManagementLocal;

@ManagedBean(name="jobManager")
@SessionScoped
public class JobManagerBean implements Serializable
{
	private static final long serialVersionUID = 7601794395554577320L;
	
	@EJB
	private JobManagementLocal jm;
	@ManagedProperty(value="#{login}")
	private LoginBean loginBean;
	
	// needed for injecting LoginBackingBean, which stores the 
	// user name and the login-status
	public void setLoginBean(LoginBean loginBean)
	{
		this.loginBean = loginBean;
	}
	
	public LoginBean getLoginBean()
	{
		return this.loginBean;
	}
	
    public List<Grid> getAllGrids()
    {
        return this.jm.getAllGrids();
    }
    
    public int getNumberOfAvailableCPUsForGrid(long gridId)
    {
        return this.jm.getNumberOfAvailableCPUsForGrid(gridId);
    }
	
	public void addJob(long gridId, int numCPUs, String workflow, List<String> params) 
		throws IllegalArgumentException, NotEnoughCPUsAvailableException
	{
		this.jm.addJob(gridId, numCPUs, workflow, params);
	}
	
	public List<Grid> getAvailableGrids()
	{
		return this.jm.getAvailableGrids();
	}
	
	public int getAmountOfTemporaryStoredJobsForGrid(long gridId)
	{
		return this.jm.getAmountOfTemporaryStoredJobsForGrid(gridId);
	}
	
	public void removeAllJobsForGrid(long gridId)
	{
		this.jm.removeAllJobsForGrid(gridId);
	}
	
	public void submitJobs() throws UserNotLoggedInException, EmptyJobListException, NotEnoughCPUsAvailableException
	{
		this.jm.submitAllJobs(this.loginBean.getUser());
	}
}
