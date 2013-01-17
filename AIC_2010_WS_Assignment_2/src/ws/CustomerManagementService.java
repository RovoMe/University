package ws;

import data.Customer;
import java.math.BigDecimal;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * <p>The creation, the update, the retrieval and the deletion of
 * {@link Customer}s are the main methods of this class. Besides 
 * that, the {@link #update_account(String, BigDecimal)}-method is 
 * able to change the balance-state of a customer whereas the 
 * {@link #notify(String, String)}-method is used to send an existing
 * customer a message.</p>
 * <p>This service defines methods to be used with REST clients.</p>
 * 
 * @author Roman
 */
@Path("/customerservice")
@Produces("application/json")
@Consumes("application/json")
public interface CustomerManagementService
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
	@PUT
	@Path("/customers/create/{id}/{name}")
	public Customer create(@PathParam("id")String id,
			@PathParam("name")String name);
	
	/**
	 * Updates all fields of an existing {@link Customer}. If the 
	 * customer is currently unknown to the system, a new object will 
	 * be created.
	 * @param customer The new customer object, which replaces the old 
	 * 		one.
	 * @return If an invalid customer object no customer-object could 
	 * 		be updated and so therefore null will be returned, else the 
	 * 		changed or newly created customer is returned.
	 */
	@POST
	@Path("/customers/update/")
	public Customer update(Customer customer);
	
	/**
	 * Retrieval method for an existing {@link Customer}
	 * @param id Unique ID or an customer.
	 * @return If the customer ID is known to the system,
	 * 		the customer will be returned, null otherwise.
	 */
    @GET
    @Path("/customers/get/{id}")
	public Customer get(@PathParam("id")String id);
	
	/**
	 * Removes an existing {@link Customer} from the system.
	 * @param id Unique ID of the customer to delete from the
	 * 		system. 
	 */
    @DELETE
    @Path("/customers/delete/{id}")
	public Response delete(@PathParam("id")String id);
	
	/**
	 * Sends a message to the {@link Customer}.
	 * @param id ID of Customer to send a message to.
	 * @param message Message to send to a customer.
	 */
    @PUT
    @Path("/customers/notify/{id}/{msg}")
	public Response notify(@PathParam("id")String id, 
			@PathParam("msg") String message);
	
	/**.
	 * Modifies the balance-state of a {@link Customer}
	 * @param id Unique ID of the customer whose balance state 
	 * 		should be modified
	 * @param changedValue New balance-state of the customer.
	 */
    @PUT
    @Path("/customers/update_account/{id}/{changedValue}")
	public Response update_account(@PathParam("id")String id,
			@PathParam("changedValue")BigDecimal changedValue);
}
