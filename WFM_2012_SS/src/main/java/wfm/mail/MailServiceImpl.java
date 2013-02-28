package wfm.mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailServiceImpl implements MailService
{

	private String from = "wfm-sender@web.de";
	private String host = "smtp.web.de";
	private String to = null;
	private String message = null;

	@Override
	public void setRecipient(String to)
	{
		this.to = to;
	}

	@Override
	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public boolean sendMessage() throws MailServiceException
	{
		// Confirm that neither recipient nor message body are empty
		if (to == null || to.equals(""))
		{
			throw new MailServiceException("No recipient defined.");
		}
		else if (message == null || message.equals(""))
		{
			throw new MailServiceException("No message defined.");
		}

		MimeMessage message = new MimeMessage(getSession());

		try
		{
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Order summary");
			message.setText(this.message);
			Transport.send(message);
		}
		catch (AddressException e)
		{
			e.printStackTrace();
			throw new MailServiceException("Mail address incorrect.", e);
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
			throw new MailServiceException("Message couldnt be sent.", e);
		}
		return true;
	}

	/**
	 * Create and return an e-mail {@link Session} to the SMTP server.
	 */
	private Session getSession()
	{
		// Configure session parameters
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.port", "587");

		// Retrieve Authenticator for authenticated message sending
		Authenticator authenticator = new Authenticator();
		properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());

		return Session.getDefaultInstance(properties, authenticator);
	}

	/**
	 * Custom {@link javax.mail.Authenticator} for e-mail message
	 * {@link Session}.
	 */
	private class Authenticator extends javax.mail.Authenticator
	{
		private PasswordAuthentication authentication;

		public Authenticator()
		{
			// Create PasswordAuthentification with username and password.
			authentication = new PasswordAuthentication("wfm-sender@web.de", "gE5spiSs");
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication()
		{
			return authentication;
		}
	}
}
