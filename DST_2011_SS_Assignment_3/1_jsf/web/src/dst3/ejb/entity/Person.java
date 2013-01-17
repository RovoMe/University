package dst3.ejb.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Person
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	protected String firstname;
	protected String lastname;
	@Embedded
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ADDRESS_ID")
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
	
	public Address getAddress()
	{
		return this.address;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
}
