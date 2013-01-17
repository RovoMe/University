package dst1.model;

import java.io.Serializable;

import javax.persistence.*;

@MappedSuperclass
public abstract class Person implements Serializable
{
	@Transient
	private static final long serialVersionUID = -5648319827112058935L;
	private long id;
	protected String firstname;
	protected String lastname;
	@Embedded
	protected Address address;
	
	public Person()
	{
		
	}
	
	public Person(String firstName, String lastName, Address address)
	{
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAddress(address);
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
	
	public String getFirstName()
	{
		return this.firstname;
	}
	
	public void setFirstName(String firstname)
	{
		this.firstname = firstname;
	}
	
	public String getLastName()
	{
		return this.lastname;
	}
	
	public void setLastName(String lastname)
	{
		this.lastname = lastname;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ADDRESS_ID")
	public Address getAddress()
	{
		return this.address;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
}
