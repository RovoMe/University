package wfm.ws.bankTransfer;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

import wfm.bean.BankAccount;

public class BankTransferServiceImpl implements BankTransferService
{
	private static Logger logger = Logger.getLogger(BankTransferService.class);
	private Map<String, BankAccount> bankAccounts;
	
	public BankTransferServiceImpl()
	{
		bankAccounts = new HashMap<String, BankAccount>();
		bankAccounts.put("12345678901", new BankAccount("Bank Austria", "12000", "12345678901", "Max Muster", 500d));
		bankAccounts.put("12345678902", new BankAccount("BAWAG", "14000", "12345678902", "Tom Test", 120d));
	}
	
	@Override
	public BankAccount validateBankAccount(String bankName, String bankCode, String bankAccountNumber, String bankAccountHoldersName) throws BankTransferValidationFault 
	{
		BankAccount account = bankAccounts.get(bankAccountNumber);
		if (account != null)
		{
			if (account.getBankName().equals(bankName) &&
					account.getBankCode().equals(bankCode) &&
					account.getHoldersName().equals(bankAccountHoldersName))
				return account;
		}
		throw new BankTransferValidationFault("No account information found for account "+bankAccountNumber);
	}

	@Override
	public boolean doPayment(String accountNo, String bankCode,	Double amount) throws AccountNotFoundFault 
	{
		for (BankAccount account : this.bankAccounts.values())
		{
			if(account.getAccountNumber().equals(accountNo) && account.getBankCode().equals(bankCode))
			{
				logger.info("Bank-Balance: "+account.getBalance()+", required payment: "+amount);
				return account.withdraw(amount);
			}
		}
		throw new AccountNotFoundFault("No account found for this payment method");
	}
}
