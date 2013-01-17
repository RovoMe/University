package dst1.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Embeddable
public class Address implements Serializable
{
	@Transient
	private static final long serialVersionUID = 4307333277419562947L;
	private long id;
	private String street;
	private String city;
	private String zipCode;
//	private Person person;

	public Address()
	{
		
	}
	
	public Address(String street, String city, String zipCode)
	{
		this.setStreetName(street);
		this.setCityName(city);
		this.setZipCode(zipCode);
	}
	
	@Id
	@GeneratedValue
	public long getId()
	{
		return this.id;
	}
	
	public void setId(long id)
	{
		this.id = id;
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
	
//	@OneToOne
//	public Person getPerson()
//	{
//		return this.person;
//	}
//	
//	public void setPerson(Person person)
//	{
//		this.person = person;
//	}
}
