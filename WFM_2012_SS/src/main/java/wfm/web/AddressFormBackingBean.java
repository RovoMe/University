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
import wfm.bean.Customer;

public class AddressFormBackingBean implements Serializable
{
	private static final long serialVersionUID = -7965328224038235301L;
	private Customer customer = new Customer();
	private String page = "";
	private String instanceId = "";
	private String taskId = "";
	private static Logger logger = Logger.getLogger(AddressFormBackingBean.class);
	
	public AddressFormBackingBean()
	{

	}
	
	public Customer getCustomer() { return this.customer; }
	public void setCustomers(Customer customer) { this.customer = customer; }
	
	public String getInstanceId() { return this.instanceId; }
	public void setInstanceId(String instanceId) { this.instanceId = instanceId; }
	
	public String getTaskId() { return this.taskId; }
	public void setTaskId(String taskId) { this.taskId = taskId; }
	
	public String getPage() { return page; }
	public void setPage(String page) { this.page = page; }
	
	public void validate(ComponentSystemEvent event)
	{
		UIForm form = (UIForm)event.getComponent();
		// Hidden input fields which contain instanceId and taskId
		UIInput instanceUI = (UIInput)form.findComponent("hiddenInstanceId");
		UIInput taskUI = (UIInput)form.findComponent("hiddenTaskId");
		
		// Form input fields		
		UIInput firstNameUI = (UIInput)form.findComponent("firstName");
		UIInput lastNameUI = (UIInput)form.findComponent("lastName");
		UIInput streetUI = (UIInput)form.findComponent("street");
		UIInput zipUI = (UIInput)form.findComponent("zip");
		UIInput cityUI = (UIInput)form.findComponent("city");
		UIInput countryUI = (UIInput)form.findComponent("country");
		UIInput telNoUI = (UIInput)form.findComponent("telNo");
		UIInput emailUI = (UIInput)form.findComponent("email");
		
		// Retrieving form fields
		Map<String, String> prop = new HashMap<String, String>();
		prop.put("firstName", firstNameUI.getValue().toString());
		prop.put("lastName", lastNameUI.getValue().toString());
		prop.put("street", streetUI.getValue().toString());
		prop.put("zip", zipUI.getValue().toString());
		prop.put("city", cityUI.getValue().toString());
		prop.put("country", countryUI.getValue().toString());
		prop.put("telNo", telNoUI.getValue().toString());
		prop.put("mailAddress", emailUI.getValue().toString());
		
		// Retrieving Activiti engine
		ActivitiEngine engine = ActivitiEngine.getInstance();	

		// Submitting task form
		engine.getProcessEngine().getFormService().submitTaskFormData(taskUI.getValue().toString(), prop);
		// Check for any execution errors
		String taskError = engine.getTaskError(instanceUI.getValue().toString());
		if (taskError != null && !taskError.equals(""))
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
		    fc.addMessage(null, msg);
		    logger.info("Setting error message: "+msg.getSummary());
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
