package wfm.bean;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class ItemEntry implements Serializable
{
	private static final long serialVersionUID = -7708647775172041528L;
	private Product product = null;
	private int quantity = 0;
	
	public ItemEntry()
	{
		
	}
	
	public Product getProduct() { return this.product; }
	public int getQuantity() { return this.quantity; }
	
	public void setProduct(Product product) { this.product = product; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
}
