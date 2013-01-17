package data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Data-class representing an address.</p>
 * <p>{@link isShipping} defines if this address is the address to ship items to.</p>
 * @author roman
 *
 */
//the root element of our generated XML
@XmlRootElement ( name = "Addresses" )
// maps this class to a generated schema type
@XmlType( name = "AddressesType", 
		propOrder={"id", "street", "city", "house", "door",
		 "zipCode", "isShipping", "isBilling", "isOther"})
// bind all non-static, non-transient fields
// to XML unless annotated with @XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public class Addresses
{
	@XmlAttribute (
			name = "id", 
			required = true
	)
	private String id = null;
	@XmlElement (   
			name = "street", 
			nillable = false, 
			type = String.class, 
			required = true,
			defaultValue = "nill"
	)
	private String street = null;
	@XmlElement (   
			name = "city", 
			nillable = false, 
			type = String.class, 
			required = true,
			defaultValue = "nill"
	)
	private String city = null;
	@XmlElement (   
			name = "house", 
			nillable = true,  
			type = int.class, 
			required = false,
			defaultValue = "0"
	)
	private int house = 0;
	@XmlElement (   
			name = "door", 
			nillable = true, 
			type = int.class, 
			required = false,
			defaultValue = "0"
	)
	private int door = 0;
	@XmlElement (   
			name = "zipCode", 
			nillable = false, 
			type = String.class, 
			required = true,
			defaultValue = "nill"
	)
	private String zipCode = null;
	@XmlElement (   
			name = "isShipping", 
			nillable = false, 
			type = boolean.class, 
			defaultValue = "false"
	)
	private boolean isShipping = false;
	@XmlElement (   
			name = "isBilling", 
			nillable = true, 
			type = boolean.class, 
			required = false,
			defaultValue = "false"
	)
	private boolean isBilling = false;
	@XmlElement (   
			name = "isOther", 
			nillable = true,  
			type = boolean.class, 
			required = false,
			defaultValue = "false"
	)
	private boolean isOther = false;
	
	public Addresses()
	{
		
	}
	
	/**
	 * Sets the ID of this address. If this method was called before, 
	 * no new ID can be set.
	 * @param id New ID of this address if there was none before.
	 */
	public void setId(String id)
	{
		if (this.id == null)
			this.id = id;
	}
	
	/**
	 * Returns the ID of this address.
	 * @return Returns the ID of this address.
	 */
	public String getId()
	{
		return this.id;
	}
	
	/**
	 * Sets the street name of this address.
	 * @param street New street name of this address.
	 */
	public void setStreet(String street)
	{
		this.street = street;
	}
	
	/**
	 * Returns the street name of this address.
	 * @return Returns the street name of this address.
	 */
	public String getStreet()
	{
		return this.street;
	}
	
	/**
	 * Sets the city of this address.
	 * @param city New city of this address.
	 */
	public void setCity(String city)
	{
		this.city = city;
	}
	
	/**
	 * Returns the city of this address.
	 * @return Returns the city of this address.
	 */
	public String getCity()
	{
		return this.city;
	}
	
	/**
	 * Sets the house number of this address.
	 * @param house New house number of this address.
	 */
	public void setHouse(int house)
	{
		this.house = house;
	}
	
	/**
	 * Returns the house number of this address.
	 * @return Returns the house number of this address.
	 */
	public int getHouse()
	{
		return this.house;
	}
	
	/**
	 * Sets the door number of this address.
	 * @param door New door number of this address.
	 */
	public void setDoor(int door)
	{
		this.door = door;
	}
	
	/**
	 * Returns the door number of this address.
	 * @return Returns the door number of this address.
	 */
	public int getDoor()
	{
		return this.door;
	}
	
	/**
	 * Sets the zip code of this address.
	 * @param zipCode New zip code of this address.
	 */
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}
	
	/**
	 * Returns the zip code of this address.
	 * @return Returns the zip code of this address.
	 */
	public String getZipCode()
	{
		return this.zipCode;
	}
	
	/**
	 * Defines if this address should be used to ship items to. 
	 * @param isShipping If set to true, this address may receive 
	 * 		items from an order, otherwise this address will be 
	 * 		ignored for shipping.
	 */
	public void setIsShipping(boolean isShipping)
	{
		this.isShipping = isShipping;
	}
	
	/**
	 * Returns if this address allows shipment of items.
	 * @return true if this address may receive items from an 
	 * 		order, false otherwise.
	 */
	public boolean isShipping()
	{
		return this.isShipping;
	}
	
	/**
	 * Marks this address as willing to receive invoices.
	 * @param isBilling If set to true, this address may receive 
	 * 		invoices from executed orders, otherwise this address 
	 * 		will be ignored for receiving invoices.
	 */
	public void setIsBilling(boolean isBilling)
	{
		this.isBilling = isBilling;
	}
	
	/**
	 * Returns if this address allows receiving invoices.
	 * @return true if this address may receive invoices from an 
	 * 		order, false otherwise.
	 */
	public boolean isBilling()
	{
		return this.isBilling;
	}
	
	/**
	 * Sets if this address is not the main address.
	 * @param If this is not the main address call this method 
	 * 		with true, false otherwise.
	 */
	public void setIsOther(boolean isOther)
	{
		this.isOther = isOther;
	}
	
	/**
	 * Returns if this address is not the main address.
	 * @return true if there is another address defined as main 
	 * 		address, false if this is the main address.
	 */
	public boolean isOther()
	{
		return this.isOther;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;
		if (obj instanceof Addresses)
		{
			Addresses addr = (Addresses)obj;
			if (addr.getId().equals(this.getId()) && 
					addr.getStreet().equals(this.getStreet()) &&
					addr.getCity().equals(this.getCity()) &&
					addr.getHouse() == this.getHouse() &&
					addr.getDoor() == this.getDoor() &&
					addr.getZipCode().equals(this.getZipCode()) &&
					addr.isShipping() == this.isShipping() &&
					addr.isBilling() == this.isBilling() && 
					addr.isOther() == this.isOther())
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
		result = 31*result+street.hashCode();
		result = 31*result+city.hashCode();
		result = 31*result+house;
		result = 31*result+door;
		result = 31*result+zipCode.hashCode();
		result = 31*result+(isShipping ? 1 : 0);
		result = 31*result+(isBilling ? 1 : 0);
		result = 31*result+(isOther ? 1 : 0);
		return result;
	}
	
	@Override
	public String toString()
	{
		return "Address("+id+": "+street+"/"+house+"/"+door+", "+zipCode+" "+city+
		"; shipping="+isShipping+", billing="+isBilling+" other="+isOther+")";
	}
}
