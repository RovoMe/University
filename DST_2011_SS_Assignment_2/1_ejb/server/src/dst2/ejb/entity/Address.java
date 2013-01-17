package dst2.ejb.entity;

import javax.persistence.*;

@Embeddable
public class Address
{
	private String street;
	private String city;
	private String zipCode;

	public Address()
	{
		
	}
	
	public Address(String street, String city, String zipCode)
	{
		this.setStreetName(street);
		this.setCityName(city);
		this.setZipCode(zipCode);
	}
	
	public String getStreetName()
	{
		return street;
	}
	
	public void setStreetName(String street)
	{
		this.street = street;
	}
	
	public String getCityName()
	{
		return city;
	}
	
	public void setCityName(String city)
	{
		this.city = city;
	}
	
	public String getZipCode()
	{
		return this.zipCode;
	}
	
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}
}
