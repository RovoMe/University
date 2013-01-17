package ws;

import java.math.BigDecimal;
import data.Product;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.Action;
import javax.xml.ws.soap.Addressing;

/**
 * Defines method to call the the supplier.
 * 
 * @author roman
 */
@WebService ( targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/supplying" )
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
@Addressing(enabled=true, required=true)
public interface SupplierService
{
	/**
	 * <p>Orders a certain amount of products from this supplier.</p>
	 * 
	 * @param product Product which should be ordered
	 * @param amount Defines how many products should be ordered
	 * @return
	 * @throws UnknownProductFault
	 * @throws IllegalArgumentException
	 */
	@WebMethod(operationName="order", action="urn:Order")
	// Due to an "org.apache.cxf.binding.soap.SoapFault: 
	//		A header representing a Message Addressing Property is not valid and the message 
	// 		cannot be processed"
	// Exception, which is caused by the action-element inside the @WebMethod-annotation 
	// being different from the @Action-annotation values, we have to adept them.
	// According Web Services Addressing 1.0 - SOAP Binding[1] 4.2 Description, The value 
	// of the SOAPAction HTTP header MUST either be identical to the value of the wsa:Action 
	// header, or be empty.
	@Action(input="urn:Order", 
			output="urn:OrderResponse")
	@WebResult(name="totalPrice")
	public abstract BigDecimal order(@WebParam(name="product") Product product, 
			@WebParam(name="amount") int amount) throws UnknownProductFault,IllegalArgumentException;
}
