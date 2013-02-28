package wfm.activiti;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import wfm.bean.BankAccount;
import wfm.bean.CreditCard;
import wfm.ws.bankTransfer.AccountNotFoundFault;
import wfm.ws.bankTransfer.BankTransferService;
import wfm.ws.creditCard.rest.CreditCardRESTService;

public class PaymentAcknowledgementWSBehavior implements JavaDelegate
{
	private static Logger logger = Logger.getLogger(PaymentAcknowledgementWSBehavior.class);
	
	@Override
	public void execute(DelegateExecution execution) throws Exception 
	{
		// Retrieve service strings from the execution
		String serviceString = execution.getVariable("paymentService").toString();
		String paymentMethod = execution.getVariable("paymentMethod").toString();
		
		if (paymentMethod.equals("bankTransferMethod"))
		{
			// connect to the SOAP PaymentService
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.getInFaultInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(BankTransferService.class);
			factory.setAddress(serviceString);
			BankTransferService bankTransferService = (BankTransferService)factory.create();
			
			BankAccount bank = (BankAccount)execution.getVariable("BankAccount");
			Double amount = new Double(execution.getVariable("price").toString());
			if (bank != null)
			{
				logger.info(bank.toString());
				try
				{
					boolean success = bankTransferService.doPayment(bank.getAccountNumber(), bank.getBankCode(), amount);
					logger.info("doBankTransferPayment succeeded: "+success);
					if (success)
						execution.setVariable("paymentError", "");
					else
						execution.setVariable("paymentError", "Your bank reported that you do not have enough credit to buy these products!");
				}
				catch (AccountNotFoundFault anfEx)
				{
					execution.setVariable("paymentError", anfEx.getLocalizedMessage());
					logger.error("Error: "+anfEx.getLocalizedMessage());
				}
			}
			else
			{
				execution.setVariable("paymentError", "BankAccount could not be found within the execution!");
				logger.error("BankAccount could not be found within the execution!");
			}
			
		}
		else if (paymentMethod.equals("creditCardMethod"))
		{
			JAXRSClientFactoryBean sf = new JAXRSClientFactoryBean();
			sf.setResourceClass(CreditCardRESTService.class);
			sf.setAddress("http://localhost:63083");
			
			// bindings
			BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
			JAXRSBindingFactory factory = new JAXRSBindingFactory();
			factory.setBus(sf.getBus());
			manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
			
			// retrieve the service
			CreditCardRESTService creditCardService = sf.create(CreditCardRESTService.class);
			// restrict content-type to be of type "application/json"
			WebClient wc = sf.createWebClient();
			wc.accept(MediaType.APPLICATION_JSON);
			
			CreditCard card = (CreditCard)execution.getVariable("creditCard");
			Double amount = new Double(execution.getVariable("price").toString());
			if (card != null)
			{
				logger.info(card.toString());
				try
				{
					boolean success = creditCardService.doPayment(card.getCardNumber(), card.getCardType(), amount);
					logger.info("doCreditCardPayment succeeded: "+success);
					if (success)
						execution.setVariable("paymentError", "");
					else
						execution.setVariable("paymentError", "Your credit institute reported that you do not have enough credit to buy these products!");
				}
				catch (WebApplicationException waEx)
				{
					execution.setVariable("paymentError", waEx.getLocalizedMessage());
					logger.error("Error: "+waEx.getLocalizedMessage());
				}
			}
			else
			{
				execution.setVariable("paymentError", "creditCard could not be found within the execution!");
				logger.error("creditCard could not be found within the execution!");
			}
		}
	}
}
