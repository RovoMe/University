package ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.ws.WebFault;

/**
 * <p>An instance of this class will be thrown if an {@link data.Addresses}-
 * instance could not be found or the provided argument to a method is 
 * invalid (null f.e).</p>
 * 
 * @author roman
 */
@WebFault(name="UnknownAddress")
@XmlAccessorType( XmlAccessType.FIELD )
public class UnknownAddressFault extends RuntimeException
{
	/**
	 * Unique serial number needed for serialization
	 */
	private static final long serialVersionUID = -2025247383968440778L;

	public UnknownAddressFault() 
	{
		super();
	}

	public UnknownAddressFault(String message, Throwable cause) 
	{
		super(message, cause);
	}

	public UnknownAddressFault(String message) 
	{
		super(message);
	}

	public UnknownAddressFault(Throwable cause) 
	{
		super(cause);
	}
}
