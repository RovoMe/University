package ws;

import java.math.BigDecimal;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import data.Product;

/**
 * <p>WarehouseService is an internal warehouse, which stores a certain 
 * amount of specified products. After receiving an order, WarehouseService
 * gets checked if the needed products are available, if so, WarehouseService
 * will provide those products for shipment, otherwise an external supplier
 * has to be asked to ship those goods.</p>
 * @author roman
 */
@WebService ( targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/warehouse" )
// to be WS-I compliant and having the method-name inside the soap-body
// we use Document Literal-Wrapped
// --> http://www.ibm.com/developerworks/webservices/library/ws-whichwsdl/
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public interface WarehouseService
{
	/**
	 * Orders a certain amount of products from the WarehousService. Before
	 * ordering products from the WarehouseService, it should be checked for 
	 * availability.
	 * @param product Kind of product which should be ordered.
	 * @param amount Corresponds to the quantity of products needed. 
	 * @return Returns the total cost of order.
	 * @throws UnknownProductFault Thrown if the product to order is invalid or 
	 * 		not stored in the WarehouseService
	 * @throws IllegalArgumentException if a parameter is null or empty in case 
	 * 		of arrays
	 * @see {@link #check_availability(Product, int)}
	 */
	@WebMethod(action="order")
	@WebResult(name="totalPrice")
	public BigDecimal order(@WebParam(name="product") Product product, 
			@WebParam(name="amount") int amount) throws UnknownProductFault, IllegalArgumentException;
	
	/**
	 * Checks if a needed product is currently available at the warehouse. If
	 * the product is available but the amount is not sufficient, this method
	 * is supposed to return false, which indicates that the product is not
	 * available.
	 * @param product Product which should be checked for availability.
	 * @param amount Corresponds to the quantity of products needed.
	 * @return true, if the certain amount of a product is available, false 
	 * 		otherwise.
	 * @throws UnknownProductFault If the product could not be found in the 
	 * 		internal database or the argument is invalid.
	 * @throws IllegalArgumentException if a parameter is null or empty in case 
	 * 		of arrays
	 */
	@WebMethod(action="check_availability")
	@WebResult(name="isAvailable")
	public WarehouseReturn check_availability(@WebParam(name="product") Product product, 
			@WebParam(name="amount") int amount) throws UnknownProductFault, IllegalArgumentException;
}
