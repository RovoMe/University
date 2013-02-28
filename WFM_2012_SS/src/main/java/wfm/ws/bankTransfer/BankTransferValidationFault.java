package wfm.ws.bankTransfer;

public class BankTransferValidationFault extends RuntimeException
{
	private static final long serialVersionUID = -1585136293633755218L;
	
    public BankTransferValidationFault(String message) 
    {
        super(message);
    }

    public BankTransferValidationFault(String message, Throwable cause) 
    {
        super(message, cause);
    }
}
