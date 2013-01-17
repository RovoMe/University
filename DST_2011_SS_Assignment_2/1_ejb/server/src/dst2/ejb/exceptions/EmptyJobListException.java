package dst2.ejb.exceptions;

import java.io.Serializable;

public class EmptyJobListException extends Exception implements Serializable
{
	private static final long serialVersionUID = -6182554193839744614L;

	public EmptyJobListException()
	{
		super();
	}
	
	public EmptyJobListException(String message)
	{
		super(message);
	}
}
