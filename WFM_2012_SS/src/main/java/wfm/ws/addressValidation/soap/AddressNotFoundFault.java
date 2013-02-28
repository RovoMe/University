package wfm.ws.addressValidation.soap;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.ws.WebFault;

@WebFault(name="AddressNotFound")
@XmlAccessorType( XmlAccessType.FIELD )
public class AddressNotFoundFault extends RuntimeException 
{
	private static final long serialVersionUID = 8700958744600602098L;
	
    public AddressNotFoundFault(String message) 
    {
        super(message);
    }

    public AddressNotFoundFault(String message, Throwable cause) 
    {
        super(message, cause);
    }
}
