package dst3.ejb.jfs;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIMessage;
import javax.faces.context.FacesContext;

@ManagedBean(name="message")
@SessionScoped
public class MessageBean
{
	@ManagedProperty(value="#{gms}")
	private HomeBean homeBean;
	private UIMessage message;
	private String txt;
	
	public void setHomeBean(HomeBean homeBean)
	{
		this.homeBean = homeBean;
	}
	
	public UIMessage getMessage()
	{
		if (this.message != null)
			return this.message;
		else
			return homeBean.getMessage();
	}
	
	public void setMessage(UIMessage message)
	{
		if (message != null)
			this.message = message;
		else
			this.message = homeBean.getMessage();
	}
	
	public String getText()
	{
		return this.txt;
	}
	
	public void setText(String text, String detail, FacesMessage.Severity type)
	{
		this.txt = text;
		
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(type, txt, detail);
	    fc.addMessage(this.getMessage().getClientId(), msg);
	}
	
	public void setText(String text, String detail, FacesMessage.Severity type, String clientId)
	{
		this.txt = text;
		
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(type, txt, detail);
	    fc.addMessage(clientId, msg);
	}
}
