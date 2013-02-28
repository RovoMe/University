package wfm.activiti;

import java.util.StringTokenizer;

import org.activiti.engine.impl.el.Expression;
import org.activiti.engine.impl.el.FixedValue;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;

import wfm.bean.BankAccount;
import wfm.ws.bankTransfer.BankTransferService;
import wfm.ws.bankTransfer.BankTransferValidationFault;

public class BankTransferValidationWSBehavior implements ActivityBehavior
{
	private FixedValue service;
	private FixedValue successPath;
	private FixedValue errorPath;
	private Expression bankName;
	private Expression bankCode;
	private Expression accountNumber;
	private Expression holdersName;
	
	private static Logger logger = Logger.getLogger(BankTransferValidationWSBehavior.class);
	
	@Override
	public void execute(ActivityExecution execution) throws Exception 
	{
		String bankNameString = "";
		String bankCodeString = "";
		String bankAccountNumberString = "";
		String bankAccountHoldersNameString = "";
		
		// connect to the SOAP AddressValidationService to check if the entered address is valid
		String serviceString = (String)service.getValue(execution);		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInFaultInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(BankTransferService.class);
		factory.setAddress(serviceString);
		BankTransferService bankTransferValidationService = (BankTransferService)factory.create();
		
		// retrieve input-values from the fields
		if (this.bankName != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.bankName.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				bankNameString = token;
			}
		}
		
		if (this.bankCode != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.bankCode.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				bankCodeString = token;
			}
		}
		
		if (this.accountNumber != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.accountNumber.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				bankAccountNumberString = token;
			}
		}
		
		if (this.holdersName != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.holdersName.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				bankAccountHoldersNameString = token;
			}
		}
		
		PvmTransition transition = null;
		try
		{
			BankAccount account = bankTransferValidationService.validateBankAccount(bankNameString, bankCodeString, bankAccountNumberString, bankAccountHoldersNameString);
			logger.info("Account successfully verified: " + account.toString());
			execution.setVariable("BankAccount", account);
			execution.setVariable("paymentService", serviceString);
			// set the transition to the next activity
			transition = execution.getActivity().findOutgoingTransition((String)this.successPath.getValue(execution));
		}
		catch (BankTransferValidationFault anfEx) // SOAP exception if address is invalid
		{
			logger.error(anfEx.getClass().getName()+": "+anfEx.getLocalizedMessage());
			execution.setVariable("paymentValidationError", anfEx.getLocalizedMessage());
			// set the transition to the error end event
			transition = execution.getActivity().findOutgoingTransition((String)this.errorPath.getValue(execution));
		}
		catch (Exception e) // any other exception
		{
			logger.error(e.getClass().getName()+": "+e.getLocalizedMessage());
			execution.setVariable("paymentValidationError", e.getLocalizedMessage());
			// not sure if we should handle all exceptions in that way
			// set the transition to the error end event
			transition = execution.getActivity().findOutgoingTransition((String)this.errorPath.getValue(execution));
		}
		// and now execute the transition so we proceed in the workflow
		execution.take(transition);
	}

}
