package ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import data.Item;
import data.Addresses;


/**
 * <p>ShippingService is responsible for executing a shipment of goods to a customer.
 * It therefore provides the {@link #ship_items}-method.</p>
 * 
 * @author roman
 */

@WebService ( targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/shipping" )
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public interface ShippingService
{
	/**
	 * This method executes a shipment of items to the stated address
	 * 
	 * @param items Items that should be sent.
	 * @param address Address the shipment should go to
	 * @return Returns a {@link String} which indicates a unique ID 
	 * @throws UnknownAddressFault will be thrown if the address is invalid or not known to the system
	 * @throws UnknownProductFault will be thrown if the product is invalid or not known to the system
	 * @throws IllegalArgumentException will be thrown if a parameter is null or not empty in case of arrays
	 */
	
	@WebMethod(operationName="ship_items", action="urn:Ship_items")
	@WebResult(name="uuid")
	public String ship_items(
			
			@WebParam(name="items") 
			Item[] items, 
			
			@WebParam(name="address") 
			Addresses address
			
	) throws UnknownAddressFault, UnknownProductFault, IllegalArgumentException;
}
