
import java.math.BigDecimal;

import javax.xml.ws.soap.SOAPFaultException;

import junit.framework.JUnit4TestAdapter;
import ws.*;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.*;
import static org.junit.Assert.*;
import data.*;

public class JUnitTest 
{
	public static String SUCCESS = "SUCCEEDED";
	public static String FAILURE = "FAILED";
	public static String FORMAT = "%-100s%10s\n";
	
	private static ShippingService shippingService = null;
	private static SupplierService supplierService = null;
	private static WarehouseService warehouseService = null;
	private static CustomerManagementServiceSOAP customerService = null;
	
	@BeforeClass
	public static void initialize() throws Exception
	{		
		String output = "";
		output += String.format(FORMAT, "Initalizing System ...", "");
		JaxWsProxyFactoryBean shipperFactory = new JaxWsProxyFactoryBean();
		JaxWsProxyFactoryBean supplierFactory = new JaxWsProxyFactoryBean();
		JaxWsProxyFactoryBean warehouseFactory = new JaxWsProxyFactoryBean();
		JaxWsProxyFactoryBean customerFactory = new JaxWsProxyFactoryBean();
//		wsFactory.getInFaultInterceptors().add(new LoggingInInterceptor());
//		wsFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		
		shipperFactory.setServiceClass(ShippingService.class);
		shipperFactory.setAddress("http://localhost:8088/ShippingService");
		shippingService = (ShippingService)shipperFactory.create();
		output += String.format(FORMAT, "   initializing ShippingService", "DONE");

		supplierFactory.setAddress("http://localhost:8088/Supplier1");
		supplierFactory.setServiceClass(SupplierService.class);
		supplierService = (SupplierService)supplierFactory.create();
		output += String.format(FORMAT, "   initializing SupplierService", "DONE");

		warehouseFactory.setAddress("http://localhost:8088/WarehouseService");
		warehouseFactory.setServiceClass(WarehouseService.class);
		warehouseService = (WarehouseService)warehouseFactory.create();
		output += String.format(FORMAT, "   initializing WarehouseService", "DONE");
		
		customerFactory.setAddress("http://localhost:8088/CustomerManagementService");
		customerFactory.setServiceClass(CustomerManagementServiceSOAP.class);
		customerService = (CustomerManagementServiceSOAP)customerFactory.create();
		output += String.format(FORMAT, "   initializing CustomerManagementService", "DONE");
		
		System.out.print(output);
	}

	@Test
	public void doShippmentTest()
	{
		String output = "";
		output += String.format(FORMAT, "Starting shippment-Test","");
		
		assertTrue(shippingService != null);
		
		Item i = new Item();
		i.setQuantity(1);
		
		Product p = new Product();		
		p.setId("978-0-321-35668-0");
		p.setName("Effective Java - Second Edition");
		p.setSingleUnitPrice(new BigDecimal(35.95));
		p.setItem(i);
		
		i.setProduct(p);
		
		Addresses a = new Addresses();
		a.setId("Addr3");
		a.setStreet("Teststraße 9");
		a.setHouse(9);
		a.setDoor(9);
		a.setCity("Wien");
		a.setZipCode("1090");
		a.setIsBilling(true);
		a.setIsShipping(true);
		a.setIsOther(false);
			
		String txt = "   Test if invalid items got passed         ";
		try
		{
			shippingService.ship_items(new Item[] {}, a);
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should have gotten IllegalArgumentException - empty item-list provided");
		}
		catch (IllegalArgumentException e)
		{
			output += String.format (FORMAT, txt, SUCCESS);
			assertEquals(e.getMessage(),"No items to ship specified");
		}
		
		txt = "   Test if no valid address is passed         ";
		try
		{
			shippingService.ship_items(new Item[] {i}, null);
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should have gotten IllegalArgumentException - product to ship is null");
		}
		catch (IllegalArgumentException e)
		{
			output += String.format (FORMAT, txt, SUCCESS);
			assertEquals(e.getMessage(),"Provided address may not be null");
		}
		
		Addresses a2 = new Addresses();
		a2.setId("NoAddr");
		txt = "   Test if address can not be found         ";
		try
		{
			shippingService.ship_items(new Item[] {i}, a2);
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should have gotten UnknownAddressFault - address provided does not exist");
		}
		catch (UnknownAddressFault e)
		{
			output += String.format (FORMAT, txt, SUCCESS);
			assertEquals(e.getMessage(),"[ShippingService] ship_items: unknown address");
		}
		
		txt = "   Ship items         ";
		try
		{
			String shipmentID = shippingService.ship_items(new Item[] {i}, a);
			assertNotNull(shipmentID);
			output += String.format (FORMAT, txt, SUCCESS);
		}
		catch (Exception e)
		{
			output += String.format (FORMAT, txt, FAILURE);
			fail("Test failed as not possible to ship items because of "+e.getMessage());
		}
		
		System.out.print(output);
	}
	
	@Test
	public void doSupplierTest()
	{
		String output = "";
		output += String.format(FORMAT, "Starting supplier-Test", "");
		
		assertTrue(supplierService != null);
		
		Product p1 = new Product();
		p1.setId("978-0-321-35668-0");
		p1.setName("Effective Java - Second Edition");
		p1.setSingleUnitPrice(new BigDecimal(35.95));
		
		String txt = "   Test if invalid product is passed";
		try
		{
			supplierService.order(null, 1);
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should have gotten IllegalArgumentException - product to order is null");
		}
		catch (IllegalArgumentException e)
		{
			assertEquals(e.getMessage(),"Product to order is null");
			output += String.format (FORMAT, txt, SUCCESS);
		}
		
		txt = "   Test if order-amount is > 0";
		try
		{
			supplierService.order(p1, 0);
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should have gotten IllegalArgumentException - amount to order is null");
		}
		catch (IllegalArgumentException e)
		{
			assertEquals(e.getMessage(),"At least one product has to be orderd - amount < 1");
			output += String.format (FORMAT, txt, SUCCESS);
		}
		
		Product p2 = new Product();
		p2.setId("Test");
		txt = "   Test if product to order is known to the system";
		try
		{
			supplierService.order(p2, 2);
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should have gotten UnknownProdcutFault - product not known to the system");
		}
		catch (UnknownProductFault e)
		{
			assertEquals(e.getMessage(), "Product is not known to the system");
			output += String.format (FORMAT, txt, SUCCESS);
		}
		
		txt = "   Order product";
		try
		{
			BigDecimal totalPrice = supplierService.order(p1, 4);
			// nasty bug - BigDecimal for 1.0 has many positions after decimal point
			// which are unequal from 0, 2 "same" values may have unequal positions
			// after decimal point
			assertEquals(new Float(totalPrice.floatValue()), new Float(new BigDecimal(4*35.95).floatValue()));
			output += String.format (FORMAT, txt, SUCCESS);
		}
		catch (Exception e)
		{
			output += String.format (FORMAT, txt, FAILURE);
			fail("Test failed as not possible to order products due to: "+e.getMessage());
		}
		
		System.out.print(output);
	}
	
	@Test
	public void doWarehouseTest()
	{
		String output = "";
		output += String.format (FORMAT,"Initializing warehouse-Test","");
		
		assertTrue(warehouseService != null);
		
		// Product exists at system and is available at the warehouse
		Product p1 = new Product();
		p1.setId("978-0-321-35668-0");
		p1.setName("Effective Java - Second Edition");
		p1.setSingleUnitPrice(new BigDecimal(35.95));
		
		// product exists at the system but is not available at the warehouse
		Product p2 = new Product();
		p2.setId("978-0596529260");
		p2.setName("RESTful Web Services");
		p2.setSingleUnitPrice(new BigDecimal(28.95));
		
		// product does not exists at the system
		Product p3 = new Product();
		p3.setId("test");
		
		// test check_availability-method
		
		String txt = "   Test if product is null";
		try
		{
			warehouseService.check_availability(null, 1);
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should have gotten IllegalArgumentException - product to check for availability is null");
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(e.getMessage(), "Product to check is null");
			output += String.format (FORMAT, txt, SUCCESS);
		}
		
		txt = "   Test if amount of product is > 0";
		try
		{
			warehouseService.check_availability(p1, 0);
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should have gotten IllegalArgumentException - amount should be > 0");
		}
		catch (IllegalArgumentException e)
		{
			assertEquals(e.getMessage(),"Amount must not be 0 or below");
			output += String.format (FORMAT, txt, SUCCESS);
		}
		
		txt = "   Test if stored product is known to the system";
		try
		{
			warehouseService.check_availability(p3, 1);
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should have gotten UnknownProductFault - product is not known to the system");
		}
		catch (UnknownProductFault e)
		{
			assertEquals(e.getMessage(),"Product ist not known to the system");
			output += String.format (FORMAT, txt, SUCCESS);
		}
		
		txt = "   Test product known by the system but not offered by the warehouse";
		try
		{
			WarehouseReturn ret = warehouseService.check_availability(p2, 1);
			assertNotNull(ret);
			assertTrue(!ret.isAvailable());
			if (ret != null && ret.isAvailable())
				output += String.format (FORMAT, txt, FAILURE);
			else
				output += String.format (FORMAT, txt, SUCCESS);
		}
		catch (Exception e)
		{
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should not have thrown Exception - reason: "+e.getMessage());
		}
		
		txt = "   Test product known by the system and offered by the warehouse, but not enough items available";
		try
		{
			WarehouseReturn ret = warehouseService.check_availability(p1, 15);
			assertNotNull(ret);
			assertTrue(!ret.isAvailable());
			if (ret != null && ret.isAvailable())
				output += String.format (FORMAT, txt, FAILURE);
			else
				output += String.format (FORMAT, txt, SUCCESS);
		}
		catch (Exception e)
		{
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should not have thrown Exceptin - reason: "+e.getMessage());
		}
		
		txt = "   Test for product handled by the warehouse and quantity to check available";
		try
		{
			WarehouseReturn ret = warehouseService.check_availability(p1, 4);
			assertNotNull(ret);
			assertTrue(ret.isAvailable());
			if (ret != null && ret.isAvailable())
				output += String.format (FORMAT, txt, SUCCESS);
			else
				output += String.format (FORMAT, txt, FAILURE);
		}
		catch (Exception e)
		{
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should not have thrown Exceptin - reason: "+e.getMessage());
		}
		
		// Test order-method
		
		txt = "   Test order invalid product";
		try
		{
			warehouseService.order(null, 1);
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should have gotten IllegalArgumentException - product to order is null");
		}
		catch (IllegalArgumentException e)
		{
			assertEquals(e.getMessage(),"Product to order is null");
			output += String.format (FORMAT, txt, SUCCESS);
		}
		
		txt = "   Test order amount < 1";
		try
		{
			warehouseService.order(p1, 0);
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should have gotten IllegalArgumentException - amount of products to order < 1");
		}
		catch (IllegalArgumentException e)
		{
			assertEquals(e.getMessage(),"At least one product has to be orderd - amount < 1");
			output += String.format (FORMAT, txt, SUCCESS);
		}
		
		txt = "   Test order of product not known to the system";
		try
		{
			warehouseService.order(p3, 1);
			output += String.format (FORMAT, txt, FAILURE);
			fail("Should have gotten UnknownProductFault - product not known to the system");
		}
		catch (UnknownProductFault e)
		{
			assertEquals(e.getMessage(),"Product ist not known to the system");
			output += String.format (FORMAT, txt, SUCCESS);
		}
		
		txt = "   Test order product not handled by the warehouse";
		try
		{
			BigDecimal ret = warehouseService.order(p2, 1);
			assertNotNull(ret);
			assertEquals(new Float(ret.floatValue()),new Float(new BigDecimal(0).floatValue()));
			if (ret.equals(new BigDecimal(0)))
				output += String.format (FORMAT, txt, SUCCESS);
			else
				output += String.format (FORMAT, txt, FAILURE);
		}
		catch (Exception e)
		{
			fail("Should not have gotten Exception - reason: "+e.getMessage());
			output += String.format (FORMAT, txt, FAILURE);
		}
		
		txt = "   Test order product handled by the warehouse but not enough items available";
		try
		{
			BigDecimal ret = warehouseService.order(p1, 15);
			assertNotNull(ret);
			assertEquals(new Float(ret.floatValue()), new Float(new BigDecimal(0).floatValue()));
			if (ret.equals(new BigDecimal(0)))
				output += String.format (FORMAT, txt, SUCCESS);
			else
				output += String.format (FORMAT, txt, FAILURE);
		}
		catch (Exception e)
		{
			fail("Should not have gotten Exception - reason: "+e.getMessage());
			output += String.format (FORMAT, txt, FAILURE);
		}
		
		txt = "   Test order product handled by the warehouse and enough items available";
		try
		{
			BigDecimal ret = warehouseService.order(p1, 4);
			assertNotNull(ret);
			assertEquals(new Float(ret.floatValue()), new Float(new BigDecimal(4*35.95).floatValue()));
			if (ret.floatValue() == new BigDecimal(4*35.95).floatValue())
				output += String.format (FORMAT, txt, SUCCESS);
			else
				output += String.format (FORMAT, txt, FAILURE);
		}
		catch (Exception e)
		{
			fail("Should not have gotten Exception - reason: "+e.getMessage());
			output += String.format (FORMAT, txt, FAILURE);
		}
		System.out.print(output);
	}
	
	@Test
	public void doCustomerManagementSOAPTest()
	{
		String output = "";
		output += String.format(FORMAT, "Starting CustomerManagementSOAP-Test","");
		
		assertNotNull(customerService);
		
		Customer c1 = new Customer();
		c1.setId("0105011");
		c1.setName("Roman Vottner");
		// c1.setAddresses(new Addresses[] { a1 });
		// c1.setOrder(new Order[] { o1 });
		// c1.setOpenBalance(new BigDecimal(35));
				
		String txt = "   Test retrieving customer with invalid ID";
		try
		{
			customerService.get(null);
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should have gotten a IllegalArgumentException - no valid ID provided");

		}
		catch (SOAPFaultException sE)
		{
			assertEquals(sE.getMessage(), "ID must not be null or empty");
			output += String.format(FORMAT, txt, SUCCESS);
		}
		
		txt = "   Test retrieving customer not known to the system";
		try
		{
			Customer c = customerService.get("InvalidID");
			assertNull(c);
			if (c == null)
				output += String.format(FORMAT, txt, SUCCESS);
			else
				output += String.format(FORMAT, txt, FAILURE);
		}
		catch (Exception e)
		{
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should not have gotten an exception - reason: "+e.getMessage());
		}
		
		txt = "   Test retrieving customer known to the system";
		try
		{
			Customer c = customerService.get("0105011");
			assertNotNull(c);
			assertEquals(c.getName(),"Roman Vottner");
			if (c != null && c.getName().equals("Roman Vottner"))
				output += String.format(FORMAT, txt, SUCCESS);
			else
				output += String.format(FORMAT, txt, FAILURE);
		}
		catch (Exception e)
		{
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should not have gotten an exeption - reason: e");
		}
		
		txt = "   Test creating customer with invalid id";
		try
		{
			customerService.create(null, "Test");
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should have gotten IllegalArgumentException - invalid ID provided");
		}
		catch (SOAPFaultException e)
		{
			assertEquals(e.getMessage(), "ID must not be null or empty");
			output += String.format(FORMAT, txt, SUCCESS);
		}
		
		txt = "   Test creating customer with invalid name";
		try
		{
			customerService.create("test", "");
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should have gotten IllegalArgumentException - invalid name provided");
		}
		catch (SOAPFaultException e)
		{
			assertEquals(e.getMessage(), "Name must not be null or empty");
			output += String.format(FORMAT, txt, SUCCESS);
		}
		
		txt = "   Test creating valid customer";
		try
		{
			Customer c = new Customer();
			c.setId("test1");
			c.setName("Test1");
			
			Customer wsC = customerService.create("test1", "Test1");
			assertNotNull(wsC);
			assertEquals(wsC,c);
			assertNotNull(customerService.get("test1"));
			if (wsC != null && wsC.equals(c))
				output += String.format(FORMAT, txt, SUCCESS);
			else
				output += String.format(FORMAT, txt, FAILURE);
		}
		catch (Exception e)
		{
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should not throw an exception - reason: "+e.getMessage());
		}
		
		txt = "   Test creating already existing customer";
		try
		{
			Customer c = customerService.get("test1");
			assertNotNull(c);
			Customer wsC = customerService.create("test1", "Test1");
			assertNotNull(wsC);
			assertEquals(wsC,c);
			// assertSame(wsC,c); does not work here as we do have proxy-objects
			
			if (wsC != null && wsC.equals(c))
				output += String.format(FORMAT, txt, SUCCESS);
			else
				output += String.format(FORMAT, txt, FAILURE);
		}
		catch (Exception e)
		{
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should not throw an exception - reason: "+e.getMessage());
		}
		
		txt = "   Test updating invalid customer object";
		try
		{
			customerService.update(null);
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should have thrown IllegalArgumentException - invalid customer-object passed");
		}
		catch (SOAPFaultException e)
		{
			assertEquals(e.getMessage(), "Invalid customer to update specified");
			output += String.format(FORMAT, txt, SUCCESS);
		}
		
		txt = "   Test updating not existing customer object";
		try
		{
			Customer c = new Customer();
			c.setId("doesnotexist");
			c.setName("DoesNotExist");
			Customer ret = customerService.update(c);
			assertNotNull(ret);
			assertEquals(ret.getName(), "DoesNotExist");
			output += String.format(FORMAT, txt, SUCCESS);
		}
		catch (SOAPFaultException e)
		{
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should not have thrown an exception - reason: "+e.getMessage());

		}
		
		txt = "   Test updating existing customer";
		try
		{
			Customer c = customerService.get("0123456");
			assertNotNull(c);
			c.setName("Updated");
			Customer cUpdated = customerService.update(c);
			assertNotNull(cUpdated);
			assertEquals(cUpdated.getName(), "Updated");
			output += String.format(FORMAT, txt, SUCCESS);
		}
		catch (SOAPFaultException e)
		{
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should not have thrown an exception - reason: "+e.getMessage());
		}
		
		txt = "   Test delete customer with invalid id";
		try
		{
			customerService.delete(null);
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should have gotten an exception - delete customer with invalid id");
		}
		catch (SOAPFaultException e)
		{
			output += String.format(FORMAT, txt, SUCCESS);
			assertEquals(e.getMessage(), "ID must not be null or empty");
		}
		
		txt = "   Test delete not existing customer";
		try
		{
			Customer c = customerService.get("DoesNotExist");
			assertNull(c);
			customerService.delete("DoesNotExist");
			c = customerService.get("DoesNotExist");
			assertNull(c);
			output += String.format(FORMAT, txt, SUCCESS);
		}
		catch (SOAPFaultException e)
		{
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should not have gotten an exception - deleting a non-existing customer has no effect");
		}
		
		txt = "   Test delete existing customer";
		try
		{
			Customer c = customerService.get("doesnotexist");
			assertNotNull(c);
			customerService.delete(c.getName());
			c = customerService.get("doesnotexist");
			assertNull(c);
			output += String.format(FORMAT, txt, SUCCESS);
		}
		catch (SOAPFaultException e)
		{
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should have gotten an exception - customer exists, so should be deletable");			
		}
		
		txt = "   Test notify with invalid id";
		try
		{
			customerService.notify(null, "test");
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should have gotten an exception - id is null");
		}
		catch (SOAPFaultException e)
		{
			output += String.format(FORMAT, txt, SUCCESS);
			assertEquals(e.getMessage(),"ID must not be null or empty");
		}

		txt = "   Test notify without message";
		try
		{
			customerService.notify("0123456", null);
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should have gotten an exception - message is null");
		}
		catch (SOAPFaultException e)
		{
			output += String.format(FORMAT, txt, SUCCESS);
			assertEquals(e.getMessage(),"Message to send must not be null or empty");
		}
		
		txt = "   Test notify not existing customer";
		try
		{
			customerService.notify("DoesNotExist", "test");
			output += String.format(FORMAT, txt, SUCCESS);
		}
		catch (SOAPFaultException e)
		{
			output += String.format(FORMAT, txt, FAILURE);
			fail("Should have gotten an exception - id is null");
		}
		
		// test notify for existing customer with valid message
		// test update_account for invalid id
		// test update_account for valid id but no existing customer
		// test update_account with invalid amount
		// test update_account for existing user with valid amount
		
		System.out.print(output);
	}
	
	@Test
	public void doCustomerManagementRESTTest()
	{
		String output = "";
		output += String.format(FORMAT, "Starting CustomerManagementREST-Test","");
		
		
		System.out.print(output);
	}
	
	@AfterClass
	public static void teardown()
	{
		System.out.println("Test finished");
	}
	
	public static junit.framework.Test suite() 
	{
		return new JUnit4TestAdapter(JUnitTest.class);
	}
}