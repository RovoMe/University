package dst3.ejb.jfs;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;
import dst3.ejb.entity.User;
import dst3.ejb.interfaces.UserManagementLocal;

@ManagedBean(name="login")
@SessionScoped
public class LoginBean implements Serializable
{
	private static final long serialVersionUID = 5654559937341437061L;

	// Variables needed for login.xhtml
	private boolean loggedIn = false;
	private String userName;
	private String password;
	
	@EJB
	private UserManagementLocal um;
	@ManagedProperty(value="#{message}")
	private MessageBean message;
	
	public LoginBean()
	{
		
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public boolean getLoggedIn()
	{
		return this.loggedIn;
	}
	
	public void setMessage(MessageBean message)
	{
		this.message = message;
	}
	
	public String doLogin()
	{
		this.loggedIn = true;
		FacesContext fc = FacesContext.getCurrentInstance();
		ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m");
		message.setText(bundle.getString("login_success")+this.getUserName(), 
				null, 
				FacesMessage.SEVERITY_INFO);
		return bundle.getString("home_page");
	}
	
	public void validate(ComponentSystemEvent event)
	{
		UIForm form = (UIForm)event.getComponent();
		UIInput username = (UIInput) form.findComponent("username");
		UIInput pw = (UIInput) form.findComponent("password");
		
		if (username == null || username.getValue() == null ||
				pw == null || pw.getValue() == null)
		{
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.renderResponse();
			return;
		}
		
		if ( ! (this.um.login(username.getValue().toString(), pw.getValue().toString())))
		{      
			FacesContext fc = FacesContext.getCurrentInstance();
			ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m");
			message.setText(bundle.getString("login_failure"), 
					bundle.getString("login_failureDetail"), 
					FacesMessage.SEVERITY_ERROR);
		    fc.renderResponse();
		}
	}
	
	public void doLogout()
	{
		this.um.logout();
		this.loggedIn = false;
		this.userName = null;
		// Invalidate Session
		FacesContext context = FacesContext.getCurrentInstance();
 		ExternalContext ec = context.getExternalContext();
 		final HttpServletRequest request = (HttpServletRequest)ec.getRequest();
 	    request.getSession(false).invalidate();
 	    
 	    FacesContext fc = FacesContext.getCurrentInstance();
		ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m");
		message.setText(bundle.getString("login_logoutMsg"), 
				null, 
				FacesMessage.SEVERITY_INFO);
//		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
//				bundle.getString("login_logoutMsg"), null);
//	    fc.addMessage(message.getMessage().getClientId(), msg);
		fc.renderResponse();
	}
	
	public User getUser()
	{
		return this.um.getLoggedInUser();
	}
}
