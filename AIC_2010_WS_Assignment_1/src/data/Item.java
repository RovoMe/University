package data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Data-class representing a certain amount of a product. This 
 * class should be thought of as a line on the order or invoice 
 * containing the product and how many times this product should be
 * ordered/bought.</p>
 * 
 * @author roman
 */
//the root element of our generated XML
@XmlRootElement ( name = "Item" )
// maps this class to a generated schema type
@XmlType( name = "ItemType", propOrder={"quantity","product"})
// bind all non-static, non-transient fields
// to XML unless annotated with @XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public class Item
{
	@XmlElement (   
			name = "quantity", 
			nillable = false, 
			type = int.class, 
			defaultValue = "0"
	)
	private int quantity = 0;
	@XmlElementRef ( name="product", 
			type=Product.class)
	private Product product = null;
	
	/**
	 * Instantiates the class.
	 */
	public Item()
	{
		
	}
	
	/**
	 * Sets how many products this item includes.
	 * @param quantity Sets how many products this item inclues.
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	/**
	 * Returns how many products this item includes.
	 * @return
	 */
	public int getQuantity()
	{
		return this.quantity;
	}
	
	/**
	 * Sets the product this item stands for.
	 * @param product The product this item stands for.
	 */
	public void setProduct(Product product)
	{
		this.product = product;
	}
	
	/**
	 * Returns the product this item stands for.
	 * @return Returns the product this item stands for.
	 */
	public Product getProduct()
	{
		return this.product;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;
		if (obj instanceof Item)
		{
			Item item = (Item)obj;
			if (item.getQuantity() == this.getQuantity() &&
					item.getProduct().equals(this.getProduct()))
				return true;
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		// Algorithm taken from "Joshua Bloch, Effective Java, 2nd Edition; Addison Wesley, 2008"
		int result = 17;
		result = 31*result+quantity;
		result = 31*result+product.hashCode();
		return result;
	}
	
	@Override
	public String toString()
	{
		return "Item("+quantity+" '"+product+"')";
	}
}
