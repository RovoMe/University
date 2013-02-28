package wfm;

import wfm.mail.MailService;
import wfm.mail.MailServiceException;
import wfm.mail.MailServiceImpl;

public class MailSendTest
{
	public static void main(String[] args)
	{
		MailService mailService = new MailServiceImpl();
		mailService.setRecipient("yourEmail@yourDomain.com");
		mailService.setMessage("Test");
		
		try
		{
			System.out.print("Sending message ");
			mailService.sendMessage();
			System.out.println("... done");
		}
		catch (MailServiceException e)
		{
			System.out.println("... failed");
			e.printStackTrace();
		}
	}
}
