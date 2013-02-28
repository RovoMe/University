package wfm.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import wfm.bean.BankAccount;
import wfm.bean.CreditCard;
import wfm.bean.Customer;

public class AcknowledgeFormBackingBean implements Serializable
{
	private static final long serialVersionUID = -2168045695029319897L;
	private String instanceId = "";
	private String taskId = "";
	private String page = "";
	private static Logger logger = Logger.getLogger(AcknowledgeFormBackingBean.class);
	private String customerName = "";
	private String customerStreet = "";
	private String customerZipCity = "";
	private String customerCountry = "";
	private String customerEMail = "";
	private String paymentMethod = "";
	private String bankName = "";
	private String bankCode = "";
	private String accountNumber = "";
	private String holdersName = "";
	private String cardInstitute = "";
	private String cardExpirationDate = "";
	private String cardCVV2Code = "";
	private String cardOwnerName = "";
	private String cardNumber = "";
	private Double price = 0D;
	
	public AcknowledgeFormBackingBean()
	{
		ActivitiEngine engine = ActivitiEngine.getInstance();
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String instanceId = request.getParameter("instanceId");
		// Access variables stored in Activiti's execution object
		this.customerName = (String)engine.getProcessEngine().getRuntimeService().getVariable(instanceId, "name");
		Customer customer = (Customer)engine.getProcessEngine().getRuntimeService().getVariable(instanceId, "customer");
		if (customer != null)
		{
			this.customerStreet = customer.getAddress().getStreet();
			this.customerZipCity = customer.getAddress().getZip()+" "+customer.getAddress().getCity();
			this.customerCountry = customer.getAddress().getCountry();
			this.customerEMail = customer.getMailAddress();
		}
		this.paymentMethod = (String)engine.getProcessEngine().getRuntimeService().getVariable(instanceId, "paymentMethod");
		BankAccount bank = (BankAccount)engine.getProcessEngine().getRuntimeService().getVariable(instanceId, "BankAccount");
		if (bank != null)
		{
			this.bankName = bank.getBankName();
			this.bankCode = bank.getBankCode();
			this.accountNumber = bank.getAccountNumber();
			this.holdersName = bank.getHoldersName();
		}
		CreditCard card = (CreditCard)engine.getProcessEngine().getRuntimeService().getVariable(instanceId, "creditCard");
		if (card != null)
		{
			this.cardInstitute = card.getCardType();
			this.cardNumber = card.getCardNumber();
			this.cardCVV2Code = card.getCVV2Code();
			this.cardExpirationDate = card.getExpirationDate();
			this.cardOwnerName = card.getNameOfOwner();
		}
		this.price = (Double)engine.getProcessEngine().getRuntimeService().getVariable(instanceId, "price");
		logger.info("paymentMethod: "+this.paymentMethod);
	}
	
	public String getCustomerName() { return this.customerName; }
	public String getCustomerStreet() { return this.customerStreet; }
	public String getCustomerZipCity() { return this.customerZipCity; }
	public String getCustomerCountry() { return this.customerCountry; }
	public String getCustomerEMail() { return this.customerEMail; }
	public String getPaymentMethod() { return this.paymentMethod; }
	public String getBankName() { return this.bankName; }
	public String getBankCode() { return this.bankCode; }
	public String getAccountNumber() { return this.accountNumber; }
	public String getHoldersName() { return this.holdersName; }
	public String getCardInstitute() { return this.cardInstitute; }
	public String getCardExpirationDate() { return this.cardExpirationDate; }
	public String getCardCVV2Code() { return this.cardCVV2Code; }
	public String getCardOwnerName() { return this.cardOwnerName; }
	public String getCardNumber() { return this.cardNumber; }
	public Double getPrice() { return this.price; }
	public String getPage() { return this.page; }
	public void setPage(String page) { this.page = page; }
	
	public boolean getIsBankTransfer()
	{
		if (this.paymentMethod.equals("bankTransferMethod"))
			return true;
		return false;
	}
	public boolean getIsCreditCard()
	{
		if (this.paymentMethod.equals("creditCardMethod"))
			return true;
		return false;
	}
	
	public String getInstanceId() { return this.instanceId; }
	public void setInstanceId(String instanceId) { this.instanceId = instanceId; }
	
	public String getTaskId() { return this.taskId; }
	public void setTaskId(String taskId) { this.taskId = taskId; }
	
	public String submit()
	{
		String result = "error";
		logger.info("Order acknowledged");		
		// Retrieving Activiti engine
		ActivitiEngine engine = ActivitiEngine.getInstance();	
		// Submitting task form
		Map<String, String> prop = new HashMap<String, String>();
		// data should already be stored in the execution environment, so no need to fill it again
		engine.getProcessEngine().getFormService().submitTaskFormData(this.taskId, prop);
		
		String taskError = engine.getTaskError(this.instanceId, false);
		if (taskError == null || taskError.equals(""))
		{
			// Retrieve next task
			List<Task> tasks = engine.getProcessEngine().getTaskService().createTaskQuery().taskAssignee("kermit").processInstanceId(this.instanceId).list();
			if (tasks.size() == 0)
				result = "success";
			else
				logger.error("Task-list is not empty!");
		}
		else
			logger.error("Error while executing: "+taskError);
		return result;
	}
	
	public String cancel()
	{
		logger.info("Order canceled");
		ActivitiEngine engine = ActivitiEngine.getInstance();
		// delete process instance
		engine.getProcessEngine().getRuntimeService().deleteProcessInstance(this.instanceId, null);
		// Destroys current session
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
 		final HttpServletRequest request = (HttpServletRequest)ec.getRequest();
 	    request.getSession(false).invalidate();
 	    
		return "canceled";
	}
}
