package ws;

import data.Customer;
import java.math.BigDecimal;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.Action;

/**
 * <p>The creation, the update, the retrieval and the deletion of
 * {@link Customer}s are the main methods of this class. Besides 
 * that, the {@link #update_account(String, BigDecimal)}-method is 
 * able to change the balance-state of a customer whereas the 
 * {@link #notify(String, String)}-method is used to send an existing
 * customer a message.</p>
 * <p>This services wraps {@link CustomerManagementService} to provide
 * those services to SOAP clients.</p>
 * 
 * @author Roman
 */
@WebService ( targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/customermanagement" )
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public interface CustomerManagementServiceSOAP
{
	/**
	 * Creates a {@link Customer}-instance with the given
	 * id and name.
	 * @param id Unique ID of the new customer.
	 * @param name Name of the new customer.
	 * @return A new instance of {@link Customer} will be returned, 
	 * 		filled with the customers name and id. Note if one of 
	 * 		those values is invalid (null or empty), the field will
	 * 		be null.
	 */
	@WebMethod(operationName="create", action="urn:Create")
	@Action(input="urn:Create", output="urn:CreateResponse")
	@WebResult(name="customer")
	public Customer create(@WebParam(name="id")String id, 
			@WebParam(name="name")String name);
	
	/**
	 * Updates all fields of an existing {@link Customer}. If the customer
	 * is currently unknown to the system, a new object will be created.
	 * @param customer The new customer object, which replaces the old one.
	 * @return If an invalid customer object no customer-object could be 
	 *      updated and so therefore null will be returned, else the changed 
	 *      or newly created customer is returned.
	 */
	@WebMethod(operationName="update", action="urn:Update")
	@Action(input="urn:Update", output="urn:UpdateResponse")
	@WebResult(name="customer")
	public Customer update(@WebParam(name="newCustomer")Customer customer);
	
	/**
	 * Retrieval method for an existing {@link Customer}
	 * @param id Unique ID or an customer.
	 * @return If the customer ID is known to the system,
	 * 		the customer will be returned, null otherwise.
	 */
    @WebMethod(operationName="get", action="urn:Get")
    @Action(input="urn:Get", output="urn:GetResponse")
    @WebResult(name="customer")
	public Customer get(@WebParam(name="id")String id);
	
	/**
	 * Removes an existing {@link Customer} from the system.
	 * @param id Unique ID of the customer to delete from the
	 * 		system. 
	 */
    @WebMethod(operationName="delete", action="urn:Delete")
    @Action(input="urn:Delete")
	public void delete(@WebParam(name="id")String id);
	
	/**
	 * Sends a message to the {@link Customer}.
	 * @param id ID of Customer to send a message to.
	 * @param message Message to send to a customer.
	 */
    @WebMethod(operationName="notify", action="urn:Notify")
    @Action(input="urn:Notify")
	public void notify(@WebParam(name="id")String id, 
			@WebParam(name="message") String message);
	
	/**.
	 * Modifies the balance-state of a {@link Customer}
	 * @param id Unique ID of the customer whose balance state 
	 * 		should be modified
	 * @param changedValue New balance-state of the customer.
	 */
    @WebMethod(operationName="update_account", action="urn:Update_Account")
    @Action(input="urn:Update_Account")
	public void update_account(@WebParam(name="id")String id, 
			@WebParam(name="changedValue")BigDecimal changedValue);
}
