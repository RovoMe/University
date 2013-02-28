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

public class CouponFormBackingBean implements Serializable
{
	private static final long serialVersionUID = -667891650722298089L;
	private String instanceId = "";
	private String taskId = "";
	private String hash = "";
	private String page = "";
	private static Logger logger = Logger.getLogger(CouponFormBackingBean.class);
	
	public CouponFormBackingBean()
	{
		
	}
	
	public String getHash() { return this.hash; }
	public void setHash(String hash) { this.hash = hash; }
	
	public String getPage() { return page; }
	public void setPage(String page) { this.page = page; }
	
	public String getInstanceId() { return this.instanceId; }
	public void setInstanceId(String instanceId) { this.instanceId = instanceId; }
	
	public String getTaskId() { return this.taskId; }
	public void setTaskId(String taskId) { this.taskId = taskId; }
	
	public void validate(ComponentSystemEvent event)
	{
		
		UIForm form = (UIForm)event.getComponent();
		// Hidden input fields which contain instanceId and taskId
		UIInput instanceUI = (UIInput)form.findComponent("hiddenInstanceId");
		UIInput taskUI = (UIInput)form.findComponent("hiddenTaskId");
		
		// Form input fields	
		UIInput couponUI = (UIInput)form.findComponent("coupon");
		
		// Retrieving form fields
		Map<String, String> prop = new HashMap<String, String>();
		prop.put("hash", couponUI.getValue().toString());
		
		// Retrieving Activiti engine
		ActivitiEngine engine = ActivitiEngine.getInstance();	
		//String taskError = taskUI.getValue().toString();
		
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
				// Get the first task
				Task task = tasks.get(0);	
				logger.info("next task: "+task.getName());
				this.setTaskId(task.getId());
				taskUI.setValue(task.getId());
			}
			
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, taskError, null);
		    fc.addMessage(null, msg);
		    fc.renderResponse();
		    logger.info("Setting error message: "+msg.getSummary());
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
			// Get the first task
			Task task = tasks.get(0);			
			logger.info("forwarding to: /"+engine.getProcessEngine().getFormService().getTaskFormData(task.getId()).getFormKey());
			this.setPage("/"+engine.getProcessEngine().getFormService().getTaskFormData(task.getId()).getFormKey());
			this.setTaskId(task.getId());
			
			result = "success";
		}
		return result;
	}
}