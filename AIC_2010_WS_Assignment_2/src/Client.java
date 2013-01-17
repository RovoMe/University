import java.math.BigDecimal;

import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import data.Addresses;
import data.Customer;
import data.Item;
import data.Product;
import ws.CustomerManagementService;
import ws.CustomerManagementServiceSOAP;
import ws.ShippingService;
import ws.SupplierService;

public class Client
{
	public static void main(String[] args)
	{
		new Client();
	}
	
	public Client()
	{
		this.registryTest();
		this.soapTest();
		this.soapTest2();
		this.rest2soapTest();
		this.soapTest_Lukas_shipping1();
		this.soapTest_Lukas_shipping2();
	}
	
	public void registryTest() {
		
	}
	
	public void soapTest()
	{
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInFaultInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(ShippingService.class);
		factory.setAddress("http://localhost:8088/ShippingService");
		ShippingService shippingService = (ShippingService)factory.create();
		
		Product p1 = new Product();
		p1.setId("978-0-321-35668-0");
		p1.setName("Effective Java - Second Edition");
		p1.setSingleUnitPrice(new BigDecimal(35.95));
		
		Product p2 = new Product();
		p2.setId("978-0201633610");
		p2.setName("Design Patterns. Elements of Reusable Object-Oriented Software");
		p2.setSingleUnitPrice(new BigDecimal(38.95));		
		
		Item i1 = new Item();
		i1.setProduct(p2);
		i1.setQuantity(10);	
		
		Item i2 = new Item();
		i2.setProduct(p1);
		i2.setQuantity(5);
		
		Item[] items = new Item[] { i1, i2 };
		
		Addresses address = new Addresses();
		address.setId("Addr1");
		address.setStreet("Andersengasse 2");
		address.setHouse(64);
		address.setDoor(11);
		address.setZipCode("1120");
		address.setCity("Wien");
		address.setIsShipping(true);
		address.setIsBilling(true);
		address.setIsOther(false);
		
		String response = shippingService.ship_items(items, address);
		System.out.println(response);
		
		System.out.println("Call to Supplier1");
		
		JaxWsProxyFactoryBean factory2 = new JaxWsProxyFactoryBean();
		factory2.getInFaultInterceptors().add(new LoggingInInterceptor());
		factory2.getOutInterceptors().add(new LoggingOutInterceptor());
		factory2.setAddress("http://localhost:8088/Supplier1");
		factory2.setServiceClass(SupplierService.class);
		SupplierService supplierService = (SupplierService)factory2.create();
		
		BigDecimal totalPrice = supplierService.order(p1, 3);
		System.out.println(totalPrice);
		
		
		System.out.println("MAKE SOAP ERROR:");
		System.out.println("Order unkown produkt");
		try {
			Product faultProduct = new Product();
			faultProduct.setId("fault");
			
			totalPrice = supplierService.order(faultProduct, 1);
		} catch (Exception e) {
			System.out.println("SOAP ERROR!!!!");
			System.out.println(e);
			System.out.println("------------------------");
		}
		
	}
	
	public void soapTest2()
	{
		JaxWsProxyFactoryBean factory3 = new JaxWsProxyFactoryBean();
		factory3.getInFaultInterceptors().add(new LoggingInInterceptor());
		factory3.getOutInterceptors().add(new LoggingOutInterceptor());
		factory3.setAddress("http://localhost:8088/Supplier2");
		factory3.setServiceClass(SupplierService.class);
		SupplierService supplierService2 = (SupplierService)factory3.create();
		
		Product p1 = new Product();
		p1.setId("978-0-321-35668-0");
		p1.setName("Effective Java - Second Edition");
		p1.setSingleUnitPrice(new BigDecimal(35.95));
		
		BigDecimal totalPrice2 = supplierService2.order(p1, 4);
		System.out.println(totalPrice2);
	}
	
	public void restTest()
	{
		JAXRSClientFactoryBean rsFactory = new JAXRSClientFactoryBean();
		rsFactory.setServiceClass(CustomerManagementService.class);
		rsFactory.setAddress("http://localhost:8089/");
		BindingFactoryManager manager = rsFactory.getBus().getExtension(BindingFactoryManager.class);
		JAXRSBindingFactory bindingFactory = new JAXRSBindingFactory();
		bindingFactory.setBus(rsFactory.getBus());
		manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, bindingFactory);
		CustomerManagementService service = rsFactory.create(CustomerManagementService.class);	
		
		Customer customer = service.create("test", "Franz Test");
		System.out.println("Customer name: "+customer.getName());
		
		customer.setName("Hans Test");
		customer = service.update(customer);
		System.out.println("Customer-client name: "+customer.getName());
		System.out.println("Customer-server name: "+service.get("test").getName());
		
		service.delete(customer.getId());
		// customer-object gets deleted on server-side, but still a valid
		// copy on client side.
		System.out.println("Customer-client after delete: "+customer);
		System.out.println("Customer-server after delete: "+service.get("test"));
		
		
		customer = service.get("0105011");
		System.out.println("Customer name: "+customer.getName());
		
		service.notify("0105011","Testnachricht");
		
		System.out.println("Balance state: "+customer.getOpenBalance());
		service.update_account("0105011", new BigDecimal(200));
		System.out.println("Balance state: "+service.get("0105011").getOpenBalance());
	}
	
	public void rest2soapTest()
	{
		JaxWsProxyFactoryBean rs2soapFactory = new JaxWsProxyFactoryBean();
		rs2soapFactory.getInFaultInterceptors().add(new LoggingInInterceptor());
		rs2soapFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		rs2soapFactory.setServiceClass(CustomerManagementServiceSOAP.class);
		rs2soapFactory.setAddress("http://localhost:8088/CustomerManagementService");
		CustomerManagementServiceSOAP service = (CustomerManagementServiceSOAP)rs2soapFactory.create();

		
		Customer customer = service.create("test", "Franz Test");
		System.out.println("Customer name: "+customer.getName());

		customer.setName("Hans Test");
		customer = service.update(customer);
		System.out.println("Customer-client name: "+customer.getName());
		System.out.println("Customer-server name: "+service.get("test").getName());
		
		service.delete(customer.getId());
		// customer-object gets deleted on server-side, but still a valid
		// copy on client side.
		System.out.println("Customer-client after delete: "+customer);
		System.out.println("Customer-server after delete: "+service.get("test"));
		
		
		customer = service.get("0105011");
		System.out.println("Customer name: "+customer.getName());
		
		service.notify("0105011","Testnachricht");
		
		System.out.println("Balance state: "+customer.getOpenBalance());
		service.update_account("0105011", new BigDecimal(200));
		System.out.println("Balance state: "+service.get("0105011").getOpenBalance());
	
	}
	
	public void soapTest_Lukas_shipping1() // throws Exception
	{
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.getInFaultInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(ShippingService.class);
			factory.setAddress("http://localhost:8088/ShEEppingService");
			ShippingService shippingService = (ShippingService)factory.create();
			
			Product p1 = new Product();
			p1.setId("978-0-321-35668-0");
			p1.setName("Effective Java - Second Edition");
			p1.setSingleUnitPrice(new BigDecimal(35.95));
			
			Product p2 = new Product();
			p2.setId("978-0201633610");
			p2.setName("Design Patterns. Elements of Reusable Object-Oriented Software");
			p2.setSingleUnitPrice(new BigDecimal(38.95));		
			
			Item i1 = new Item();
			i1.setProduct(p2);
			i1.setQuantity(10);
			
			Item i2 = new Item();
			i2.setProduct(p1);
			i2.setQuantity(5);
			
			Item[] items = new Item[] { i1, i2 };
			
			Addresses address = new Addresses();
			address.setId("Addr1");
			address.setStreet("Andersengasse 2");
			address.setHouse(64);
			address.setDoor(11);
			address.setZipCode("1120");
			address.setCity("Wien");
			address.setIsShipping(true);
			address.setIsBilling(true);
			address.setIsOther(false);
			
			String response = shippingService.ship_items(items, address);
			System.out.println(response);
			
		} catch(Exception e) {
			System.out.println("UnknownAddressFault Test 1: SUCCESS");
			return;
		}
		
		//throw new Exception("UnknownAddressFault Test: FAILED");
		System.out.println("UnknownAddressFault Test 1: FAILED");
	}

	public void soapTest_Lukas_shipping2() // throws Exception
	{
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.getInFaultInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(ShippingService.class);
			factory.setAddress("http://localhost:8088/ShippingServices");
			ShippingService shippingService = (ShippingService)factory.create();
			
			Product p1 = new Product();
			p1.setId("978-0-321-35668-0");
			p1.setName("Effective Java - Second Edition");
			p1.setSingleUnitPrice(new BigDecimal(35.95));
			
			Product p2 = new Product();
			p2.setId("978-0201633610");
			p2.setName("Design Patterns. Elements of Reusable Object-Oriented Software");
			p2.setSingleUnitPrice(new BigDecimal(38.95));		
			
			Item i1 = new Item();
			i1.setProduct(p2);
			i1.setQuantity(10);
			
			Item i2 = new Item();
			i2.setProduct(p1);
			i2.setQuantity(5);
			
			Item[] items = new Item[] { i1, i2 };
			
			Addresses address = new Addresses();
			address.setId("Addr1");
			address.setStreet("Andersengasse 2");
			address.setHouse(64);
			address.setDoor(11);
			address.setZipCode("1120");
			address.setCity("Wien");
			address.setIsShipping(true);
			address.setIsBilling(true);
			address.setIsOther(false);
			
			String response = shippingService.ship_items(items, address);
			System.out.println(response);
			
		} catch(Exception e) {
			System.out.println("UnknownAddressFault Test 2: SUCCESS");
			return;
		}
		
		//throw new Exception("UnknownAddressFault Test: FAILED");
		System.out.println("UnknownAddressFault Test 2: FAILED");
	}

}
