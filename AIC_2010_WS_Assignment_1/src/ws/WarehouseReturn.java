package ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>This class is a helper class for the {@link WarehouseService} 
 * to return more then one value</p>
 * 
 * @author roman
 */
@XmlRootElement( name = "WarehouseReturn" )
@XmlType( name = "WarehouseReturnType" )
@XmlAccessorType( XmlAccessType.FIELD )
public class WarehouseReturn
{
	@XmlElement ( name = "available",
			nillable = false,
			required = true,
			type = boolean.class,
			defaultValue = "false" )
	private boolean available = false;
	@XmlElement ( name = "estimatedDeliveryTime",
			nillable = false,
			required = true,
			type = long.class,
			defaultValue = "0")
	private long estimatedDeliveryTime = 0;
	
	/**
	 * Initializes the class
	 */
	public WarehouseReturn()
	{
		
	}
	
	/**
	 * Sets if a product is available or not.
	 * @param available true if a product is available, 
	 * 		false otherwise
	 */
	public void setAvailable(boolean available)
	{
		this.available = available;
	}
	
	/**
	 * Returns if a product is available or not.
	 * @return true if a product is available, 
	 * 		false otherwise.
	 */
	public boolean isAvailable()
	{
		return this.available;
	}
	
	/**
	 * Sets the estimated delivery time for a product.
	 * @param estimatedDeliveryTime Indicates the estimated delivery 
	 * 		time for a product.
	 */
	public void setEstimatedDeliveryTime(long estimatedDeliveryTime)
	{
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}
	
	/**
	 * Returns the estimated delivery time for a product.
	 * @return Returns the estimated delivery time for a product.
	 */
	public long getEstimatedDeliveryTime()
	{
		return this.estimatedDeliveryTime;
	}
}
