package wfm.ws.creditCard.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import wfm.bean.CreditCard;

@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_HTML, MediaType.APPLICATION_XHTML_XML})
@Path("/creditCardValidation")
public interface CreditCardRESTService
{		
	
	@GET
	@Path("validateCreditCard")
	public CreditCard validateCreditCard(
			@QueryParam("cardNumber") String cardNumber,
			@QueryParam("nameOfOwner") String nameOfOwner,
			@QueryParam("expirationDate") String expirationDate,
			@QueryParam("CVV2Code") String CVV2Code,
			@QueryParam("cardType") String cardType)
				throws WebApplicationException;
	
	@GET
	@Path("doPayment")
	public boolean doPayment(
			@QueryParam("cardNo") String cardNo,
			@QueryParam("cardType") String cardType,
			@QueryParam("amount") Double amount) 
				throws WebApplicationException;
}
