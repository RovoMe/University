package wfm.mail;

/**
 * Service to send simple e-mail messages.
 */
public interface MailService {

	/**
	 * Set the recipient of the e-mail message.
	 */
	void setRecipient(String to);

	/**
	 * Set the message body of the e-mail message.
	 */
	void setMessage(String message);

	/**
	 * Sends the e-mail message and returns {@code true} if successful.
	 * Otherwise a {@link MailServiceException} is thrown.
	 * 
	 * @throws MailServiceException
	 */
	boolean sendMessage() throws MailServiceException;

}
