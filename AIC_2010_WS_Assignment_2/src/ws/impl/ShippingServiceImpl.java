package ws.impl;

import java.util.Date;
import java.util.UUID;
import javax.jws.WebService;
import ws.ShippingService;
import ws.UnknownAddressFault;
import ws.UnknownProductFault;
import data.Addresses;
import data.DataBackend;
import data.Item;
import data.Product;
//import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
//import org.apache.cxf.interceptor.LoggingInInterceptor;
//import org.apache.cxf.interceptor.LoggingOutInterceptor;

// http://cxf.apache.org/docs/developing-a-service.html#DevelopingaService-RequiredAnnotations

@WebService(
		endpointInterface="ws.ShippingService",  
		serviceName="ShippingService", 
		targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/shipping",
		portName="ShippingPT" // wird nach portType gemapped
)
public class ShippingServiceImpl implements ShippingService
{
//    private class Inner extends Thread {
//        Inner(String name) {
//            super(name);
//            start();
//        }
//        public void run() {
//            try {
//                sleep(10000);
//            } catch(InterruptedException e) {
//                // throw new RuntimeException(e);
//            }
//            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//            factory.getInFaultInterceptors().add(new LoggingInInterceptor());
//            factory.getOutInterceptors().add(new LoggingOutInterceptor());
//            factory.setServiceClass(ShippingService.class);
//            factory.setAddress("http://localhost:8088/ShippingService");
//            ShippingService shippingService = (ShippingService)factory.create();
//
//        }
//    }


	@Override
	public String ship_items(Item[] items, Addresses address) 
		throws UnknownAddressFault, UnknownProductFault, IllegalArgumentException
	{

		// check if all parameters are valid
		if (items == null || items.length <= 0)
			throw new IllegalArgumentException("No items to ship specified");
		if (address == null)
			throw new IllegalArgumentException("Provided address may not be null");
		
		// check if the address is known to the system
		Addresses storedAddress = DataBackend.getInstance().getAddress(address.getId());
		if (storedAddress == null || !storedAddress.equals(address))
			throw new UnknownAddressFault("[ShippingService] ship_items: unknown address");
		
		UUID ret = UUID.randomUUID();
		String retItems = "[ShippingService] Sending items ";
		for (Item item : items)
		{
			if (item != null)
			{
				Product p = item.getProduct();
				
				// is the stored product in the item valid?
				if (p == null)
					throw new UnknownProductFault("One of the products to ship is null");
				
				// check if the product is known to the system
				Product storedProduct = DataBackend.getInstance().getProduct(p.getId());
				if (storedProduct == null || !storedProduct.equals(p))
					throw new UnknownProductFault("Unknown product to ship");
				else
					// everything worked fine so ship the product
					// and add an info to the console
					retItems+="'" + storedProduct.getName() + "', ";
			}
		}
		
		// remove the last ", "
		retItems = retItems.substring(0, retItems.length()-2);
		retItems+=" to";
		
		// create log-output on server-side
		System.out.println("[ShippingService] "+new Date(System.currentTimeMillis()));
		System.out.println(retItems);
		System.out.println("[ShippingService] "+address.getStreet()+" "+
				address.getHouse()+", "+address.getZipCode()+" "+address.getCity());
		
		new ShippingCallbackHelper(ret.toString());

		return ret.toString();
	}

}
