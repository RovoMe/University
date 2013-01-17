package ws.impl;

/**
 * <p>Helper-class to store extended details for products in a 
 * hashtable</p>
 * 
 * @author roman
 */
public class ProductDetails
{
	private boolean available = false;
	private long deliveryTime = 0L;
	private int amount = 0;
	
	/**
	 * Instantiates the class.
	 */
	public ProductDetails()
	{
		
	}
	
	/**
	 * Sets the delivery time for a certain product.
	 * @param deliveryTime Delivery time for a certain product.
	 */
	public void setDeliveryTime(long deliveryTime)
	{
		this.deliveryTime = deliveryTime;
	}
	
	/**
	 * Returns the delivery time for a certain product.
	 * @return Delivery time for a certain product.
	 */
	public long getDeliveryTime()
	{
		return this.deliveryTime;
	}
	
	/**
	 * Marks if a product is available (true) or not (false)
	 * @param available true if this product is available, false 
	 * 		otherwise.
	 */
	public void setIsAvailable(boolean available)
	{
		this.available = available;
	}
	
	/**
	 * Returns if a product is available or not.
	 * @return true if this product is available, false 
	 * 		otherwise.
	 */
	public boolean isAvailable()
	{
		return available;
	}
	
	/**
	 * Returns if a product is available with the according amount.
	 * @param amount Represents the quantity of a product that 
	 * 		currently has to be in the warehouse
	 * @return true if this product is available with the 
	 * 		certain amount, false otherwise.
	 */
	public boolean isAvailable(int amount)
	{
		if (this.amount > amount)
			this.available = true;
		else
			this.available = false;
		return available;
	}
	
	/**
	 * Sets the amount of a certain product currently stored in the 
	 * 		warehouse.
	 * @param amount Quantity of currently available products in the 
	 * 		warehouse.
	 */
	public void setAmount(int amount)
	{
		this.amount=amount;
	}
	
	/**
	 * Returns the quantity of a currently available product in the 
	 * 		warehouse.
	 * @return Returns the quantity of a currently available product 
	 * 		in the warehouse.
	 */
	public int getAmount()
	{
		return this.amount;
	}
	
}
