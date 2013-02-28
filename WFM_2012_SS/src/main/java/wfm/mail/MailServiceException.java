package wfm.mail;

public class MailServiceException extends Exception {

	private static final long serialVersionUID = 7764645902802497855L;

	public MailServiceException() {
		super();
	}

	public MailServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MailServiceException(String message) {
		super(message);
	}

	public MailServiceException(Throwable cause) {
		super(cause);
	}

}
