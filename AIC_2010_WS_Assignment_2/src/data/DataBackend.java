package data;

import java.util.Map;
import java.util.List;
import java.util.Hashtable;
import java.util.Vector;

/**
 * <p>DataBackend is a simple backend solution which provides access to business 
 * data and offers an in-memory storage system.</p>
 * 
 * @author roman
 */
public class DataBackend
{
	private static DataBackend backend = null;
	
	private Map<String,Addresses> addressMap= new Hashtable<String,Addresses>();
	private Map<String,Customer> customerMap = new Hashtable<String,Customer>();
	private List<Item> itemList = new Vector<Item>();
	private Map<String,Order> orderMap = new Hashtable<String,Order>();
	private Map<String,Product> productMap = new Hashtable<String,Product>();
	
	/**
	 * Initializes this class.
	 */
	private DataBackend()
	{

	}
	
	/**
	 * Singleton-pattern for this data backend.
	 * @return Returns a new instance of this class if there has not 
	 * 		been defined one before, else the before created instance.
	 */
	public static DataBackend getInstance()
	{
		if (backend == null)
			backend = new DataBackend();
		
		return backend;
	}
	
	/**
	 * Stores an address-instance in the internal data structures.
	 * @param address {@link Addresses}-instance to be stored.
	 */
	public void store(Addresses address)
	{
		synchronized (addressMap)
		{
			addressMap.put(address.getId(),address);
		}
	}
	
	/**
	 * Stores a customer-instance in the internal data structures.
	 * @param customer {@link Customer}-instance to be stored.
	 */
	public void store(Customer customer)
	{
		synchronized (customerMap)
		{
			customerMap.put(customer.getId(),customer);
		}
	}
	
	/**
	 * Stores an item-instance in the internal data structures.
	 * @param item {@link Item}-instance to be stored.
	 */
	public void store(Item item)
	{
		synchronized (itemList)
		{
			itemList.add(item);
		}
	}
	
	/**
	 * Stores an order-instance in the internal data structures.
	 * @param order {@link Order}-instance to be stored.
	 */
	public void store(Order order)
	{
		synchronized (orderMap)
		{
			orderMap.put(order.getId(),order);
		}
	}
		
	/**
	 * Stores a product-instance in the internal data structures.
	 * @param product {@link Product}-instance to be stored.
	 */
	public void store(Product product)
	{
		synchronized (productMap)
		{
			productMap.put(product.getId(),product);
		}
	}
	
	/**
	 * Return all {@link Addresses} stored.
	 * @return Stored {@link Addresses}.
	 */
	public Addresses[] getAddresses()
	{
		Addresses[] ret = new Addresses[] {};
		synchronized(addressMap)
		{
			ret = addressMap.values().toArray(ret);
		}
		return ret;
	}
	
	/**
	 * Return all {@link Customer}s stored.
	 * @return Stored {@link Customer}s.
	 */
	public Customer[] getCustomer()
	{
		Customer[] ret = new Customer[] {};
		synchronized(customerMap)
		{
			ret = customerMap.values().toArray(ret);
		}
		return ret;
	}
	
	/**
	 * Return all {@link Item}s stored.
	 * @return Stored {@link Item}s.
	 */
	public Item[] getItem()
	{
		Item[] ret = new Item[] {};
		synchronized(itemList)
		{
			ret = itemList.toArray(ret);
		}
		return ret;
	}
	
	/**
	 * Return all {@link Order}s stored.
	 * @return Stored {@link Order}s.
	 */
	public Order[] getOrder()
	{
		Order[] ret = new Order[] {};
		synchronized(orderMap)
		{
			ret = orderMap.values().toArray(ret);
		}
		return ret;
	}
	
	/**
	 * Return all {@link Product}s stored.
	 * @return Stored {@link Product}s.
	 */
	public Product[] getProduct()
	{
		Product[] ret = new Product[] {};
		synchronized(productMap)
		{
			ret = productMap.values().toArray(ret);
		}
		return ret;
	}
	
	/**
	 * Returns the {@link Addresses} based on the ID.
	 * @param id Unique ID of an {@link Addresses}-object.
	 * @return {@link Addressees} related to this ID, null 
	 * 		if non could be found.
	 */
	public Addresses getAddress(String id)
	{
		Addresses address = null;
		synchronized(addressMap)
		{
			address = addressMap.get(id);
		}
		return address;
	}
	
	/**
	 * Returns the {@link Customer} based on his ID.
	 * @param id Unique ID of a {@link Customer}
	 * @return {@link Customer} which corresponds to the 
	 * 		provided ID, null if no customer could be found
	 * 		for the specified ID.
	 */
	public Customer getCustomer(String id)
	{
		Customer customer = null;
		synchronized(customerMap)
		{
			customer = customerMap.get(id);
		}
		return customer;
	}
	
	/**
	 * Returns the {@link Order} based on the order-ID.
	 * @param id Unique ID of an {@link Order}
	 * @return {@link Order} related to this ID, null
	 * 		if non could be found.
	 */
	public Order getOrder(String id)
	{
		Order order = null;
		synchronized(orderMap)
		{
			order = orderMap.get(id);
		}
		return order;
	}
	
	/**
	 * Returns the {@link Product} based on the ID of the 
	 * product.
	 * @param id Unique ID of an {@link Product}
	 * @return {@link Product} related to this ID, null
	 * 		if non could be found.
	 */
	public Product getProduct(String id)
	{
		Product product = null;
		synchronized(productMap)
		{
			product = productMap.get(id);
		}
		return product;
	}
	
	/**
	 * Removes the {@link Addresses} with the specified ID 
	 * from the system.
	 * @param address ID of the {@link Addresses} to be removed 
	 * 		from the system.
	 */
	public void removeAddresses(String id)
	{
		synchronized (addressMap)
		{
			addressMap.remove(id);
		}
	}
	
	/**
	 * Removes the {@link Customer} with the specified ID from
	 * the system.
	 * @param id ID of the {@link Customer} to be removed from
	 * 		the system.
	 */
	public void removeCustomer(String id)
	{
		synchronized (customerMap)
		{
			customerMap.remove(id);
		}
	}
	
	/**
	 * Removes the {@link Item} from the system.
	 * @param item {@link Item}-object to be removed from the 
	 * 		system.
	 */
	public void removeItem(Item item)
	{
		synchronized (itemList)
		{
			itemList.remove(item);
		}
	}
	
	/**
	 * Removes the {@link Order} with the specified ID from the
	 * system.
	 * @param id ID of the {@link Order} to be removed from the
	 * 		system.
	 */
	public void removeOrder(String id)
	{
		synchronized (orderMap)
		{
			orderMap.remove(id);
		}
	}
	
	/**
	 * Removes the {@link Product} with the specified ID from the
	 * system.
	 * @param id ID of the {@link Product} to be removed from the
	 * 		system.
	 */
	public void removeProduct(String id)
	{
		synchronized (productMap)
		{
			productMap.remove(id);
		}
	}
}
