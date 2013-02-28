package wfm.bean;

import java.io.Serializable;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
@Named
public class Product implements Serializable
{
	private static final long serialVersionUID = 2812226321508646172L;
	
	private String id;
	private String name;
	private Double price;
	
	public String getId() { return this.id; }
	public String getName() { return this.name; }
	public Double getPrice() { return this.price; }
	
	public void setId(String id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setPrice(Double price) { this.price = price; }
}
