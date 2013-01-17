package dst3.ejb.exceptions;

import java.io.Serializable;

public class UserNotLoggedInException extends Exception implements Serializable
{
	private static final long serialVersionUID = -6182554193839744614L;

	public UserNotLoggedInException()
	{
		super();
	}
	
	public UserNotLoggedInException(String message)
	{
		super(message);
	}
}