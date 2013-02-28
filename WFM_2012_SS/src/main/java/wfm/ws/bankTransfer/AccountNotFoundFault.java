package wfm.ws.bankTransfer;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.ws.WebFault;

@WebFault(name="AccountNotFound")
@XmlAccessorType( XmlAccessType.FIELD )
public class AccountNotFoundFault extends RuntimeException 
{
	private static final long serialVersionUID = 8700958744600602098L;
	
    public AccountNotFoundFault(String message) 
    {
        super(message);
    }

    public AccountNotFoundFault(String message, Throwable cause) 
    {
        super(message, cause);
    }
}
