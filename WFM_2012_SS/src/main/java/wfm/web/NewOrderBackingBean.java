package wfm.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import wfm.bean.ItemEntry;
import wfm.bean.Product;

public class NewOrderBackingBean implements Serializable
{
	private static final long serialVersionUID = -5209137839862052151L;
	private String instanceId = "";
	private String taskId = "";
	private List<ItemEntry> items;
	private String page = "";
	private static Logger logger = Logger.getLogger(NewOrderBackingBean.class);
	
	public NewOrderBackingBean()
	{
		logger.info("Initializing item entries");
		items = new ArrayList<ItemEntry>();
		
		ItemEntry e1 = new ItemEntry();
		Product p1 = new Product();
		p1.setId("prod1");
		p1.setName("Product 1");
		p1.setPrice(20.0);
		e1.setProduct(p1);
		items.add(e1);
		
		ItemEntry e2 = new ItemEntry();
		Product p2 = new Product();
		p2.setId("prod2");
		p2.setName("Product 2");
		p2.setPrice(9.99);
		e2.setProduct(p2);
		items.add(e2);
		
		ItemEntry e3 = new ItemEntry();
		Product p3 = new Product();
		p3.setId("prod3");
		p3.setName("Product 3");
		p3.setPrice(15.99);
		e3.setProduct(p3);
		items.add(e3);
		
		ItemEntry e4 = new ItemEntry();
		Product p4 = new Product();
		p4.setId("prod4");
		p4.setName("Product 4");
		p4.setPrice(6.95);
		e4.setProduct(p4);
		items.add(e4);
		logger.info("Initialization ... done");
	}
	
	public List<ItemEntry> getItems()
	{
		return this.items;
	}
	
	public void setItems(List<ItemEntry> items)
	{
		this.items = items;
	}
	
	public String getPage() { return page; }
	public void setPage(String page) { this.page = page; }
	
	public String getInstanceId() { return this.instanceId; }
	public void setInstanceId(String instanceId) { this.instanceId = instanceId; }
	
	public String getTaskId() { return this.taskId; }
	public void setTaskId(String taskId) { this.taskId = taskId; }
	
	private Double calculateTotalPrice()
	{
		Double ret = 0.;
		for (ItemEntry item : items)
		{
			ret += item.getProduct().getPrice() * item.getQuantity();
		}
		return ret;
	}
	
	public String start()
	{
		logger.info("starting new order!");
		FacesContext fc = FacesContext.getCurrentInstance();
		// Get values for entries in messages.properties
		ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "m");
		String processName = bundle.getString("app_process_name");
		String price_var = bundle.getString("newOrder_price_var");
		// Get request-parameter sent from newOrder.jsf to show-case method parameter invocation
		// as JSF does not support calls to methods with parameters!
		String processDescription = fc.getExternalContext().getRequestParameterMap().get("processDescription").toString();
		// calculate the total price
		Double price = this.calculateTotalPrice();
		
		// Start the business process and pass the calculated price as a start variable
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put(price_var, price);
		ActivitiEngine engine = ActivitiEngine.getInstance();
		engine.init(processDescription);
		RuntimeService runtimeService = engine.getProcessEngine().getRuntimeService();
		ProcessInstance instance = runtimeService.startProcessInstanceByKey(processName, variableMap);
		
		// Retrieve process ID
		String id = instance.getId();
		this.setInstanceId(id);
		logger.info("Process ID: "+id);
		
		// Retrieve next task
		List<Task> tasks = engine.getProcessEngine().getTaskService().createTaskQuery().taskAssignee("kermit").processInstanceId(instance.getId()).list();
		if (tasks.size() > 0)
		{
			// Get first task
			Task task = tasks.get(0);
			this.setTaskId(task.getId());
			logger.info("Next Task: "+task.getId()+" "+task.getName()+"; descr: "+task.getDescription()+" for assignee: "+task.getAssignee()+" owner: "+task.getOwner()+" executionId: "+task.getExecutionId()+" processInstanceId: "+task.getProcessInstanceId()+" parentTaskId: "+task.getParentTaskId()+" processDefinitionId: "+task.getProcessDefinitionId()+" taskDefinitionKey: "+task.getTaskDefinitionKey());
	
			logger.info("forwarding to: /"+engine.getProcessEngine().getFormService().getTaskFormData(task.getId()).getFormKey());
			this.setPage("/"+engine.getProcessEngine().getFormService().getTaskFormData(task.getId()).getFormKey());
			this.setTaskId(task.getId());
			
			return "success";
		}
		return "";
	}
}
