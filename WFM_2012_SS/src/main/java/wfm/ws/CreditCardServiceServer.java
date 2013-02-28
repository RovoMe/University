package wfm.ws;

import java.util.HashMap;
import java.util.Map;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;
import wfm.ws.addressValidation.rest.AddressValidationRESTService;
import wfm.ws.creditCard.rest.CreditCardRESTService;
import wfm.ws.creditCard.rest.CreditCardRESTServiceImpl;

public class CreditCardServiceServer 
{
	private Server restServer = null;
	
	public void start()
	{	
		// define a JSON provider and a mapping between REST and JSON namespace's
		JSONProvider<AddressValidationRESTService> jsonProvider = new JSONProvider<AddressValidationRESTService>();
		Map<String, String> map = new HashMap<String, String> ();
		map.put ( "http://rest.ws.wfm", "{}" );
		jsonProvider.setNamespaceMap ( map );
		
		// http://cxf.apache.org/docs/jaxrs-services-configuration.html#JAXRSServicesConfiguration-ConfiguringJAXRSendpointsprogrammaticallywithoutSpring
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();        
        sf.setResourceClasses(CreditCardRESTService.class);
        sf.setResourceProvider(CreditCardRESTService.class, new SingletonResourceProvider(new CreditCardRESTServiceImpl()));
        sf.setAddress("http://localhost:63083");
        
        sf.getInFaultInterceptors().add(new LoggingInInterceptor());
        sf.getOutFaultInterceptors().add(new LoggingOutInterceptor());
        sf.getInInterceptors().add(new LoggingInInterceptor());
        sf.getOutInterceptors().add(new LoggingOutInterceptor());
        
        sf.setProvider(jsonProvider);
        
        BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
		JAXRSBindingFactory factory = new JAXRSBindingFactory();
		factory.setBus(sf.getBus());
		manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
        
        // REST-Service
        this.restServer = sf.create();
        this.restServer.start();
	}
	
	public void stop()
	{
		if (this.restServer != null)
			this.restServer.stop();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		new CreditCardServiceServer().start();
	}
}
