package wfm.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;

public class PaymentModalitiesFormBackingBean implements Serializable
{
	private static final long serialVersionUID = 2405237600538406799L;
	private String instanceId = "";
	private String taskId = "";
	private String paymentMethod = "";
	private String page = "";
	private static Logger logger = Logger.getLogger(PaymentModalitiesFormBackingBean.class);
	
	public PaymentModalitiesFormBackingBean()
	{
		
	}
	
	public String getPaymentMethod() { return this.paymentMethod; }
	public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
	
	public String getPage() { return page; }
	public void setPage(String page) { this.page = page; }
	
	public String getInstanceId() { return this.instanceId; }
	public void setInstanceId(String instanceId) { this.instanceId = instanceId; }
	
	public String getTaskId() { return this.taskId; }
	public void setTaskId(String taskId) { this.taskId = taskId; }
	
	public String submit()
	{
		// Retrieving form fields
		Map<String, String> prop = new HashMap<String, String>();
		prop.put("paymentMethod", this.paymentMethod);
		
		// Retrieving Activiti engine
		ActivitiEngine engine = ActivitiEngine.getInstance();		
		// Submitting task form
		engine.getProcessEngine().getFormService().submitTaskFormData(this.taskId, prop);
		// Check for any execution errors
		String taskError = engine.getTaskError(this.instanceId);
		if (!taskError.equals(""))
		{
			FacesContext fc = FacesContext.getCurrentInstance();
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, taskError, null);
		    fc.addMessage(null, msg);
		    fc.renderResponse();
		    return "";
		}
		
		// Retrieve next task
		List<Task> tasks = engine.getProcessEngine().getTaskService().createTaskQuery().taskAssignee("kermit").processInstanceId(this.instanceId).list();
		if (tasks.size() > 0)
		{
			// Get first task
			Task task = tasks.get(0);			
			logger.info("forwarding to: /"+engine.getProcessEngine().getFormService().getTaskFormData(task.getId()).getFormKey());
			this.setPage("/"+engine.getProcessEngine().getFormService().getTaskFormData(task.getId()).getFormKey());
			this.setTaskId(task.getId());
			
			return "success";
		}
		return "";
	}
}
