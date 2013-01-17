package ws.impl;

import java.math.BigDecimal;
import java.util.Date;
import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;
import ws.SupplierService;
import ws.UnknownProductFault;
import data.DataBackend;
import data.Product;

/**
 * <p>This class is the implementation of {@link ws.SupplierService}.</p>
 * <p>Following call produces a wsdl-file for this class:</p>
 * <code>java2ws -wsdl -d ressources/ -verbose -cp bin/ webService.impl.Supplier1</code>
 * 
 * @author roman
 */

// http://cxf.apache.org/docs/developing-a-service.html#DevelopingaService-RequiredAnnotations
@WebService(endpointInterface="ws.SupplierService", 
		serviceName="SupplierService",
		targetNamespace="http://infosys.tuwien.ac.at/aic10/ass1/dto/supplying",
		portName="SupplierPT") // wird nach portType gemapped
@Addressing
public class Supplier1 implements SupplierService
{
	@Override
	public BigDecimal order(Product product, int amount) throws UnknownProductFault, IllegalArgumentException
	{
		// check input parameters
		if (product == null)
			throw new IllegalArgumentException("Product to order is null");
		if (amount < 1)
			throw new IllegalArgumentException("At least one product has to be orderd - amount < 1");
		
		// check if the product is known to the system
		Product storedProduct = DataBackend.getInstance().getProduct(product.getId());
		if (storedProduct == null || !storedProduct.equals(product))
			throw new UnknownProductFault("Product is not known to the system");
		
		BigDecimal totalPrice = storedProduct.getSingleUnitPrice().multiply(new BigDecimal(amount));
		System.out.println("[SupplierService][Supplier1] "+new Date(System.currentTimeMillis()).toString());
		System.out.println("[SupplierService][Supplier1] order for "+amount+" '"+storedProduct.getName()+"' received");
		System.out.println("[SupplierService][Supplier1] total cost: "+totalPrice);
		return totalPrice;
	}
}
