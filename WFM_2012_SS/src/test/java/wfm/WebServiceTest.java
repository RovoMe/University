package wfm;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import junit.framework.Assert;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import wfm.AllServers;
import wfm.bean.BankAccount;
import wfm.bean.CreditCard;
import wfm.bean.Customer;
import wfm.ws.addressValidation.rest.AddressValidationRESTService;
import wfm.ws.bankTransfer.AccountNotFoundFault;
import wfm.ws.bankTransfer.BankTransferService;
import wfm.ws.bankTransfer.BankTransferValidationFault;
import wfm.ws.couponValidation.CouponValidationService;
import wfm.ws.creditCard.rest.CreditCardRESTService;

public class WebServiceTest
{
	private static AllServers server = null;
	
	@BeforeClass
	public static void init()
	{
		server = new AllServers();
		server.start();
	}
	
	@Test
	public void testAddressValidationWS() 
	{				
		// SOAP-Service
//		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//		factory.setServiceClass(AddressValidationService.class);
//		factory.setAddress("http://localhost:63081/AddressValidationService");
//		AddressValidationService addressValidationService = (AddressValidationService)factory.create();		
		
		// REST-Service
		// define a JSON provider and a mapping between REST and JSON namespace's
		java.util.List<JSONProvider<AddressValidationRESTService>> providers = new java.util.ArrayList<JSONProvider<AddressValidationRESTService>> ();
		JSONProvider<AddressValidationRESTService> jsonProvider = new JSONProvider<AddressValidationRESTService>();
		Map<String, String> map = new HashMap<String, String> ();
		map.put ( "http://rest.ws.wfm", "{}" );
		jsonProvider.setNamespaceMap ( map );
		providers.add ( jsonProvider );
		
		// Client setup programmatically
		JAXRSClientFactoryBean sf = new JAXRSClientFactoryBean();
		sf.setResourceClass(AddressValidationRESTService.class);
		sf.setAddress("http://localhost:63082");
		sf.setProvider(jsonProvider);
		
		BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
		JAXRSBindingFactory factory = new JAXRSBindingFactory();
		factory.setBus(sf.getBus());
		manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
		
		AddressValidationRESTService service = sf.create(AddressValidationRESTService.class);
		WebClient wc = sf.createWebClient();
		wc.accept(MediaType.APPLICATION_JSON);
				
		// shorter version but without interceptors		
//		AddressValidationRESTService service = org.apache.cxf.jaxrs.client.JAXRSClientFactory.create ("http://localhost:63082", AddressValidationRESTService.class, providers, true );
//		WebClient.client(service).accept(MediaType.APPLICATION_JSON);
		
		Customer customer;
		try 
		{
			customer = service.validateAddress("Max", "Muster", "Mustergasse 2", "Wien", "1090", "Austria", "1234567", "max@muster.at");
			Assert.assertNotNull(customer);
			Assert.assertEquals("Max", customer.getFirstName());
			Assert.assertEquals("Muster", customer.getLastName());
			Assert.assertNotNull(customer.getAddress());
			Assert.assertEquals("Mustergasse 2", customer.getAddress().getStreet());
			Assert.assertEquals("1090", customer.getAddress().getZip());
			Assert.assertEquals("Wien", customer.getAddress().getCity());
			Assert.assertEquals("Austria", customer.getAddress().getCountry());
			Assert.assertEquals("1234567", customer.getTelNo());
			Assert.assertEquals("max@muster.at", customer.getMailAddress());
			
		} 
		catch (WebApplicationException waEx)
		{
			Assert.fail(waEx.getLocalizedMessage());
			waEx.printStackTrace();
		}
		catch (Exception e) 
		{
			Assert.fail(e.getLocalizedMessage());
			e.printStackTrace();
		}
		customer = null;
		
		try
		{
			customer = service.validateAddress("Tom", "Test", "Unknown Street 1", "Nowhere", "100000", "Nomansland", "1234567", "tom@test.com");
			Assert.fail();
		} 
		catch (WebApplicationException waEx)
		{
			Assert.assertNull(customer);
			Assert.assertNotNull(waEx);
			Assert.assertEquals(404, waEx.getResponse().getStatus());
			Assert.assertEquals("text/html", waEx.getResponse().getMetadata().get("content-type").get(0).toString());
			Assert.assertEquals("<h3>Invalid address! Address could not be found</h3>", waEx.getMessage());
		}
		catch (Exception e) 
		{
			Assert.fail(e.getLocalizedMessage());
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testCouponWS() 
	{
		// This method has problems with exceptions - better use the second approach
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(CouponValidationService.class);
		factory.setAddress("http://localhost:63081/CouponValidationService");
		CouponValidationService couponValidationService = (CouponValidationService)factory.create();
		
		try 
		{
			Double priceReduction = couponValidationService.validateCoupon("abcd");
			Assert.assertEquals(20.0, priceReduction);
			
			priceReduction = couponValidationService.validateCoupon("abcde");
			Assert.assertEquals(0.0, priceReduction);
		} 
		catch (Exception e) 
		{
			Assert.fail(e.getLocalizedMessage());
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testBankTransferWS()
	{
		// This method has problems with exceptions - better use the second approach
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInFaultInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(BankTransferService.class);
		factory.setAddress("http://localhost:63084/BankTransferService");
		BankTransferService bankTransferService = (BankTransferService)factory.create();
		
		BankAccount account;
		try 
		{
			account = bankTransferService.validateBankAccount("Bank Austria", "12000", "12345678901", "Max Muster");
			Assert.assertNotNull(account);
			Assert.assertEquals("Bank Austria", account.getBankName());
			Assert.assertEquals("12000", account.getBankCode());
			Assert.assertEquals("12345678901", account.getAccountNumber());
			Assert.assertEquals("Max Muster", account.getHoldersName());
		} 
		catch (BankTransferValidationFault anfEx)
		{
			Assert.fail(anfEx.getLocalizedMessage());
			anfEx.printStackTrace();
		}
		catch (Exception e) 
		{
			Assert.fail(e.getLocalizedMessage());
			e.printStackTrace();
		}
		account = null;
		
		String accountNo = "000000000000";
		try 
		{
			
			account = bankTransferService.validateBankAccount("Greedy Bank", "00000", accountNo, "Tom Test");
			Assert.fail();
		} 
		catch (BankTransferValidationFault anfEx)
		{
			Assert.assertNotNull(anfEx);
			Assert.assertEquals("No account information found for account "+accountNo, anfEx.getLocalizedMessage());
		}
		catch (Exception e) 
		{
			Assert.fail(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreditCardValidation()
	{
		// connect to the REST AddressValidationRESTService to check if the entered creditcard is valid
		JAXRSClientFactoryBean sf = new JAXRSClientFactoryBean();
		sf.setResourceClass(CreditCardRESTService.class);
		sf.setAddress("http://localhost:63083");
		
		// bindings
		BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
		JAXRSBindingFactory factory = new JAXRSBindingFactory();
		factory.setBus(sf.getBus());
		manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
		
		// retrieve the service
		CreditCardRESTService creditCardService = sf.create(CreditCardRESTService.class);
		// restrict content-type to be of type "application/json"
		WebClient wc = sf.createWebClient();
		wc.accept(MediaType.APPLICATION_JSON);

		CreditCard creditCard;
		try
		{
			creditCard = creditCardService.validateCreditCard("123456", "Max Muster", "0115", "1234", "Visa");
			Assert.assertNotNull(creditCard);
			Assert.assertEquals("123456", creditCard.getCardNumber());
			Assert.assertEquals("Max Muster", creditCard.getNameOfOwner());
			Assert.assertEquals("0115", creditCard.getExpirationDate());
			Assert.assertEquals("1234", creditCard.getCVV2Code());
			Assert.assertEquals("Visa", creditCard.getCardType());
		}
		catch(WebApplicationException waEx)
		{
			Assert.fail(waEx.getLocalizedMessage());
			waEx.printStackTrace();
		}
		catch (Exception e)
		{
			Assert.fail(e.getLocalizedMessage());
			e.printStackTrace();
		}
		creditCard = null;
		
		try
		{
			creditCard = creditCardService.validateCreditCard("0123456", "Tom Test", "1212", "1234", "Mastercard");
			Assert.fail();
		}
		catch(WebApplicationException waEx)
		{
			Assert.assertNotNull(waEx);
			Assert.assertEquals(404, waEx.getResponse().getStatus());
			Assert.assertEquals("text/html", waEx.getResponse().getMetadata().get("content-type").get(0).toString());
			Assert.assertEquals("<h3>Invalid creditcard! Creditcard could not be found</h3>", waEx.getLocalizedMessage());
		}
		catch (Exception e)
		{
			Assert.fail(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPaymentService()
	{
		JaxWsProxyFactoryBean wsFactory = new JaxWsProxyFactoryBean();
		wsFactory.setServiceClass(BankTransferService.class);
		wsFactory.setAddress("http://localhost:63084/BankTransferService");
		BankTransferService bankTransferService = (BankTransferService)wsFactory.create();
		
		JAXRSClientFactoryBean sf = new JAXRSClientFactoryBean();
		sf.setResourceClass(CreditCardRESTService.class);
		sf.setAddress("http://localhost:63083");
		
		// bindings
		BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
		JAXRSBindingFactory factory = new JAXRSBindingFactory();
		factory.setBus(sf.getBus());
		manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
		
		// retrieve the service
		CreditCardRESTService creditCardService = sf.create(CreditCardRESTService.class);
		// restrict content-type to be of type "application/json"
		WebClient wc = sf.createWebClient();
		wc.accept(MediaType.APPLICATION_JSON);
		
		// valid transactions
		try
		{
			boolean success = creditCardService.doPayment("123456", "Visa", 100.);
			Assert.assertTrue(success);
			
			success = bankTransferService.doPayment("12345678901", "12000", 100.);
			Assert.assertTrue(success);
		}
		catch (AccountNotFoundFault anfEx)
		{
			Assert.fail(anfEx.getLocalizedMessage());
			anfEx.printStackTrace();
		}
		catch (WebApplicationException waEx)
		{
			Assert.fail(waEx.getLocalizedMessage());
			waEx.printStackTrace();
		}
		catch (Exception e)
		{
			Assert.fail(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		// balance exceeded
		try
		{
			boolean success = creditCardService.doPayment("123456", "Visa", 300.);
			Assert.assertFalse(success);
			
			success = bankTransferService.doPayment("12345678901", "12000", 450.);
			Assert.assertFalse(success);
		}
		catch (AccountNotFoundFault anfEx)
		{
			Assert.fail(anfEx.getLocalizedMessage());
			anfEx.printStackTrace();
		}
		catch (WebApplicationException waEx)
		{
			Assert.fail(waEx.getLocalizedMessage());
			waEx.printStackTrace();
		}
		catch (Exception e)
		{
			Assert.fail(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		// not existing accounts
		try
		{
			creditCardService.doPayment("1234567", "Mastercard", 100.);
			Assert.fail();
		}
		catch (WebApplicationException waEx)
		{
			Assert.assertNotNull(waEx);
			Assert.assertEquals("<h3>No account found for this payment method</h3>", waEx.getLocalizedMessage());
		}
		catch (Exception e)
		{
			Assert.fail(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		try
		{			
			bankTransferService.doPayment("123456789012", "32000", 100.);
			Assert.fail();
		}
		catch (AccountNotFoundFault anfEx)
		{
			Assert.assertNotNull(anfEx);
			Assert.assertEquals("No account found for this payment method", anfEx.getLocalizedMessage());
		}
		catch (Exception e)
		{
			Assert.fail(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void clean()
	{
		if (server != null)
			server.stop();
	}
}
