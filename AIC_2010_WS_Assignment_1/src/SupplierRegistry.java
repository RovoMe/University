import java.util.Hashtable;
import java.util.Map;
import org.xmlsoap.schemas.ws._2004._08.addressing.AttributedURI;
import org.xmlsoap.schemas.ws._2004._08.addressing.EndpointReferenceType;
import org.xmlsoap.schemas.ws._2004._08.addressing.ObjectFactory;
import ws.SupplierService;
import ws.UnknownProductFault;
import ws.impl.Supplier1;
import ws.impl.Supplier2;
import data.DataBackend;
import data.Product;

/**
 * http://wso2.org/library/2605
 * 
 * Message Addressing Property  	Description
 *  wsa:To 			Provides the destination URI. This property indicates where the message will be sent to.
 *  wsa:From 		Provides the source endpoint reference. Indicates where the message came from.
 *  wsa:ReplyTo 	Provides the reply endpoint reference. Indicates where the reply for the request message should be sent to. If this is not present, usually the reply will be sent back to the endpoint where the request came from.
 *  wsa:FaultTo 	Provides the fault endpoint reference. Indicates where the fault message should be sent to in case there is a fault. If this is not present, usually the fault will be sent back to the endpoint where the request came from
 *  wsa:Action 		Conveys the action related to a message. For example, the wsa:Action property can be used to identify the operation to be invoked upon receiving a request message. This property must be provided in message addressing properties of a message.
 *  wsa:MessageID 	Conveys the ID of a message that can be used to uniquely identify a message.
 *  wsa:RelatesTo 	Conveys the message ID of a related message, along with the relationship type.
 *  
 *  http://community.jboss.org/wiki/JBossWS-CXFWS-Addressingtutorial
 *  http://www.informit.com/guides/content.aspx?g=xml&seqNum=160
 *  http://www.java2s.com/Code/Java/Web-Services-SOA/ThisdemoshowshowWSAddressingsupportinApacheCXFmaybeenabled.htm
 */

/**
 * <p>{@link SupplierService}s can register themselves or being registered by an
 * other component with {@link #registerProductWithSupplier(Product, SupplierService)}
 * to this registry. To decided which supplier to choose for which {@link Product}s use 
 * {@link #getSupplierOfProduct(Product)}.</p>
 * <p>Note that there can only by one supplier
 * for a product.</p>
 */
public class SupplierRegistry
{
	private static final String SUPPLIER1_EP_URL = "http://localhost:9000/Supplier1";
	private static final String SUPPLIER2_EP_URL = "http://localhost:9000/Supplier2";
	
	private Map<Product,EndpointReferenceType> supplierOfProduct = 
		new Hashtable<Product, EndpointReferenceType>();
	
	/**
	 * Instantiates this class.
	 */
	public SupplierRegistry()
	{
		
	}
	
	/**
	 * Registers a {@link SupplierService} who is able to provide this {@link Product}.
	 * @param product {@link Product} the {@link SupplierService} can offer. 
	 * @param supplierService {@link SupplierService} a {@link Product} can be ordered from.
	 * @throws UnknownProductFault Thrown if the product is not known to the system.
	 */
	public void registerProductWithSupplier(Product product, SupplierService supplierService) 
		throws UnknownProductFault
	{
		// checks if the product is known to the system
		if (product == null || 
				!product.equals(DataBackend.getInstance().getProduct(product.getId())))
			throw new UnknownProductFault();
		
		// we don't need empty products and services
		// and as the hashtable should be static, 
		// forbid changes of supplier for already existing products
		if (product != null && supplierService != null && 
				supplierOfProduct.get(product) == null)
		{
			System.out.println("[SupplierRegistry] adding product: '"+product+
					"' for supplier: "+supplierService);

			// We don't really need the ObjectFactory as we could instantiate
			// the EndpointReferenceType directly, but if there happens some
			// kind of magic in the background - use it for safety-reasons
			ObjectFactory factory = new ObjectFactory();
			EndpointReferenceType erType = factory.createEndpointReferenceType();
				
//			final String NS = "http://infosys.tuwien.ac.at/aic10/ass1/dto/supplying";
//			ServiceNameType serviceNameType = factory.createServiceNameType();			
//			serviceNameType.setPortName("SupplierPT");
//			serviceNameType.setValue(new QName(NS,"SupplierService"));
//			erType.setServiceName(serviceNameType);
				
			// the AttributedURI stores the actual address of the service
			// we'd like to reference to
			AttributedURI attributedURI = factory.createAttributedURI();
			if (supplierService instanceof Supplier1)
				attributedURI.setValue(SUPPLIER1_EP_URL);
			if (supplierService instanceof Supplier2)
				attributedURI.setValue(SUPPLIER2_EP_URL);
			erType.setAddress(attributedURI);
			
			// put it in the table to store it
			supplierOfProduct.put(product, erType);
		}	
	}
	
	/**
	 * Returns an {@link EndpointReferenceType} of the {@link SupplierService} which 
	 * registered for a certain {@link Product}.
	 * @param product {@link Product} a {@link SupplierService} registered for.
	 * @return {@link EndpointReferenceType} of the {@link SupplierService} which 
	 * 		offers the {@link Product}
	 * @throws UnknownProductFault Thrown if the {@link Product} is not known to the
	 * 		system.
	 */
	public EndpointReferenceType getSupplierOfProduct(Product product) 
		throws UnknownProductFault
	{
		if (product == null || 
				!product.equals(DataBackend.getInstance().getProduct(product.getId())))
			throw new UnknownProductFault("Product is not known to the system");

		EndpointReferenceType erType = supplierOfProduct.get(product);		
		if (erType == null)
			throw new UnknownProductFault("No supplier could be found for product: '"+product.getName()+"'");

		return erType;
	}
}