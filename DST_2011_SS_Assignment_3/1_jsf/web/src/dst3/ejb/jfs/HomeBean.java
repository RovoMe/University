package dst3.ejb.jfs;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import dst3.ejb.interfaces.JobExecutionSimulatorLocal;
import dst3.ejb.interfaces.TestingLocal;

@ManagedBean(name="gms")
@ApplicationScoped
public class HomeBean implements Serializable
{
	private static final long serialVersionUID = 3814932957920199950L;
	
	private boolean isLoaded = false;
	private int numberOfDeletedRows = 0;
	private boolean isInitialShow = true;
	private final String jobExecutionSimulator = "JobExecutionSimulator";
	@EJB
	private TestingLocal testing;
	@EJB
	private JobExecutionSimulatorLocal execution;
	private UIMessage message;
	
	public HomeBean()
	{
		
	}
	
	public boolean getIsLoaded()
	{
		return this.isLoaded;
	}
	
	public void setMessage(UIMessage message)
	{
		this.message = message;
	}
	
	public UIMessage getMessage()
	{
		return this.message;
	}

	public void loadData()
	{
		if (this.isInitialShow)
			this.isInitialShow = false;
		this.testing.init();
		this.isLoaded = true;
		this.execution.init(jobExecutionSimulator);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m");
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
				bundle.getString("home_loadMsg"), null);
	    fc.addMessage(message.getClientId(), msg);
	    fc.renderResponse();
	}
	
	public void unloadData()
	{
		this.execution.unload(jobExecutionSimulator);
		this.numberOfDeletedRows = this.testing.unload();
		this.isLoaded = false;
		
		FacesContext context = FacesContext.getCurrentInstance();
 		ExternalContext ec = context.getExternalContext();
 		final HttpServletRequest request = (HttpServletRequest)ec.getRequest();
 	    request.getSession(false).invalidate();
 	    
		FacesContext fc = FacesContext.getCurrentInstance();
		ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m");
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
				numberOfDeletedRows+" "+bundle.getString("home_unloadMsg"), null);
	    fc.addMessage(message.getClientId(), msg);
	    fc.renderResponse();
	}
	
	public int getNumberOfDeletedRows()
	{
		return this.numberOfDeletedRows;
	}
	
	public boolean getIsInitialShow()
	{
		return this.isInitialShow;
	}
}
