package wfm;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import wfm.bean.Customer;

/**
 * <p>JUnit test for an online order- and payment-process using the Activiti workflow
 * engine and a credit card payment modality.</p>
 * <p>Please change <em>yourEMail</em> to your own mail address to receive a confirmation 
 * mail at the end of the process.</p>
 * 
 * @author Roman Vottner
 */
public class RuntimeServiceTest_CreditCardPayment
{
	private static AllServers servers = null;
	private static RuntimeService runtimeService;
	private static ProcessEngine processEngine;
	private static ProcessInstance processInstance;
	private static Logger logger = Logger.getLogger(RuntimeServiceTest_CreditCardPayment.class);
	private String yourEMail = "yourEMail@yourHost.com";
	
	@BeforeClass
	public static void init() throws FileNotFoundException
	{
		servers = new AllServers();
		servers.start();
		
		// Create Activiti process engine
		processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine();
		
		RepositoryService repositoryService = processEngine.getRepositoryService();
		DeploymentBuilder builder = repositoryService.createDeployment();
		String xmlFile = "PaymentProcess.bpmn";
		builder.addClasspathResource("diagrams/"+xmlFile);
		builder.name(xmlFile);
		builder.deploy();
		runtimeService = processEngine.getRuntimeService();
	}
	
	@Test
	public void startProcessInstance() 
	{
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("price", 150);
		processInstance = runtimeService.startProcessInstanceByKey("OnlinePaymentProcess", variableMap);
		assertNotNull(processInstance.getId());
		logger.info("Created new Activiti process instance: id " + processInstance.getId() + " " + processInstance.getProcessDefinitionId());
	}
	
	@Test
	public void testAddressValidation()
	{				
		TaskService taskService = processEngine.getTaskService();
		FormService formService = processEngine.getFormService();

		// Verify kermit can now retrieve the task
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("kermit").processInstanceId(processInstance.getId()).list();
		assertEquals(1, tasks.size());
		assertEquals("get address", tasks.get(0).getName());
		
		/**
		 * Invalid address
		 */
		for (Task task : tasks)
		{
			assertEquals("get address", task.getName());
			Map<String, String> formData = new HashMap<String, String>();
			formData.put("firstName", "Max");
			formData.put("lastName", "Muster");
			formData.put("street", "Mustergasse 2");
			formData.put("zip", "1080"); // wrong zip-code!
			formData.put("city", "Wien");
			formData.put("country", "Austria");
			formData.put("telNo", "1234567");
			formData.put("mailAddress", this.yourEMail);
			formService.submitTaskFormData(task.getId(), formData);
			
			break;
		}
		
		// There should only be one task available at this stage
		tasks = taskService.createTaskQuery().taskAssignee("kermit").processInstanceId(processInstance.getId()).list();
		assertEquals(1, tasks.size());
		assertEquals("get address", tasks.get(0).getName());
		
		/**
		 * Valid address
		 */
		for (Task task : tasks) 
		{
			assertEquals("get address", task.getName());
			Map<String, String> formData = new HashMap<String, String>();
			formData.put("firstName", "Max");
			formData.put("lastName", "Muster");
			formData.put("street", "Mustergasse 2");
			formData.put("zip", "1090");
			formData.put("city", "Wien");
			formData.put("country", "Austria");
			formData.put("telNo", "1234567");
			formData.put("mailAddress", this.yourEMail);
			formService.submitTaskFormData(task.getId(), formData);
			
			break;
		}
		
		// check if the customer was set correctly
		Customer customer = (Customer) runtimeService.getVariable(processInstance.getId(), "customer");
		assertNotNull(customer);
		assertEquals("Max", customer.getFirstName());
		assertEquals("Muster", customer.getLastName());
		assertEquals("Mustergasse 2", customer.getAddress().getStreet());
		
		// There should now be 2 tasks available
		tasks = taskService.createTaskQuery().taskAssignee("kermit").processInstanceId(processInstance.getId()).list();
		assertEquals(2, tasks.size());
		List<String> expected = new ArrayList<String>();
		expected.add("get payment modalities");
		expected.add("get coupon");
		assertTrue(expected.contains(tasks.get(0).getName()));
		assertTrue(expected.contains(tasks.get(1).getName()));
	}
	
	@Test
	public void testPaymentModality()
	{
		TaskService taskService = processEngine.getTaskService();
		FormService formService = processEngine.getFormService();
		
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("kermit").processInstanceId(processInstance.getId()).list();
		assertEquals(2, tasks.size());
		List<String> expected = new ArrayList<String>();
		expected.add("get payment modalities");
		expected.add("get coupon");
		assertTrue(expected.contains(tasks.get(0).getName()));
		assertTrue(expected.contains(tasks.get(1).getName()));
		
		for (Task task : tasks)
		{
			assertNotNull(task);
			if (task.getName().equals("get payment modalities"))
			{
				Map<String, String> formData = new HashMap<String, String>();
				formData.put("paymentMethod", "creditCardMethod");
				formService.submitTaskFormData(task.getId(), formData);
				
				break;
			}
		}
		
		tasks = taskService.createTaskQuery().taskAssignee("kermit").processInstanceId(processInstance.getId()).list();
		assertEquals(1, tasks.size());
		assertEquals("get coupon", tasks.get(0).getName());
	}
	
	@Test
	public void testCouponValidation()
	{
		TaskService taskService = processEngine.getTaskService();
		FormService formService = processEngine.getFormService();
		
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("kermit").processInstanceId(processInstance.getId()).list();
		assertEquals(1, tasks.size());
		
		/**
		 * Invalid coupon hash
		 */
		for (Task task : tasks)
		{
			assertNotNull(task);
			assertEquals("get coupon", task.getName());
			Map<String, String> formData = new HashMap<String, String>();
			formData.put("hash", "abc");
			formService.submitTaskFormData(task.getId(), formData);
			
			break;
		}
		
		tasks = taskService.createTaskQuery().taskAssignee("kermit").processInstanceId(processInstance.getId()).list();
		assertEquals(1, tasks.size());
		
		/**
		 * Valid coupon hash
		 */
		for (Task task : tasks)
		{
			assertNotNull(task);
			assertEquals("get coupon", task.getName());
			Map<String, String> formData = new HashMap<String, String>();
			formData.put("hash", "abcd");
			formService.submitTaskFormData(task.getId(), formData);
			
			break;
		}
		
		// check if the price was reduced correctly
		Object price = runtimeService.getVariable(processInstance.getId(), "price");
		assertNotNull(price);
		assertEquals(new Double(130), price);
		
		// 'get credit card data' should be next on the list
		tasks = taskService.createTaskQuery().taskAssignee("kermit").processInstanceId(processInstance.getId()).list();
		assertEquals(1, tasks.size());
		assertEquals("get credit card data", tasks.get(0).getName());
	}
	
	@Test
	public void testCreditCardValidation()
	{
		TaskService taskService = processEngine.getTaskService();
		FormService formService = processEngine.getFormService();
		
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("kermit").processInstanceId(processInstance.getId()).list();
		assertEquals(1, tasks.size());
		assertEquals("get credit card data", tasks.get(0).getName());
		
		// Invalid credit card data
		for (Task task : tasks)
		{
			assertNotNull(task);
			assertEquals("get credit card data", task.getName());
			Map<String, String> formData = new HashMap<String, String>();
			formData.put("cardNumber", "12345"); // invalid credit card number
			formData.put("cardType", "Visa");
			formData.put("expirationDate", "0115");
			formData.put("CVV2Code", "1234");
			formData.put("nameOfOwner", "Max Muster");
			formService.submitTaskFormData(task.getId(), formData);
		}
		
		tasks = taskService.createTaskQuery().taskAssignee("kermit").processInstanceId(processInstance.getId()).list();
		assertEquals(1, tasks.size());
		// As of an error the form should be empty
		assertEquals(0, formService.getTaskFormData(tasks.get(0).getId()).getFormProperties().size());
		
		// Invalid credit card data
		for (Task task : tasks)
		{
			assertNotNull(task);
			assertEquals("get credit card data", task.getName());
			Map<String, String> formData = new HashMap<String, String>();
			formData.put("cardNumber", "123456"); // valid credit card number
			formData.put("cardType", "Visa");
			formData.put("expirationDate", "0115");
			formData.put("CVV2Code", "1234");
			formData.put("nameOfOwner", "Max Muster");
			formService.submitTaskFormData(task.getId(), formData);
		}
		
		tasks = taskService.createTaskQuery().taskAssignee("kermit").processInstanceId(processInstance.getId()).list();
		assertEquals(1, tasks.size());
		assertEquals("get acknowledgement", tasks.get(0).getName());
	}
	
	@Test
	public void testAcknowledgement()
	{
		TaskService taskService = processEngine.getTaskService();
		FormService formService = processEngine.getFormService();
		
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("kermit").processInstanceId(processInstance.getId()).list();
		assertEquals(1, tasks.size());
		assertEquals("get acknowledgement", tasks.get(0).getName());
		
		for (Task task : tasks)
		{
			Map<String, String> formData = new HashMap<String, String>();
			formService.submitTaskFormData(task.getId(), formData); // Stack trace starts here
		}
		
		tasks = taskService.createTaskQuery().taskAssignee("kermit").processInstanceId(processInstance.getId()).list();
		assertEquals(0, tasks.size());
	}
	
	@Test
	public void queryProcessInstance() 
	{
		// print open process instances - shouldn't return any
		List<ProcessInstance> instanceList = runtimeService.createProcessInstanceQuery().processDefinitionKey("OnlinePaymentProcess").list();
		for (ProcessInstance queryProcessInstance : instanceList) 
		{
			assertEquals(false, queryProcessInstance.isEnded());
			logger.info("id " + queryProcessInstance.getId() + ", ended=" + queryProcessInstance.isEnded());
		}
		assertEquals(0, instanceList.size());
	}
	
	@AfterClass
	public static void clean()
	{
		if (servers != null)
			servers.stop();
	}
}