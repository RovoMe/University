package wfm.bean;

import java.io.Serializable;

import javax.inject.Named;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="account")
@Named
public class BankAccount implements Serializable
{
	private static final long serialVersionUID = -4074847517902424325L;
	private String bankName = "";
	private String bankCode = "";
	private String holdersName = "";
	private String accountNumber = "";
	private Double balance = 0d;
	
	public BankAccount()
	{
		
	}
	
	public BankAccount(String bankName, String bankCode, String accountNumber, String holdersName)
	{
		this.bankName = bankName;
		this.bankCode = bankCode;
		this.accountNumber = accountNumber;
		this.holdersName = holdersName;
	}
	
	public BankAccount(String bankName, String bankCode, String accountNumber, String holdersName, Double balance)
	{
		this.bankName = bankName;
		this.bankCode = bankCode;
		this.accountNumber = accountNumber;
		this.holdersName = holdersName;
		if (balance >= 0d)
			this.balance = balance;
	}
	
	public String getBankName() { return this.bankName; }
	public String getBankCode() { return this.bankCode; }
	public String getHoldersName() { return this.holdersName; }
	public String getAccountNumber() { return this.accountNumber; }
	
	public void setBankName(String bankName) { this.bankName = bankName; }
	public void setBankCode(String bankCode) { this.bankCode = bankCode; }
	public void setHoldersName(String holdersName) { this.holdersName = holdersName; }
	public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
	
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
		return this.getBankCode()+", "+this.getBankCode()+", "+this.getAccountNumber()+", "+this.getHoldersName()+", "+this.getBalance().toString();
	}
}
