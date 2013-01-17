import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.xmlsoap.schemas.ws._2004._08.addressing.EndpointReferenceType;
import data.Addresses;
import data.Customer;
import data.DataBackend;
import data.Item;
import data.Order;
import data.Product;
import ws.CustomerManagementService;
import ws.CustomerManagementServiceSOAP;
import ws.ShippingService;
import ws.SupplierService;
import ws.WarehouseReturn;
import ws.WarehouseService;
import ws.impl.CustomerManagementServiceRESTImpl;
import ws.impl.CustomerManagementServiceSOAPImpl;
import ws.impl.ShippingServiceImpl;
import ws.impl.Supplier1;
import ws.impl.Supplier2;
import ws.impl.WarehouseServiceImpl;
import ws.impl.SupplierRegistry;

/**
 * <p>A simple Server which publishes 3 services. The first service is
 * a {@link ShippingService} and the other two are {@link SupplierService}s, 
 * where a {@link SupplierRegistry} decides which product get ordered by 
 * which supplier.</p>
 * 
 * @author roman
 */
public class Server
{	
	private DataBackend backend = DataBackend.getInstance();
	
	/**
	 * Simple main-method of the server-application. Creates test-data,
	 * registers services and starts some test-methods.
	 */
	public Server()
	{
		System.out.println("Starting Server");
		this.createTestData();
		
		// creates the SupplierRegistry
		SupplierRegistry registry = new SupplierRegistry();
		// bind the SupplyingServices
		String supplyingServiceAddress = "http://localhost:8088/Supplier";
        
		Endpoint.publish(supplyingServiceAddress+"1", new Supplier1());
		Endpoint.publish(supplyingServiceAddress+"2", new Supplier2());
		
		String supplierRegistryAddress = "http://localhost:8088/SupplierRegistry";
        Endpoint.publish(supplierRegistryAddress, registry);
		this.createSupplier1Data(registry);
		this.createSupplier2Data(registry);
		
		// bind the ShippingService
		ShippingService shippingService = new ShippingServiceImpl();
		String shippingServiceAddress = "http://localhost:8088/ShippingService";
		Endpoint.publish(shippingServiceAddress, shippingService);
		
		// bind the WarehouseService
		WarehouseService warehouseService = new WarehouseServiceImpl();
		String warehouseServiceAddress = "http://localhost:8088/WarehouseService";
		Endpoint.publish(warehouseServiceAddress, warehouseService);
		
		// first test
//		this.orderProduct(registry, backend.getProduct("978-0-321-35668-0"), 10);

		Item[] itemsTotal = backend.getItem();
		Item[] items = new Item[] { itemsTotal[0], itemsTotal[1] };
		Addresses _address = backend.getAddress("Addr1");
		
		// first item = 10 x Design Patterns. Elements of Reusable Object-Oriented Software
		// second item = 5 x Effective Java - Second Edition
		// warehouses stock is:
		// first product = 2 x Design Patterns. Elements of Reusable Object-Oriented Software
		// second product = 10 x Effective Java - Second Edition
		// result should be:
		// the first product has to be ordered from a supplier
		// the second product can be taken from the warehouse
		this.checkWarehouseAndOrderFromIt(registry, items, _address);
		
		// shipping test
		this.shipItems(items, _address);
		
		// http://osdir.com/ml/users-cxf-apache/2009-06/msg00088.html ff.
		String customerServiceAddress = "http://localhost:8089/";
		JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
		factory.setResourceClasses(CustomerManagementService.class);
		factory.setResourceProvider(CustomerManagementService.class, new SingletonResourceProvider(new CustomerManagementServiceRESTImpl()));
		factory.setAddress(customerServiceAddress);
		BindingFactoryManager manager = factory.getBus().getExtension(BindingFactoryManager.class);
		JAXRSBindingFactory bindingFactory = new JAXRSBindingFactory();
		bindingFactory.setBus(factory.getBus());
		manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, bindingFactory);
		factory.create();
		
		CustomerManagementServiceSOAP customerManagementServiceSOAP = new CustomerManagementServiceSOAPImpl();
		String customerManagementServiceAddress = "http://localhost:8088/CustomerManagementService";
		Endpoint.publish(customerManagementServiceAddress, customerManagementServiceSOAP);
		
	}
	
	/**
	 * Entrance point of the server-application
	 * @param args String-arguments received from the console
	 */
	public static void main(String[] args)
	{
		new Server();
	}

	/**
	 * Registers Supplier1 with the SupplierRegistry and offers two
	 * Products for ordering.
	 * @param registry SupplyRegistry to register the service to
	 */
	private void createSupplier1Data(SupplierRegistry registry)
	{		
		SupplierService supplierService = new Supplier1();
		registry.registerProductWithSupplier(backend.getProduct("978-0-321-35668-0"), supplierService);
		registry.registerProductWithSupplier(backend.getProduct("978-0201633610"), supplierService);	
	}
	
	/**
	 * Registers Supplier2 with the SupplierRegistry and offers two
	 * Products for ordering.
	 * @param registry SupplyRegistry to register the service to
	 */
	private void createSupplier2Data(SupplierRegistry registry)
	{		
		SupplierService supplierService = new Supplier2();
		registry.registerProductWithSupplier(backend.getProduct("978-1847195401"), supplierService);
		registry.registerProductWithSupplier(backend.getProduct("978-0596529260"), supplierService);
	}
	
	/**
	 * Tests ordering a certain amount of a product. This method uses
	 * the SupplierRegistry to decide from which SupplierService to order
	 * those products from.
	 * @param registry SupplyRegistry to ask for the supplier of a product
	 * @param product Product to order
	 * @param amount Amount of a Product to order
	 * @return Total price of the order
	 */
	private BigDecimal orderProduct(SupplierRegistry registry, Product product, int amount)
	{
		EndpointReferenceType erType = registry.getSupplierOfProduct(product);
//		System.out.println("Service bound to: " +erType.getAddress().getValue());
		
		QName serviceName =  new QName("http://infosys.tuwien.ac.at/aic10/ass1/dto/supplying", "SupplierService");
		QName portName = new QName("http://infosys.tuwien.ac.at/aic10/ass1/dto/supplying", "SupplierPT");
		
		Service service = Service.create(serviceName);
		service.addPort(portName,SOAPBinding.SOAP11HTTP_BINDING,erType.getAddress().getValue());
		
		SupplierService test = service.getPort(portName, SupplierService.class);
		return test.order(product, amount);
	}
	
	/**
	 * Tests shipping of items to a certain address
	 * @param items Items to ship
	 * @param address Address to ship those items to
	 * @return Returns a unique ID for the shipment
	 */
	private String shipItems(Item[] items, Addresses address)
	{
//		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
////		factory.getInFaultInterceptors().add(new LoggingInInterceptor());
////		factory.getOutInterceptors().add(new LoggingOutInterceptor());
//		factory.setAddress("http://localhost:8088/ShippingService");
//		factory.setServiceClass(ShippingService.class);
//		ShippingService shippingService = (ShippingService)factory.create();
//		shippingService.ship_items(items, address);
		
		QName serviceName =  new QName("http://infosys.tuwien.ac.at/aic10/ass1/dto/shipping", "ShippingService");
		QName portName = new QName("http://infosys.tuwien.ac.at/aic10/ass1/dto/shipping", "ShippingPT");
		
		Service service = Service.create(serviceName);
		service.addPort(portName,SOAPBinding.SOAP11HTTP_BINDING,"http://localhost:8088/ShippingService");
		
		ShippingService test = service.getPort(portName, ShippingService.class);
		return test.ship_items(items, address);
	}
	
	/**
	 * Test query to the WarehouseService if enough products for an 
	 * order are available. If so a order those goods from the warehouse,
	 * otherwise use a SupplierService
	 * @param items Items to check and order
	 * @param address Address to send those items to
	 */
	private void checkWarehouseAndOrderFromIt(SupplierRegistry registry, Item[] items, Addresses address)
	{
		if (items != null && items.length > 0 && address != null)
		{
			String NS = "http://infosys.tuwien.ac.at/aic10/ass1/dto/";
			QName serviceName = new QName(NS+"warehouseing","WarehouseService");
			QName portName = new QName(NS+"warehouseing","WarehousePT");
			
			Service service = Service.create(serviceName);
			service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, "http://localhost:8088/WarehouseService");
			
			WarehouseService test = service.getPort(portName, WarehouseService.class);
			
			for (Item item : items)
			{
				WarehouseReturn ret = test.check_availability(item.getProduct(), item.getQuantity());
				if (ret.isAvailable())
				{
					System.out.println("Product '"+item.getProduct().getName()+"' is available. Estimated delivery time: "+ret.getEstimatedDeliveryTime());
					test.order(item.getProduct(), item.getQuantity());
				}
				else
				{
					EndpointReferenceType erType = registry.getSupplierOfProduct(item.getProduct());
					System.out.println("Product '"+item.getProduct().getName()+"' currently not available. Ordering those products from supplier '"+erType.getAddress().getValue());

					this.orderProduct(registry, item.getProduct(), item.getQuantity());			
				}
			}
			

		}
	}
	
	/**
	 * Creates test data
	 */
	private void createTestData()
	{
		Product p1 = new Product();
		Product p2 = new Product();
		Product p3 = new Product();
		Product p4 = new Product();
		
		Item i1 = new Item();
		Item i2 = new Item();
		Item i3 = new Item();
		Item i4 = new Item();
		
		Order o1 = new Order();
		Order o2 = new Order();
		Order o3 = new Order();
		
		Addresses a1 = new Addresses();
		Addresses a2 = new Addresses();
		Addresses a3 = new Addresses();
		Addresses a4 = new Addresses();
		
		Customer c1 = new Customer();
		Customer c2 = new Customer();
		// Create some Test-Data
		
		p1.setId("978-0-321-35668-0");
		p1.setName("Effective Java - Second Edition");
		p1.setSingleUnitPrice(new BigDecimal(35.95));
		p1.setItem(i2);
		backend.store(p1);
		
		p2.setId("978-0201633610");
		p2.setName("Design Patterns. Elements of Reusable Object-Oriented Software");
		p2.setSingleUnitPrice(new BigDecimal(38.95));
		p2.setItem(i1);
		backend.store(p2);
		
		p3.setId("978-1847195401");
		p3.setName("Apache Cxf Web Service Development");
		p3.setSingleUnitPrice(new BigDecimal(33.70));
		p3.setItem(i3);
		backend.store(p3);
		
		p4.setId("978-0596529260");
		p4.setName("RESTful Web Services");
		p4.setSingleUnitPrice(new BigDecimal(28.95));
		p4.setItem(i4);
		backend.store(p4);
		
		i1.setProduct(p2);
		i1.setQuantity(10);
		i1.setOrder(o1);
		backend.store(i1);
		
		i2.setProduct(p1);
		i2.setQuantity(5);
		i2.setOrder(o1);
		backend.store(i2);
		
		i3.setProduct(p3);
		i3.setQuantity(4);
		i3.setOrder(o2);
		backend.store(i3);
		
		i4.setProduct(p4);
		i4.setQuantity(0);
		i4.setOrder(o3);
		backend.store(i4);
		
		o1.setId("No: 12345");
		o1.setItems(new Item[] { i1, i2 });
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(2010, Calendar.OCTOBER, 10, 20, 15, 00);
		o1.setOrderDate(cal.getTime());
		o1.setCustomer(c1);
		backend.store(o1);

		// hoffentlich verändert sich durch setzen eines neuen Datums 
		// fuer Calendar nicht das Datum fuer o1 - sonst einfach neuen 
		// Calendar erstellen
		o2.setId("No: 12346");
		o2.setItems(new Item[] { i3 });
		cal.set(2010, Calendar.OCTOBER, 11, 10, 35, 00);
		o2.setOrderDate(cal.getTime());
		o2.setCustomer(c2);
		backend.store(o2);
		
		o3.setId("No: 123457");
		o3.setItems(new Item[] {i4});
		cal.set(2010, Calendar.OCTOBER, 12, 9, 05, 00);
		o3.setOrderDate(cal.getTime());
		o3.setCustomer(c2);
		backend.store(o3);
		
		a1.setId("Addr1");
		a1.setStreet("Andersengasse 2");
		a1.setHouse(64);
		a1.setDoor(11);
		a1.setZipCode("1120");
		a1.setCity("Wien");
		a1.setIsShipping(true);
		a1.setIsBilling(true);
		a1.setIsOther(false);
		a1.setCustomer(c1);
		backend.store(a1);

		a2.setId("Addr2");
		a2.setStreet("Musterweg 1");
		a2.setZipCode("1010");
		a2.setCity("Wien");
		a2.setIsOther(true);
		a2.setCustomer(c2);
		backend.store(a2);
		
		a3.setId("Addr3");
		a3.setStreet("Teststraße 9");
		a3.setHouse(9);
		a3.setDoor(9);
		a3.setZipCode("1090");
		a3.setCity("Wien");
		a3.setIsShipping(true);
		a3.setIsBilling(true);
		a3.setIsOther(false);
		a3.setCustomer(c2);
		backend.store(a3);
		
		a4.setId("Addr4");
		a4.setStreet("Karlsplatz 13");
		a4.setZipCode("1040");
		a4.setCity("Wien");
		backend.store(a4);

		c1.setId("0105011");
		c1.setName("Roman Vottner");
		c1.setAddresses(new Addresses[] { a1 });
		c1.setOrder(new Order[] { o1 });
		c1.setOpenBalance(new BigDecimal(35));
		backend.store(c1);
		
		c2.setId("0123456");
		c2.setName("Max Mustermann");
		c2.setAddresses(new Addresses[] { a2, a3 });
		c2.setOrder(new Order[] { o2, o3 });
		c2.setOpenBalance(new BigDecimal(420.37));
		backend.store(c2);
	}
}
