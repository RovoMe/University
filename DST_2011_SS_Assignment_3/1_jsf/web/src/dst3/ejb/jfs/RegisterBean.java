package dst3.ejb.jfs;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import dst3.ejb.interfaces.UserManagementLocal;

@ManagedBean(name="register")
@ViewScoped
public class RegisterBean implements Serializable
{
	private static final long serialVersionUID = -5527148744356294990L;

	@EJB
	private UserManagementLocal um;
	@ManagedProperty(value="#{message}")
	private MessageBean message;
	
	private String userName;
	private String firstName;
	private String lastName;
	private String password1;
	private String password2;
	private String streetName;
	private String city;
	private String zipCode;
	private String accountNo;
	private String bankCode;
	
	public String getUserName()
	{
		return this.userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public String getPassword1()
	{
		return this.password1;
	}
	
	public void setPassword1(String password1)
	{
		this.password1 = password1;
	}
	
	public String getPassword2()
	{
		return this.password2;
	}
	
	public void setPassword2(String password2)
	{
		this.password2 = password2;
	}
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public String getStreetName()
	{
		return this.streetName;
	}
	
	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}
	
	public String getCity()
	{
		return this.city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getZipCode()
	{
		return this.zipCode;
	}
	
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}
	
	public String getAccountNo()
	{
		return this.accountNo;
	}
	
	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}
	
	public String getBankCode()
	{
		return this.bankCode;
	}
	
	public void setBankCode(String bankCode)
	{
		this.bankCode = bankCode;
	}
	
	public void setMessage(MessageBean message)
	{
		this.message = message;
	}
	
	// this method is called after the validation succeeded without any errors
	// so pass the valid data to the UserManager
	public String register()
	{		
		if (this.um.addUser(userName, password1, firstName, lastName, 
				streetName, city, zipCode, accountNo, bankCode))
		{
			FacesContext fc = FacesContext.getCurrentInstance();
			ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m");
			message.setText(bundle.getString("register_success"), 
					null,
					FacesMessage.SEVERITY_INFO);
			return bundle.getString("home_page");
		}
		return null;
	}
	
	// validates the form to find any errors regarding the user input
	// "never trust any user"
	public void validate(ComponentSystemEvent event)
	{
		UIForm form = (UIForm)event.getComponent();
		UIInput pw1 = (UIInput) form.findComponent("password1");
		UIInput pw2 = (UIInput) form.findComponent("password2");
		UIInput zipCode = (UIInput) form.findComponent("zip");
		UIInput accountNo = (UIInput) form.findComponent("accountNo");
		UIInput bankCode = (UIInput) form.findComponent("bankCode");

		// check if a password has been entered
		if (pw1 == null || pw1.getValue() == null)
		{
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.renderResponse();
			return;
		}
		
		// check further that both passwords are equal
		// if not, a message should be shown to the user telling him
		// that both passwords do not match
		if ( ! (pw1.getValue().equals(pw2.getValue())))
		{      
			pw1.setValue("");
			pw2.setValue("");
			FacesContext fc = FacesContext.getCurrentInstance();
			ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m");
			message.setText(bundle.getString("register_passwordDifferent"), 
					bundle.getString("register_passwordDifferentDetail"),
					FacesMessage.SEVERITY_ERROR,
					pw1.getClientId());
			fc.renderResponse();
		}
		
		String zip = (String)zipCode.getValue();
		if (zip.length()>8 || zip.length()<4)
		{
			zipCode.setValue("");
			FacesContext fc = FacesContext.getCurrentInstance();
			ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m");
			message.setText(bundle.getString("register_zipError"), 
					null,
					FacesMessage.SEVERITY_ERROR,
					zipCode.getClientId());
			fc.renderResponse();
		}
		
		String accountNumber = (String)accountNo.getValue();
		if (accountNumber.length()>12 || accountNumber.length()<5 )
		{
			accountNo.setValue("");
			FacesContext fc = FacesContext.getCurrentInstance();
			ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m");
			message.setText(bundle.getString("register_accountNoError"), 
					null,
					FacesMessage.SEVERITY_ERROR,
					accountNo.getClientId());
			fc.renderResponse();
		}
		
		String bankcode = (String)bankCode.getValue();
		if (bankcode.length()>7 || bankcode.length()<5)
		{
			bankCode.setValue("");
			FacesContext fc = FacesContext.getCurrentInstance();
			ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m");
			message.setText(bundle.getString("register_bankCodeError"), 
					null,
					FacesMessage.SEVERITY_ERROR,
					bankCode.getClientId());
			fc.renderResponse();
		}
	}
	
	// checks if the user name is still available
	// if not, a message will be shown indicating that the
	// user name already exists
	public void validateUserName(FacesContext context, UIComponent toValidate, Object value)
	{
		String userName = (String)value;
		if (!this.um.isUserNameAvailable(userName))
		{
			((UIInput)toValidate).setValid(false);
			FacesContext fc = FacesContext.getCurrentInstance();
			ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m");
			message.setText(bundle.getString("register_userExists"), 
					bundle.getString("register_userExistsDetail"),
					FacesMessage.SEVERITY_ERROR,
					toValidate.getClientId());
		}
	}
	
//	public void onUsernameChange(ValueChangeEvent event)
//	{
//		// http://java-server-faces.blogspot.com/2006/04/valuechangelisteners-what-you-need-to.html
//		PhaseId phaseId = event.getPhaseId();
//		String userName = (String)event.getNewValue();
//		if (phaseId.equals(PhaseId.ANY_PHASE))
//		{
//			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
//			event.queue();
//		}
//		else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES))
//		{
//			if (!this.grid.isUserNameAvailable(userName))
//			{
//				((UIInput)event.getComponent()).setValid(false);
//				FacesMessage message = new FacesMessage("Username already exists!");
//				FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(FacesContext.getCurrentInstance()),message);
//				FacesContext.getCurrentInstance().renderResponse();
//	//			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),null,"register.jsf");
//	
//			}
//		}
//	}
}
