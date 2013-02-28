package wfm.bean;

import java.io.Serializable;

import javax.inject.Named;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Named
public class Customer implements Serializable
{
	private static final long serialVersionUID = -4340132397514846599L;
	private String firstName = "";
	private String lastName = "";
	private Address address = new Address();
	private String telNo = "";
	private String mailAddress = "";
	
	public Customer() {}
	public Customer(String firstName, String lastName, Address address, String telNo, String mailAddress)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.telNo = telNo;
		this.mailAddress = mailAddress;
	}
	
	public Customer(String firstName, String lastName, String street, String city, String zip, String country, String telNo,  String mailAddress)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = new Address(street, city, zip, country);
		this.telNo = telNo;
		this.mailAddress = mailAddress;
	}
	
	public String getFirstName() { return this.firstName; }
	public String getLastName() { return this.lastName; }
	public Address getAddress() { return this.address; }
	public String getTelNo() { return this.telNo; }
	public String getMailAddress() { return this.mailAddress; }
	
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setAddress(Address address) { this.address = address; }
	public void setAddress(String street, String city, String zip, String country) { this.address = new Address(street, city, zip, country); }
	public void setTelNo(String telNo) { this.telNo = telNo; }
	public void setMailAddress(String mailAddress) { this.mailAddress = mailAddress; }
	
	public String toString()
	{
		return "Name: "+this.firstName+" "+this.lastName+", Address: "+this.getAddress()+", TelNo: "+this.telNo+", Email Address: "+this.mailAddress;
	}
}
