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

// http://cxf.apache.org/docs/developing-a-service.html#DevelopingaService-RequiredAnnotations
@WebService(endpointInterface="ws.ShippingService",  
		serviceName="ShippingService", 
		targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/shipping", 
		name="ShippingPT") // wird nach portType gemapped
public class ShippingServiceImpl implements ShippingService
{
	@Override
	public String ship_items(Item[] items, Addresses address) 
		throws UnknownAddressFault, UnknownProductFault
	{
		// check if all parameters are valid
		if (items == null || items.length <= 0)
			throw new IllegalArgumentException("No items to ship specified");
		if (address == null)
			throw new IllegalArgumentException("Provided address my not be null");
		
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
				{
					// everything worked fine so ship the product
					// and add an info to the console
					retItems+="'"+p.getName()+"', ";	
				}
			}
		}
		// remove the last ", "
		retItems = retItems.substring(0, retItems.length()-2);
		retItems+=" to";
		
		System.out.println("[ShippingService] "+new Date(System.currentTimeMillis()));
		System.out.println(retItems);
		System.out.println("[ShippingService] "+address.getStreet()+" "+
				address.getHouse()+", "+address.getZipCode()+" "+address.getCity());
		return ret.toString();
	}

}
