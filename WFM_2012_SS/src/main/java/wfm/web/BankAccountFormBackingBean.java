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
import wfm.bean.BankAccount;

public class BankAccountFormBackingBean implements Serializable
{
	private static final long serialVersionUID = 665806479835745355L;
	private String instanceId = "";
	private String taskId = "";
	private BankAccount bankAcccount = new BankAccount();
	private String page = "";
	private static Logger logger = Logger.getLogger(BankAccountFormBackingBean.class);
	private String bankName = "";
	private String bankCode = "";
	private String accountNumber = "";
	private String holdersName = "";
	
	public BankAccountFormBackingBean()
	{
		
	}
	
	public BankAccount getBankAcccount() { return this.bankAcccount; }
	public void setBankAccounts(BankAccount bankAccount) { this.bankAcccount = bankAccount; }
	
	public String getPage() { return page; }
	public void setPage(String page) { this.page = page; }
	
	public String getInstanceId() { return this.instanceId; }
	public void setInstanceId(String instanceId) { this.instanceId = instanceId; }
	
	public String getTaskId() { return this.taskId; }
	public void setTaskId(String taskId) { this.taskId = taskId; }
	
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getHoldersName() {
		return holdersName;
	}

	public void setHoldersName(String holdersName) {
		this.holdersName = holdersName;
	}

	public void setBankAcccount(BankAccount bankAcccount) {
		this.bankAcccount = bankAcccount;
	}

	public void validate(ComponentSystemEvent event)
	{
		UIForm form = (UIForm)event.getComponent();
		// Hidden input fields which contain instanceId and taskId
		UIInput instanceUI = (UIInput)form.findComponent("hiddenInstanceId");
		UIInput taskUI = (UIInput)form.findComponent("hiddenTaskId");
		
		// Form input fields	
		UIInput bankNameUI = (UIInput)form.findComponent("bankName");
		UIInput bankCodeUI = (UIInput)form.findComponent("bankCode");
		UIInput accountNumberUI = (UIInput)form.findComponent("accountNumber");
		UIInput holdersNameUI = (UIInput)form.findComponent("holdersName");

		// Retrieving form fields
		Map<String, String> prop = new HashMap<String, String>();
		prop.put("bankName", bankNameUI.getValue().toString());
		prop.put("bankCode", bankCodeUI.getValue().toString());
		prop.put("accountNumber",accountNumberUI.getValue().toString());
		prop.put("holdersName", holdersNameUI.getValue().toString());
		// Retrieving Activiti engine
		ActivitiEngine engine = ActivitiEngine.getInstance();		
		// Submitting task form
		engine.getProcessEngine().getFormService().submitTaskFormData(taskUI.getValue().toString(), prop);
		// Check for any execution errors
		String taskError = engine.getTaskError(instanceUI.getValue().toString());
		if (!taskError.equals(""))
		{	
			// Retrieve next task
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
