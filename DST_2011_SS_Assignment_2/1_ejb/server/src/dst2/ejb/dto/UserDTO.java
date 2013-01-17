package dst2.ejb.dto;

import java.io.Serializable;

public class UserDTO implements Serializable
{
	private static final long serialVersionUID = -7379348887995944997L;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String streetName;
	private String city;
	private String zipCode;
	private String accountNo;
	private String bankCode;
	
	public UserDTO()
	{
		
	}
	
	public UserDTO(String userName, String password, String firstName, String lastName, 
			String streetName, String city, String zipCode, String accountNo, String bankCode)
	{
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetName = streetName;
		this.city = city;
		this.zipCode = zipCode;
		this.accountNo = accountNo;
		this.bankCode = bankCode;
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public String getStreetName()
	{
		return this.streetName;
	}
	
	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}
	
	public String getCity()
	{
		return this.city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getZIPCode()
	{
		return this.zipCode;
	}
	
	public void setZIPCode(String zipCode)
	{
		this.zipCode = zipCode;
	}
	
	public String getAccountNo()
	{
		return this.accountNo;
	}
	
	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}
	
	public String getBankCode()
	{
		return this.bankCode;
	}
	
	public void setBankCode(String bankCode)
	{
		this.bankCode = bankCode;
	}
}
