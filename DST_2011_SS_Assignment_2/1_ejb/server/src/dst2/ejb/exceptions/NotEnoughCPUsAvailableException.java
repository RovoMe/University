package dst2.ejb.exceptions;

import java.io.Serializable;

public class NotEnoughCPUsAvailableException extends Exception implements Serializable
{
	private static final long serialVersionUID = 4547225926057018106L;

	public NotEnoughCPUsAvailableException()
	{
		super();
	}
	
	public NotEnoughCPUsAvailableException(String message)
	{
		super(message);
	}
}
