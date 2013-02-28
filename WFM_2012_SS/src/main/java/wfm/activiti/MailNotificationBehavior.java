package wfm.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

import wfm.bean.Address;
import wfm.bean.BankAccount;
import wfm.bean.CreditCard;
import wfm.bean.Customer;
import wfm.mail.MailService;
import wfm.mail.MailServiceImpl;

public class MailNotificationBehavior implements JavaDelegate
{

	private static Logger LOGGER = Logger.getLogger(MailNotificationBehavior.class);
	private Customer customer = null;
	private StringBuilder message = null;
	private String paymentMethod = null;
	private CreditCard card = null;
	private BankAccount account = null;
	private Double price = 0.0;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception 
	{
		LOGGER.info("Sending mail");
		
		customer = (Customer) execution.getVariable("customer");
		buildMessage(String.format("Dear Mr./Ms./Mrs. %s %s,\n\n", customer.getFirstName(), customer.getLastName()));
		buildMessage("this is an automatically generated order notification.\n\n");
		Address address = customer.getAddress();
		buildMessage(String.format("Delivery address: %s, %s - %s, %s\n\n", address.getStreet(), address.getZip(),
				address.getCity(), address.getCountry()));

		paymentMethod = (String) execution.getVariable("paymentMethod");
		LOGGER.debug("Payment method: " + paymentMethod);

		if (paymentMethod.equals("creditCardMethod")) {
			buildMessage("Chosen payment method: Credit card\n");
			card = (CreditCard) execution.getVariable("creditCard");
			buildMessage(String.format("Card type: %s\nCard number: %s\nExpiration date: %s\nCard owner: %s\n\n",
					card.getCardType(), maskSensitiveInformation(card.getCardNumber()), card.getExpirationDate(),
					card.getNameOfOwner()));
		} else {
			buildMessage("Chosen payment method: Bank transfer\n");
			account = (BankAccount) execution.getVariable("BankAccount");
			buildMessage(String.format("Bank: %s\nBank code: %s\nAccount number %s\nAccount owner: %s\n\n",
					account.getBankName(), account.getBankCode(), maskSensitiveInformation(account.getAccountNumber()),
					account.getHoldersName()));
		}
		
		price = (Double) execution.getVariable("price");
		buildMessage("Price: " + price + "\n\n");

		buildMessage("Best regards,\nWFM-Team");
		
		initializeMailService().sendMessage();
	}

	private MailService initializeMailService() {
		MailService mailService = new MailServiceImpl();
		mailService.setRecipient(customer.getMailAddress());
		mailService.setMessage(message.toString());
		return mailService;
	}

	private void buildMessage(String messagePart) {
		if (message == null) {
			message = new StringBuilder();
		}
		message.append(messagePart);
	}

	private String maskSensitiveInformation(String sensitive) {
		return sensitive.substring(0, sensitive.length() - 5).replaceAll(".", "x")
				+ sensitive.substring(sensitive.length() - 5, sensitive.length());
	}

}
