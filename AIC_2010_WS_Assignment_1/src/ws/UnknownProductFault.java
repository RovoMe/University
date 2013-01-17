package ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.ws.WebFault;

/**
 * <p>An instance of this class will be thrown if a {@link data.Product}-
 * instance could not be found or the provided argument to a method is 
 * invalid (null f.e).</p>
 * 
 * @author roman
 */
@WebFault(name="UnknownProduct")
@XmlAccessorType( XmlAccessType.FIELD )
public class UnknownProductFault extends RuntimeException
{
	/**
	 * Unique serial number needed for serialization
	 */
	private static final long serialVersionUID = -2025247383968440778L;

	public UnknownProductFault() 
	{
		super();
	}

	public UnknownProductFault(String message, Throwable cause) 
	{
		super(message, cause);
	}

	public UnknownProductFault(String message) 
	{
		super(message);
	}

	public UnknownProductFault(Throwable cause) 
	{
		super(cause);
	}
}
