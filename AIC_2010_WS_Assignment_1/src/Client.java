import java.math.BigDecimal;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import data.Addresses;
import data.Item;
import data.Product;
import ws.ShippingService;
import ws.SupplierService;


public class Client
{
	public static void main(String[] args)
	{
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInFaultInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(ShippingService.class);
		factory.setAddress("http://localhost:9000/ShippingService");
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
		factory2.setAddress("http://localhost:9000/Supplier1");
		factory2.setServiceClass(SupplierService.class);
		SupplierService supplierService = (SupplierService)factory2.create();
		
		BigDecimal totalPrice = supplierService.order(p1, 3);
		System.out.println(totalPrice);
		
		
		JaxWsProxyFactoryBean factory3 = new JaxWsProxyFactoryBean();
		factory3.getInFaultInterceptors().add(new LoggingInInterceptor());
		factory3.getOutInterceptors().add(new LoggingOutInterceptor());
		factory3.setAddress("http://localhost:9000/Supplier2");
		factory3.setServiceClass(SupplierService.class);
		SupplierService supplierService2 = (SupplierService)factory3.create();
		
		BigDecimal totalPrice2 = supplierService2.order(p1, 4);
		System.out.println(totalPrice2);
	}
}
