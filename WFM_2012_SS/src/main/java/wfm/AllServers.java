package wfm;

import wfm.ws.AddressValidationServiceServer;
import wfm.ws.CouponValidationServiceServer;
import wfm.ws.BankTransferServiceServer;
import wfm.ws.CreditCardServiceServer;

/**
 * Hello world!
 *
 */
public class AllServers 
{
	private AddressValidationServiceServer adderssValidationServer = null;
	private CouponValidationServiceServer couponValidationServer = null;
	private CreditCardServiceServer creditCardServer = null;
	private BankTransferServiceServer bankTransferServer = null;
	
    public AllServers()
    {
        this.adderssValidationServer = new AddressValidationServiceServer();
        this.couponValidationServer = new CouponValidationServiceServer();
        this.creditCardServer = new CreditCardServiceServer();
        this.bankTransferServer = new BankTransferServiceServer();
    }
    
    public void start()
    {
    	this.adderssValidationServer.start();
    	this.couponValidationServer.start();
    	this.creditCardServer.start();
    	this.bankTransferServer.start();
    	
        // Test-URIs
        // http://localhost:63082/addressValidation/validateAddress?_wadl
        // http://localhost:63082/addressValidation/validateAddress?firstName=Max&lastName=Muster&street=Mustergasse%202&city=Wien&zip=1090&country=Austria&telNo=1234567
        // http://localhost:63081/CouponValidationService?wsdl
        // http://localhost:63083/creditCard/validateCreditCard?_wadl
        // http://localhost:63083/creditCard/validateCreditCard?cardNumber=123456&nameOfOwner=Maier&expirationDate=0115&CVV2Code=1234&cardType=Visa
        // http://localhost:63084/BankTransferService?wsdl
    }
    
    public void stop()
    {
    	this.adderssValidationServer.stop();
    	this.couponValidationServer.stop();
    	this.creditCardServer.stop();
    	this.bankTransferServer.stop();
    }
    
    public static void main(String ... args)
    {
    	new AllServers().start();
    }
}
