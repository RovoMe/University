package wfm.bean;

import java.io.Serializable;

import javax.inject.Named;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="account")
@Named
public class CreditCard implements Serializable
{
	private static final long serialVersionUID = -4340132397514846599L;
	private String cardNumber = "";
	private String cardType = "";
	private String nameOfOwner = "";
	private String expirationDate = "";
	private String CVV2Code = "";
	private Double balance = 0D;

	public CreditCard() 
	{ 

	}

	public CreditCard(String cardNumber, String nameOfOwner, String expirationDate, String CVV2Code, String cardType )
	{
		this.cardNumber = cardNumber;
		this.nameOfOwner= nameOfOwner;
		this.expirationDate=expirationDate;
		this.CVV2Code=CVV2Code;
		this.cardType = cardType;
	}
	
	public CreditCard(String cardNumber, String nameOfOwner, String expirationDate, String CVV2Code, String cardType, Double balance)
	{
		this.cardNumber = cardNumber;
		this.nameOfOwner= nameOfOwner;
		this.expirationDate=expirationDate;
		this.CVV2Code=CVV2Code;
		this.cardType = cardType;
		if (balance >= 0d)
			this.balance = balance;
	}
	
	public String getCardNumber() { return this.cardNumber; }
	public String getNameOfOwner() { return this.nameOfOwner; }
	public String getExpirationDate() { return this.expirationDate; }
	public String getCVV2Code() { return this.CVV2Code; }
	public String getCardType() { return this.cardType; }
	
	public void setCardNumber(String cardNumber) {  this.cardNumber = cardNumber; }
	public void setNameOfOwner(String nameOfOwner) {  this.nameOfOwner = nameOfOwner; }
	public void setExpirationDate(String expirationDate) {  this.expirationDate = expirationDate; }
	public void setCVV2Code(String CVV2Code) {  this.CVV2Code = CVV2Code; }
	public void setCardType(String cardType) {  this.cardType = cardType; }
	
	public void setBalance(Double balance) 
	{
		if (balance > 0d)
			this.balance = balance; 
	}
	public Double getBalance() { return this.balance; }
	
	public boolean withdraw(Double amount)
	{
		if (this.balance < amount)
			return false;
		this.balance = this.balance - amount;
		return true;
	}
	
	public String toString()
	{
		return "Name of Owner: "+this.getNameOfOwner()+", Creditcard Type: "+this.getCardType()+", Cardnumber: "+this.getCardNumber()+", Expiration Date: "+this.getExpirationDate()+", CV2Code: "+this.getCVV2Code()+", Balance: "+this.getBalance().toString();
	}
}
