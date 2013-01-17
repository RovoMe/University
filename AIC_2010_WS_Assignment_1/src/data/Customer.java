package data;
import java.math.BigDecimal;
import java.util.Arrays;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Data-class representing a customer. It stores information like his ID, 
 * his name and his open balance.</p>
 * <p>This class stores a link to his {@link Addresses} and {@link Order}s.</p>
 * 
 * @author roman
 */
//the root element of our generated XML
@XmlRootElement ( name = "Customer" )
// maps this class to a generated schema type
@XmlType( name = "CustomerType", 
		propOrder={"id", "name", "openBalance","addresses","order"})
// bind all non-static, non-transient fields
// to XML unless annotated with @XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer
{
	@XmlAttribute (
			name = "id", 
			required = true
	)
	private String id = null;
	@XmlElement (   
			name = "name", 
			nillable = false, 
			type = String.class, 
			defaultValue = "null"
	)
	private String name = null;
	@XmlElement (   
			name = "openBalance", 
			nillable = false, 
			type = BigDecimal.class, 
			defaultValue = "null"
	)
	private BigDecimal openBalance = null;
	@XmlElementRef ( name="addresses",
			type = Addresses.class)
	private Addresses[] addresses = null;
	@XmlElementRef ( name="order",
			type = Order.class)
	private Order[] order = null;
	
	/**
	 * Instantiates the class.
	 */
	public Customer()
	{
		
	}
	
	/**
	 * Sets the ID of this customer, if there has not been 
	 * defined an ID for this customer before.
	 * @param id New ID of this customer if there has not 
	 * 		been defined one before.
	 */
	public void setId(String id)
	{
		if (this.id == null)
			this.id = id;
	}
	
	/**
	 * Returns the ID of this customer.
	 * @return Returns the ID of this customer.
	 */
	public String getId()
	{
		return this.id;
	}
	
	/**
	 * Sets the name of this customer.
	 * @param name Sets the name of this customer.
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Returns the name of this customer.
	 * @return Returns the name of this customer.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Sets the open balance for this customer.
	 * @param openBalance New open balance for this customer.
	 */
	public void setOpenBalance(BigDecimal openBalance)
	{
		this.openBalance = openBalance;
	}
	
	/**
	 * Returns the open balance for this customer.
	 * @return Returns the open balance for this customer.
	 */
	public BigDecimal getOpenBalance()
	{
		return this.openBalance;
	}
	
	/**
	 * Sets all {@link Addresses} of this customer.
	 * @param addresses Sets all {@link Addresses} of this customer.
	 */
	public void setAddresses(Addresses[] addresses)
	{
		if (addresses != null && addresses.length > 0)
			this.addresses = addresses;
	}
	
	/**
	 * Returns all {@link Addresses} of this customer.
	 * @return Returns all {@link Addresses} of this customer.
	 */
	public Addresses[] getAddresses()
	{
		return this.addresses;
	}
	
	/**
	 * Sets all {@link Order}s of this customer.
	 * @param order Sets all {@link Order}s of this customer.
	 */
	public void setOrder(Order[] order)
	{
		this.order = order;
	}
	
	/**
	 * Returns all {@link Order} of this customer.
	 * @return Returns all {@link Order} of this customer.
	 */
	public Order[] getOrder()
	{
		return this.order;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;
		if (obj instanceof Customer)
		{
			Customer cust = (Customer)obj;
			if (cust.getId().equals(this.getId()) && 
					cust.getName().equals(this.getName()) &&
					cust.getOpenBalance().equals(this.getOpenBalance()) &&
					cust.getAddresses().equals(this.getAddresses()) &&
					cust.getOrder().equals(this.getOrder()))
				return true;
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		// Algorithm taken from "Joshua Bloch, Effective Java, 2nd Edition; Addison Wesley, 2008"
		int result = 17;
		result = 31*result+id.hashCode();
		result = 31*result+name.hashCode();
		result = 31*result+openBalance.hashCode();
		result = 31*result+Arrays.hashCode(addresses);
		result = 31*result+Arrays.hashCode(order);
		return result;
	}
	
	@Override
	public String toString()
	{
		String addr = "";
		int i=1;
		for (Addresses address : addresses)
			addr+= "Addresse "+ i++ +": "+address.toString()+", ";
		String orderList = "";
		for (Order _order : order)
		{
			orderList+=" "+_order.toString()+", ";
		}
		return "Customer("+id+": "+name+", "+addr+" Orders: "+orderList+"openBalance="+openBalance+")";
	}
}
