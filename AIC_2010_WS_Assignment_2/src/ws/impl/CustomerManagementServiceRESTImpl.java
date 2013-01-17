package ws.impl;

import java.math.BigDecimal;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import data.Customer;
import data.DataBackend;
import ws.CustomerManagementService;

/**
 * @Path       relative path of this service
 * @GET        Retrieve data from the server
 * @PUT        Update data on the server
 * @DELETE     Delete data from the server
 * @POST       Creates new data on the server
 * @Produces   specifies the format of the response
 * @Consumes   specifies the format of the parameters
 * @QueryParam used to name a parameter in a query, f.e. id and nextParam
 *             are names of parameters in the following URL: 
 *             http://foo.net/service?id=1&nextParam=2
 * 
 * The return-type, in our case Customer, gets serialized using JAXB.
 * Response-Type has to be taken for procedures, as JAXRS mocks up that
 * every JSON has to start with a leading { ...
 * 
 * Issues concerning IllegalStatementException: Already connected-Messages:
 * 
 * "Is it the POST method which is used in this case? As it happens, in 2.2.5, 
 * proxies can not handle empty POSTS (those which do not have any bodies in 
 * the request) due to a minor restriction in HttpConduit. Which would likely 
 * explain the exception."
 * http://www.mail-archive.com/users@cxf.apache.org/msg12048.html
 */

/**
 * <p>This class implements services for the creation, modification and 
 * deletion of {@link Customer}-objects. Moreover this class implements
 * {@link #notify(String, String)} and {@link #update_account(String, BigDecimal)}
 * as specified in {@link CustomerManagementService}.</p>
 * 
 * @author Roman
 */
public class CustomerManagementServiceRESTImpl implements CustomerManagementService
{
	/**
	 * Instantiates the class.
	 */
	public CustomerManagementServiceRESTImpl()
	{
		
	}
	
	@Override
	public Customer create(String id, String name)
	{
		// test if already a customer for the provided id exists
		// if so, return it - if not create a new customer object
		Customer c = get(id);
		if (c != null)
			return c;
		else
			c = new Customer();
		
		if (id != null && !id.equals(""))
			c.setId(id);
		if (name != null && !name.equals(""))
			c.setName(name);
		System.out.println("[CustomerManagementService] Creating new customer with ID '"+id+"' and name '"+name+"'");
		DataBackend.getInstance().store(c);
		return c;
	}
	
	@Override
	public Customer update(Customer customer) 
	{
		if (customer != null && !"".equals(customer.getId()))
		{
			String id = customer.getId();
			Customer origCustomer = DataBackend.getInstance().getCustomer(id);
			
			if (origCustomer != null && customer != null)
			{
				System.out.println("[CustomerManagementService] Updating infos for customer: "+id+", new name: "+customer.getName());
				origCustomer.setName(customer.getName());
				origCustomer.setOpenBalance(customer.getOpenBalance());
				origCustomer.setAddresses(customer.getAddresses());
				origCustomer.setOrder(customer.getOrder());
				return origCustomer;
			}
			else
			{
				System.out.println("[CustomerManagementService] Customer with ID '"+id+"' not found, creating new one: "+customer.getName());
				DataBackend.getInstance().store(customer);
				return customer;
			}
		}
		return null;
	}
	
	@Override
	public Customer get(String id)
	{
		if (id == null)
			throw new IllegalArgumentException("ID must not be null");
		
		if (!"".equals(id))
		{
			System.out.println("[CustomerManagementService] Retrieving infos for customer: "+id);
			return DataBackend.getInstance().getCustomer(id);
		}
		return null;
	}
	
	@Override
	public Response delete(@PathParam("id") String id) 
	{
		if (id != null && !"".equals(id))
		{
			System.out.println("[CustomerManagementService] Deleting customer: "+id);
			DataBackend.getInstance().removeCustomer(id);
			// no guarantee that customer gets really deleted
			System.gc();
		}
		return Response.status(Status.OK).build();
	}
	
//	@Override
//	public Response delete(Customer customer) 
//	{
////		System.out.println("[CustomerManagementService] Delete called");
////		System.out.println(id);
//		if (customer != null && !"".equals(customer.getId()))
//		{
//			System.out.println("[CustomerManagementService] Deleting customer: "+customer.getId());
//			DataBackend.getInstance().removeCustomer(customer.getId());
//			// no guarantee that customer gets really deleted
//			System.gc();
//		}
//		return Response.status(Status.OK).build();
//	}

	@Override
	public Response notify(@PathParam("id") String id, @PathParam("msg")String message) 
	{
		if (id != null && !id.equals(""))
		{
			Customer customer = DataBackend.getInstance().getCustomer(id);
			if (customer != null )
				System.out.println("[CustomerManagementSystem] Sending "+
						customer.getName()+" message: "+message);
				
		} else {
			System.out.println("[CustomerManagementSystem] Sending to anonymous message: "+message);
		}
		return Response.status(Status.OK).build();
	}
	
	@Override
	public Response update_account(@PathParam("id") String id, @PathParam("changedValue")BigDecimal changedValue) 
	{
		if (id != null && !"".equals(id) && changedValue != null)
		{
			System.out.println("[CustomerManagementService] Updating account balance for customer '"+id+"', new balance state: "+changedValue);
			Customer cust = DataBackend.getInstance().getCustomer(id);
			BigDecimal oldBalance = cust.getOpenBalance();
                        cust.setOpenBalance(oldBalance.add(changedValue));
		}
		ResponseBuilder builder = Response.ok();
		// builder.type("application/json");
		return builder.build();
	}
}
