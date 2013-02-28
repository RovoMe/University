package wfm.activiti;

import java.util.StringTokenizer;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import org.activiti.engine.impl.el.Expression;
import org.activiti.engine.impl.el.FixedValue;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import wfm.bean.CreditCard;
import wfm.ws.creditCard.rest.CreditCardRESTService;

public class CreditCardValidationWSBehavior implements ActivityBehavior
{
	// These fields correspond to the name of the <activiti:field /> elements
	// in the bpmn
	private FixedValue service;
	private FixedValue successPath;
	private FixedValue errorPath;
	private Expression cardNumber;
	private Expression nameOfOwner;
	private Expression expirationDate;
	private Expression CVV2Code;
	private Expression cardType;
	private static Logger logger = Logger.getLogger(CreditCardValidationWSBehavior.class);

	
	@Override
	public void execute(ActivityExecution execution) throws Exception 
	{
		String cardNumber = "";
		String nameOfOwner = "";
		String expirationDate = "";
		String CVV2Code = "";
		String cardType = "";
		
		// connect to the REST AddressValidationRESTService to check if the entered creditcard is valid
		String serviceString = (String)service.getValue(execution);	
		JAXRSClientFactoryBean sf = new JAXRSClientFactoryBean();
		sf.setResourceClass(CreditCardRESTService.class);
		sf.setAddress(serviceString);
		
		// set loggers
		sf.getOutFaultInterceptors().add(new LoggingOutInterceptor());
		sf.getInFaultInterceptors().add(new LoggingInInterceptor());
		sf.getOutInterceptors().add(new LoggingOutInterceptor());
		sf.getInInterceptors().add(new LoggingInInterceptor());

		// bindings
		BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
		JAXRSBindingFactory factory = new JAXRSBindingFactory();
		factory.setBus(sf.getBus());
		manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
		
		// retrieve the service
		CreditCardRESTService creditCardValidationService = sf.create(CreditCardRESTService.class);
		// restrict content-type to be of type "application/json"
		WebClient wc = sf.createWebClient();
		wc.accept(MediaType.APPLICATION_JSON);
		
		// retrieve input-values from the fields
		if (this.cardNumber != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.cardNumber.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				cardNumber = token;
			}
		}
		
		if (this.nameOfOwner != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.nameOfOwner.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				nameOfOwner = token;
			}
		}
		
		if (this.expirationDate != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.expirationDate.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				expirationDate = token;
			}
		}
		
		if (this.CVV2Code != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.CVV2Code.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				CVV2Code = token;
			}
		}
		
		if (this.cardType != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.cardType.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				cardType =  token;
			}
		}
		
		PvmTransition transition = null;
		try
		{
			CreditCard creditcard = creditCardValidationService.validateCreditCard(cardNumber, nameOfOwner, expirationDate, CVV2Code, cardType);
			logger.info("Credit card successfully verified: " + creditcard);
			execution.setVariable("creditCard", creditcard);
			execution.setVariable("paymentService", serviceString);
			// set the transition to the next activity
			transition = execution.getActivity().findOutgoingTransition((String)this.successPath.getValue(execution));
		}
		catch (WebApplicationException waEx) // REST exception if creditcard is invalid
		{
			logger.error(waEx.getClass().getName()+": "+waEx.getLocalizedMessage());
			execution.setVariable("paymentValidationError", waEx.getLocalizedMessage());
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
