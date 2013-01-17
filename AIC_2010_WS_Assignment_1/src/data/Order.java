package data;
import java.util.Arrays;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import data.adapter.DateToLongAdapter;

/**
 * <p>Data-class which represents an order of certain goods.</p>
 * 
 * @author roman
 */
// the root element of our generated XML
@XmlRootElement ( name = "Order" )
// maps this class to a generated schema type
@XmlType( name = "OrderType", 
		propOrder={"id", "orderDate", "items"})
// bind all non-static, non-transient fields
// to XML unless annotated with @XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public class Order
{
	// maps this field to an XML attribute
	@XmlAttribute (
			name = "id", 
			required = true
	)
	private String id = null;
 	@XmlJavaTypeAdapter( value = DateToLongAdapter.class)
	@XmlElement (   
			name = "orderDate", 
			nillable = false, 
			type = long.class, 
			defaultValue = "null"
	)
	private Date orderDate = null;
 	@XmlElementRef ( name = "items",
 			type=Item.class)
 	private Item[] items = null;
	
 	/**
 	 * Instantiates the class.
 	 */
	public Order()
	{
		
	}
	
	/**
	 * Sets the order number. If there was defined an order number 
	 * before no new order number will be set.
	 * @param id
	 */
	public void setId(String id)
	{
		if (this.id == null)
			this.id = id;
	}
	
	/**
	 * Returns the order number.
	 * @return Returns the order number.
	 */
	public String getId()
	{
		return this.id;
	}
	
	/**
	 * Sets the date the order was placed.
	 * @param orderDate Sets the date the order was placed.
	 */
	public void setOrderDate(Date orderDate)
	{
		this.orderDate = orderDate;
	}
	
	/**
	 * Returns the date the order was placed.
	 * @return Returns the date the order was placed.
	 */
	public Date getOrderDate()
	{
		return this.orderDate;
	}
	
	/**
	 * Set a bunch of items someone is going to order.
	 * @param items Items to order.
	 */
	public void setItems(Item[] items)
	{
		this.items = items;
	}
	
	/**
	 * Returns the ordered items
	 * @return Returns the ordered items.
	 */
	public Item[] getItems()
	{
		return this.items;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;
		if (obj instanceof Order)
		{
			Order order = (Order)obj;
			if (order.getId().equals(this.getId()) && 
					order.getOrderDate().equals(this.getOrderDate()) &&
					order.getItems().equals(this.getItems()))
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
		result = 31*result+orderDate.hashCode();
		result = 31*result+Arrays.hashCode(items);
		return result;
	}
	
	@Override
	public String toString()
	{
		String _items = "";
		for (Item item : items)
		{
			_items+=" "+item.toString()+", ";
		}
		return "Order("+id+": ordered items: "+_items+" orderDate="+orderDate+")";
	}
}
