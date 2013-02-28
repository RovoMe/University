package wfm.ws.creditCard.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import org.apache.log4j.Logger;
import wfm.bean.CreditCard;


public class CreditCardRESTServiceImpl implements CreditCardRESTService
{
	private static Logger logger = Logger.getLogger(CreditCardRESTServiceImpl.class);
	private List<CreditCard> knownCreditCards = new ArrayList<CreditCard>();
	
	public CreditCardRESTServiceImpl()
	{
		this.knownCreditCards.add(new CreditCard("123456", "Max Muster" , "0115", "1234", "Visa", 200d));
		this.knownCreditCards.add(new CreditCard("123457", "Maier", "0115", "1234",  "Visa", 50d));
		this.knownCreditCards.add(new CreditCard("123458", "Franz Huber", "0115", "1234",  "Visa", 350d));
		this.knownCreditCards.add(new CreditCard("123459", "Eva Trost", "0115", "1234",  "Visa", 13.5d));
		this.knownCreditCards.add(new CreditCard("123460", "Karl Franz", "0115", "1234",  "Visa", 6000d));
		this.knownCreditCards.add(new CreditCard("123461", "Sabine Lechner", "0115", "1234",  "Visa", 150d));
	}			
	
	@Override
	public CreditCard validateCreditCard(String cardNumber, String nameOfOwner, String expirationDate, String CVV2Code,String cardType) throws WebApplicationException
	{		
		for (CreditCard creditcard : this.knownCreditCards)
		
		{
			if (creditcard.getCardNumber().equals(cardNumber) &&
				creditcard.getNameOfOwner().equals(nameOfOwner) &&
				creditcard.getExpirationDate().equals(expirationDate) &&
				creditcard.getCVV2Code().equals(CVV2Code) &&
				creditcard.getCardType().equals(cardType))
			{
				logger.info("Credit Card verified successfully");
				return new CreditCard(cardNumber, nameOfOwner, expirationDate, CVV2Code, cardType);
			}
		}
		logger.info("Credit Card not verified correctly");
		ResponseBuilder builder = Response.status(Status.NOT_FOUND);
		builder.type("text/html");
		builder.entity("<h3>Invalid creditcard! Creditcard could not be found</h3>");
		throw new WebApplicationException(builder.build());
	}
	
	@Override
	public boolean doPayment(String cardNo, String cardType, Double amount) throws WebApplicationException
	{
		for (CreditCard account : this.knownCreditCards)
		{
			if(account.getCardNumber().equals(cardNo) && account.getCardType().equals(cardType))
			{
				logger.info("Credit Card-Balance: "+account.getBalance()+", required payment: "+amount);
				return account.withdraw(amount);
			}
		}
		logger.info("Wrong credit card informatio provided");
		ResponseBuilder builder = Response.status(Status.NOT_FOUND);
		builder.type("text/html");
		builder.entity("<h3>No account found for this payment method</h3>");
		throw new WebApplicationException(builder.build());
	}
}
