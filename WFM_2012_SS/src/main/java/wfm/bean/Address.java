package wfm.bean;

import java.io.Serializable;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Address")
@Named
public class Address implements Serializable
{
	private static final long serialVersionUID = -3450110429324400066L;
	private String street = "";
	private String city = "";
	private String zip = "";
	private String country = "";
	
	public Address() {}
	public Address(String street, String city, String zip, String country)
	{
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}
	
	// Getter Methods
	public String getStreet() { return this.street; }
	public String getCity() { return this.city; }
	public String getZip() { return this.zip; }
	public String getCountry() { return this.country; }
	
	// Setter-Methods
	public void setStreet(String street) { this.street = street; }
	public void setCity(String city) { this.city = city; }
	public void setZip(String zip) { this.zip = zip; }
	public void setCountry(String country) { this.country = country; }
	
	public String toString()
	{
		return this.street+", "+this.zip+", "+this.city+", "+this.country;
	}
}
