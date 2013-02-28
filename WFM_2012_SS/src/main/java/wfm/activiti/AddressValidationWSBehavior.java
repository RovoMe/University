package wfm.activiti;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.el.Expression;
import org.activiti.engine.impl.el.FixedValue;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;
import org.apache.log4j.Logger;
import wfm.bean.Customer;
import wfm.ws.addressValidation.rest.AddressValidationRESTService;
import wfm.ws.addressValidation.soap.AddressNotFoundFault;

public class AddressValidationWSBehavior implements JavaDelegate
{
	// These fields correspond to the name of the <activiti:field /> elements
	// in the bpmn
	private FixedValue service;
	private Expression firstName;
	private Expression lastName;
	private Expression street;
	private Expression city;
	private Expression zip;
	private Expression country;
	private Expression telNo;
	private Expression mailAddress;
	
	private static Logger logger = Logger.getLogger(AddressValidationWSBehavior.class);
	
	@Override
	public void execute(DelegateExecution execution) throws Exception 
	{
		String firstName = "";
		String lastName = "";
		String street = "";
		String city = "";
		String zip = "";
		String country = "";
		String telNo = "";
		String mailAddress = "";
		
		/*
		// connect to the SOAP AddressValidationService to check if the entered address is valid
		String serviceString = (String)service.getValue(execution);		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInFaultInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(AddressValidationService.class);
		factory.setAddress(serviceString);
		AddressValidationService addressValidationService = (AddressValidationService)factory.create();
		*/
		
		// connect to the REST AddressValidationRESTService to check if the entered address is valid
		String serviceString = (String)service.getValue(execution);	
			
		// define a JSON provider and a mapping between REST and JSON namespace's
		JSONProvider<AddressValidationRESTService> jsonProvider = new JSONProvider<AddressValidationRESTService>();
		Map<String, String> map = new HashMap<String, String> ();
		map.put ( "http://rest.ws.wfm", "{}" );
		jsonProvider.setNamespaceMap ( map );
		
		// create the REST service
		JAXRSClientFactoryBean sf = new JAXRSClientFactoryBean();
		sf.setResourceClass(AddressValidationRESTService.class);
		sf.setAddress(serviceString);
		
		// set loggers
		sf.getOutFaultInterceptors().add(new LoggingOutInterceptor());
		sf.getInFaultInterceptors().add(new LoggingInInterceptor());
		sf.getOutInterceptors().add(new LoggingOutInterceptor());
		sf.getInInterceptors().add(new LoggingInInterceptor());		
		
		// set the JSON provider
		sf.setProvider(jsonProvider);
		
		// set the binding
		BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
		JAXRSBindingFactory factory = new JAXRSBindingFactory();
		factory.setBus(sf.getBus());
		manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
		
		// retrieve the service
		AddressValidationRESTService addressValidationService = sf.create(AddressValidationRESTService.class);
		// restrict content-type to be of type "application/json"
		WebClient wc = sf.createWebClient();
		wc.accept(MediaType.APPLICATION_JSON);
		
		// retrieve input-values from the fields
		if (this.firstName != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.firstName.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				firstName = token;
			}
		}
		
		if (this.lastName != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.lastName.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				lastName = token;
			}
		}
		
		if (this.street != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.street.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				street = token;
			}
		}
		
		if (this.city != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.city.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				city = token;
			}
		}
		
		if (this.zip != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.zip.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				zip =  token;
			}
		}
		
		if (this.country != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.country.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				country = token;
			}
		}
		
		if (this.telNo != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.telNo.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				telNo = token;
			}
		}
		if (this.mailAddress != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.mailAddress.getValue(execution), ",");
			while (st.hasMoreTokens())
			{
				String token = st.nextToken().trim();
				mailAddress = token;
			}
		}
		
		try
		{
			Customer customer = addressValidationService.validateAddress(firstName, lastName, street, city, zip, country, telNo, mailAddress);
			logger.info("The Variable name is: " + customer);
			execution.setVariable("customer", customer);
		}
		catch (WebApplicationException waEx) // REST exception if address is invalid
		{
			logger.error(waEx.getClass().getName()+": "+waEx.getLocalizedMessage());
			execution.setVariable("addressValidationError", waEx.getLocalizedMessage());;
			execution.setVariable("customer", null);
		}
		catch (AddressNotFoundFault anfEx) // SOAP exception if address is invalid
		{
			logger.error(anfEx.getClass().getName()+": "+anfEx.getLocalizedMessage());
			execution.setVariable("addressValidationError", anfEx.getLocalizedMessage());
			execution.setVariable("customer", null);
		}
		catch (Exception e) // any other exception
		{
			logger.error(e.getClass().getName()+": "+e.getLocalizedMessage());
			execution.setVariable("addressValidationError", e.getLocalizedMessage());
			execution.setVariable("customer", null);
		}
	}
}
