package ws.impl;

import java.math.BigDecimal;

import javax.jws.WebService;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import data.Customer;
import ws.CustomerManagementService;
import ws.CustomerManagementServiceSOAP;

/**
 * <p>Implementation the SOAP-interface {@link CustomerManagementServiceSOAP} 
 * which is an adapter for {@link CustomerManagementService} to provide REST
 * services to SOAP clients.</p>
 * 
 * @author Roman
 */
@WebService(endpointInterface="ws.CustomerManagementServiceSOAP",  
		serviceName="CustomerManagementService", 
		targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/customermanagement",
		portName="CustomerManagementPT") // wird nach portType gemapped
public class CustomerManagementServiceSOAPImpl implements CustomerManagementServiceSOAP
{
	private JAXRSClientFactoryBean rsFactory = null;

	public CustomerManagementServiceSOAPImpl()
	{
		/*		
		// should work but the following exception occurs:
		// org.apache.cxf.BusException: No binding factory for namespace 
		//     http://apache.org/cxf/binding/jaxrs registered.
		// this should be caused due to not using the Spring-framework
		WebClient web = WebClient.create("http://localhost:8088/CustomerManagementService");
		web.path("/customers");
		web.type("application/json");
		web.accept("application/json");
		CustomerManagementService service = (CustomerManagementService)web.get(CustomerManagementService.class);
		 */	

		this.rsFactory = new JAXRSClientFactoryBean();
		this.rsFactory.setServiceClass(CustomerManagementService.class);
		this.rsFactory.setAddress("http://localhost:8089/");
		BindingFactoryManager manager = this.rsFactory.getBus().getExtension(BindingFactoryManager.class);
		JAXRSBindingFactory bindingFactory = new JAXRSBindingFactory();
		bindingFactory.setBus(this.rsFactory.getBus());
		manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, bindingFactory);
	}
	
	@Override
	public Customer create(String id, String name) throws IllegalArgumentException
	{
		if (id == null || "".equals(id))
			throw new IllegalArgumentException("CustomerSOAP::create(): ID must not be null or empty");
		if (name == null || "".equals(name))
			throw new IllegalArgumentException("Name must not be null or empty");
		
		System.out.println("[CustomManagementServiceSOAP] Calling create");
		CustomerManagementService service = rsFactory.create(CustomerManagementService.class);	
		Customer c = service.create(id, name);
		return c;
	}

	@Override
	public Customer update(Customer customer) throws IllegalArgumentException
	{
		if (customer == null)
			throw new IllegalArgumentException("Invalid customer to update specified");
		
		System.out.println("[CustomManagementServiceSOAP] Calling update");
		CustomerManagementService service = this.rsFactory.create(CustomerManagementService.class);	
		return service.update(customer);
	}

	@Override
	public Customer get(String id) throws IllegalArgumentException
	{
		if (id == null || "".equals(id))
			throw new IllegalArgumentException("CustomerSOAP::get(): ID must not be null or empty");
		
		System.out.println("[CustomManagementServiceSOAP] Calling get");
		CustomerManagementService service = this.rsFactory.create(CustomerManagementService.class);
		
		Customer customer = service.get(id);
		if (customer == null) {
			throw new IllegalArgumentException("CustomerSOAP::get(): unkown user");
		}
		
		return customer;
	}

	@Override
	public void delete(String id) throws IllegalArgumentException
	{
		if (id == null || "".equals(id))
			throw new IllegalArgumentException("CustomerSOAP::delete(): ID must not be null or empty");
		
		System.out.println("[CustomManagementServiceSOAP] Calling delete");
		CustomerManagementService service = this.rsFactory.create(CustomerManagementService.class);	
		service.delete(id);
	}

	@Override
	public void notify(String id, String message) throws IllegalArgumentException
	{
		if (id == null || "".equals(id))
			//throw new IllegalArgumentException("CustomerSOAP::notify(): ID must not be null or empty");
		if (message == null || "".equals(message))
			throw new IllegalArgumentException("Message to send must not be null or empty");
		
		System.out.println("[CustomManagementServiceSOAP] Calling notify");
		CustomerManagementService service = this.rsFactory.create(CustomerManagementService.class);	
		service.notify(id, message);
	}

	@Override
	public void update_account(String id, BigDecimal changedValue) throws IllegalArgumentException
	{
		if (id == null || "".equals(id))
			throw new IllegalArgumentException("CustomerSOAP::update_account(): ID must not be null or empty");
		if (changedValue == null)
			throw new IllegalArgumentException("Value to change must not be null");

		System.out.println("[CustomManagementServiceSOAP] Calling update_account");
		CustomerManagementService service = this.rsFactory.create(CustomerManagementService.class);	
		service.update_account(id, changedValue);
	}
}
