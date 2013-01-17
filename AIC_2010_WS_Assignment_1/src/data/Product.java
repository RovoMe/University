package data;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Data-class representing a certain product.</p>
 * 
 * @author roman
 */
//the root element of our generated XML
@XmlRootElement ( name = "Product" )
// maps this class to a generated schema type
@XmlType( name = "ProductType", 
		propOrder={"id", "name", "singleUnitPrice"})
// bind all non-static, non-transient fields
// to XML unless annotated with @XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public class Product
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
			name = "singleUnitPrice", 
			nillable = false, 
			type = BigDecimal.class, 
			defaultValue = "null"
	)
	private BigDecimal singleUnitPrice = null;
	
	/**
	 * Instantiates the class.
	 */
	public Product()
	{
		
	}
	
	/**
	 * Sets the ID of this product. If there was defined an 
	 * ID before, no new ID will be set.
	 * @param id New ID if there was not set an ID before.
	 */
	public void setId(String id)
	{
		if (this.id == null)
			this.id = id;
	}
	
	/**
	 * Returns the ID of this product.
	 * @return Returns the ID of this product.
	 */
	public String getId()
	{
		return this.id;
	}
	
	/**
	 * Sets the name of this product.
	 * @param name The name of this product.
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Returns the name of this product.
	 * @return Returns the name of this product.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Sets the single unit price of this product.
	 * @param singleUnitPrice The unit price of this product.
	 */
	public void setSingleUnitPrice(BigDecimal singleUnitPrice)
	{
		this.singleUnitPrice = singleUnitPrice;
	}
	
	/**
	 * Returns the single unit price of this product.
	 * @return The single unit price of this product.
	 */
	public BigDecimal getSingleUnitPrice()
	{
		return this.singleUnitPrice;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;
		if (obj instanceof Product)
		{
			Product prod = (Product)obj;
			if (prod.getId().equals(this.getId()) && 
					prod.getName().equals(this.getName()) &&
					prod.getSingleUnitPrice().equals(this.getSingleUnitPrice()))
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
		result = 31*result+singleUnitPrice.hashCode();
		return result;
	}
	
	@Override
	public String toString()
	{
		return "Product("+id+": "+name+"; singleUnitPrice="+singleUnitPrice+")";
	}
}
