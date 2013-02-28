package wfm.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import wfm.bean.CreditCard;


public class CreditCardFormBackingBean implements Serializable 
{
	private static final long serialVersionUID = -8429620830603360442L;
	private String instanceId = "";
	private String taskId = "";
	private CreditCard creditCard = new CreditCard();
	private String page = "";
	private static Logger logger = Logger.getLogger(CreditCardFormBackingBean.class);
	private String cardNumber ="";
	private String nameOfOwner ="";
	private String expirationDate ="";
	private String CVV2Code ="";
	private String cardType = "";
	public CreditCardFormBackingBean()
	{

	}
	
	public CreditCard getCreditCard() { return this.creditCard; }
	public void setCreditcards(CreditCard creditCard) { this.creditCard = creditCard; }

	public String getInstanceId() { return this.instanceId; }
	public void setInstanceId(String instanceId) { this.instanceId = instanceId; }
	
	public String getTaskId() { return this.taskId; }
	public void setTaskId(String taskId) { this.taskId = taskId; }
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getNameOfOwner() {
		return nameOfOwner;
	}

	public void setNameOfOwner(String nameOfOwner) {
		this.nameOfOwner = nameOfOwner;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCVV2Code() {
		return CVV2Code;
	}

	public void setCVV2Code(String cVV2Code) {
		CVV2Code = cVV2Code;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public String getPage() { return page; }
	public void setPage(String page) { this.page = page; }
	
	public void validate(ComponentSystemEvent event)
	{
		UIForm form = (UIForm)event.getComponent();
		// Hidden input fields which contain instanceId and taskId
		UIInput instanceUI = (UIInput)form.findComponent("hiddenInstanceId");
		UIInput taskUI = (UIInput)form.findComponent("hiddenTaskId");
		
		// Form input fields	
		UIInput cardNumberUI = (UIInput)form.findComponent("cardNumber");
		UIInput nameOfOwnerUI = (UIInput)form.findComponent("nameOfOwner");
		UIInput expirationDateUI = (UIInput)form.findComponent("expirationDate");
		UIInput CVV2CodeUI = (UIInput)form.findComponent("CVV2Code");
		UIInput cardTypeUI = (UIInput)form.findComponent("cardType");
		
		// Retrieving form fields
		Map<String, String> prop = new HashMap<String, String>();
		prop.put("cardNumber", cardNumberUI.getValue().toString());
		prop.put("nameOfOwner", nameOfOwnerUI.getValue().toString());
		prop.put("expirationDate", expirationDateUI.getValue().toString());
		prop.put("CVV2Code", CVV2CodeUI.getValue().toString());
		prop.put("cardType", cardTypeUI.getValue().toString());
		// Retrieving Activiti engine
		ActivitiEngine engine = ActivitiEngine.getInstance();		
		// Submitting task form
		engine.getProcessEngine().getFormService().submitTaskFormData(taskUI.getValue().toString(), prop);
		// Check for any execution errors
		String taskError = engine.getTaskError(instanceUI.getValue().toString());
		if (!taskError.equals(""))
		{	
			List<Task> tasks = engine.getProcessEngine().getTaskService().createTaskQuery().taskAssignee("kermit").processInstanceId(instanceUI.getValue().toString()).list();
			if (tasks.size() > 0)
			{
				// Get first task
				Task task = tasks.get(0);
				this.setTaskId(task.getId());
				taskUI.setValue(task.getId());
			}
			
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, taskError, null);
			logger.info("Setting error message: "+msg.getSummary());
		    fc.addMessage(null, msg);
		    fc.renderResponse();
		}
		
	}
	
	public String submit()
	{
		String result = "error";
		// Retrieving Activiti engine
		ActivitiEngine engine = ActivitiEngine.getInstance();
		// Retrieve next task
		List<Task> tasks = engine.getProcessEngine().getTaskService().createTaskQuery().taskAssignee("kermit").processInstanceId(this.instanceId).list();
		if (tasks.size() > 0)
		{
			// Get first task
			Task task = tasks.get(0);			
			logger.info("forwarding to: /"+engine.getProcessEngine().getFormService().getTaskFormData(task.getId()).getFormKey());
			this.setPage("/"+engine.getProcessEngine().getFormService().getTaskFormData(task.getId()).getFormKey());
			this.setTaskId(task.getId());
			
			result = "success";
		}
		return result;
	}
}
