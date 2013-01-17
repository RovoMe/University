package ws.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Hashtable;
import javax.jws.WebService;
import data.DataBackend;
import data.Product;
import ws.UnknownProductFault;
import ws.WarehouseReturn;
import ws.WarehouseService;

/**
 * <p>This class is the implementation of {@link ws.WarehouseService}.</p>
 * <p>Following call produces a wsdl-file for this class:</p>
 * <code>java2ws -wsdl -d ressources/ -verbose -cp bin/ webService.impl.WarehouseServiceImpl</code>
 * 
 * @author roman
 */

//http://cxf.apache.org/docs/developing-a-service.html#DevelopingaService-RequiredAnnotations
@WebService(endpointInterface="ws.WarehouseService", 
		serviceName="WarehouseService",
		targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/warehouse",
		portName="WarehousePT") // wird nach portType gemapped
public class WarehouseServiceImpl implements WarehouseService
{
	private Hashtable<Product, ProductDetails> stock = new Hashtable<Product, ProductDetails>();
	
	public WarehouseServiceImpl()
	{
		// one day in milliseconds - 1000*60*60*24;
		long day = 86400000;
		
		ProductDetails detailsP1 = new ProductDetails();
		detailsP1.setDeliveryTime(3*day);
		detailsP1.setAmount(10);
		detailsP1.setIsAvailable(true);
		
		stock.put(DataBackend.getInstance().getProduct("978-0-321-35668-0"), detailsP1);
		
		ProductDetails detailsP2 = new ProductDetails();
		detailsP2.setDeliveryTime(7*day);
		detailsP2.setAmount(2);
		detailsP2.setIsAvailable(true);
		
		stock.put(DataBackend.getInstance().getProduct("978-0201633610"), detailsP2);
	}
	
	@Override
	public BigDecimal order(Product product, int amount)
			throws UnknownProductFault, IllegalArgumentException
	{
		// check input parameters
		if (product == null)
			throw new IllegalArgumentException("Product to order is null");
		if (amount < 1)
			throw new IllegalArgumentException("At least one product has to be orderd - amount < 1");
		
		// check if the product is known to the system
		Product storedProduct = DataBackend.getInstance().getProduct(product.getId());
		if (storedProduct == null || !storedProduct.equals(product))
			throw new UnknownProductFault("Product ist not known to the system");
		
		WarehouseReturn ret = check_availability(product, amount);
		if (ret.isAvailable())
		{
			BigDecimal totalPrice = storedProduct.getSingleUnitPrice().multiply(new BigDecimal(amount));
			System.out.println("[WarehouseService] "+new Date(System.currentTimeMillis()).toString());
			System.out.println("[WarehouseService] order for "+amount+" '"+storedProduct.getName()+"' received");
			System.out.println("[WarehouseService] total cost: "+totalPrice);

			ProductDetails details = stock.get(product);
			if (details != null)
			{
				details.setAmount(details.getAmount()-amount);
				System.out.println("[WarehouseService] amount of '"+storedProduct.getName()+"' available: "+details.getAmount());
				System.out.println("[WarehouseService] estimated delivery time: "+ret.getEstimatedDeliveryTime());
			}
			else
				System.err.println("[WarehouseService] no details could be found");
			
			return totalPrice;
		}
		else
		{
			System.out.println("[WarehouseService] "+new Date(System.currentTimeMillis()).toString());
			System.out.println("[WarehouseService] not enough goods of "+product.getName()+ " available");
			System.out.println("[WarehouseService] estimated delivery time: "+ret.getEstimatedDeliveryTime());
		}
		return new BigDecimal(0);
	}

	@Override
	public WarehouseReturn check_availability(Product product, int amount) 
		throws UnknownProductFault, IllegalArgumentException
	{
		// check input parameters
		if (product == null)
			throw new IllegalArgumentException("Product to check is null");
		if (amount < 1)
			throw new IllegalArgumentException("Amount must not be 0 or below");
		
		// check if the product is known to the system
		Product storedProduct = DataBackend.getInstance().getProduct(product.getId());
		if (storedProduct == null) {
			System.err.println("[WarehouseService] Product with id " + product.getId() + "not found");
			throw new UnknownProductFault("Product ist not known to the system");
		}
		
		System.out.println("[WarehouseService] check_availability: product " + product.getId() + " found!");
		
		WarehouseReturn ret = new WarehouseReturn();
		
		ProductDetails details = stock.get(product);
		// Product is not in the warehouse
		if (details == null) {
			System.out.println("[WarehouseService] check_availability: not avaliable!");
			ret.setAvailable(false);
			ret.setEstimatedDeliveryTime(0);
		} else {
			System.out.println("[WarehouseService] check_availability: in stock: " + details.getAmount() + " | ordered: " + amount);
			ret.setAvailable(details.isAvailable(amount));
			System.out.println("[WarehouseService] check_availability: Available: " + ret.isAvailable());
			ret.setEstimatedDeliveryTime(details.getDeliveryTime());
		}
		
		return ret;
	}
}
