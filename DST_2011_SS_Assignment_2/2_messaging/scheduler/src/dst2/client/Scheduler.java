package dst2.client;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import dst2.ejb.dto.TaskDTO;

public class Scheduler implements MessageListener
{
	private static Scheduler instance = null;
	private Queue serverQueue;
	private Queue myQueue;
	private ConsoleOutput console;
	
	private Scheduler()
	{
		Scanner scanner = null;
		Connection connection = null;
		Session session = null;
		try
		{
			InitialContext jndiContext = new InitialContext();
			ConnectionFactory factory = (ConnectionFactory)jndiContext.lookup("dst.Factory");

			this.myQueue = (Queue)jndiContext.lookup("queue.dst.SchedulerQueue");
			this.serverQueue = (Queue)jndiContext.lookup("queue.dst.ServerQueue");
			
			connection = factory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageConsumer consumer = session.createConsumer(this.myQueue);
			consumer.setMessageListener(this);
			
			connection.start();
			scanner = new Scanner(System.in);
			String command = "";
			boolean done = false;
			
			System.out.println("\n Scheduler-Application");
			this.console = new ConsoleOutput("scheduler", System.out, System.err);
			Thread consolThread = new Thread(this.console);
			consolThread.start();
			
			
//			java.io.InputStreamReader isr = null;
//			java.io.BufferedReader input = null;
//			try
//			{
//				String line;
//				Process p = Runtime.getRuntime().exec("ant.bat -version");
//			    isr = new java.io.InputStreamreader(p.getInputStream());
//			    input = new java.io.BufferedReader(isr);
//			    while ((line = input.readLine()) != null) 
//			          System.out.println(line);
//			}
//			catch (IOException e)
//			{
//				e.printStackTrace();
//			}
//			finally
//			{
//				if (input != null)
//					input.close();
//				if (isr != null)
//					isr.close();
//			}
			
			connection.start();
			while (!done)
			{	
				if (scanner.hasNext())
					command = scanner.nextLine();
				
				if ("stop".equals(command))
					done = true;
				else
					this.processInput(command);
			}
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (JMSException e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.console.addNewLine("Exiting program");
			this.console.stop();
			
			if (session != null)
			{
				try 
				{
					session.close();
				} 
				catch (JMSException e) 
				{
					e.printStackTrace();
				}
			}
			if (connection != null)
			{
				try 
				{
					connection.close();
				} 
				catch (JMSException e) 
				{
					e.printStackTrace();
				}
			}
			if (scanner != null)
				scanner.close();
		}
		System.exit(0);
	}
	
	public static Scheduler getInstance()
	{
		if (instance == null)
			instance = new Scheduler();
		return instance;
	}
	
	private void processInput(String command)
	{
		boolean transacted = false;
		if (command.startsWith("assign "))
		{
			try
			{
				this.console.add("Assigning new job to the server");
				String cmd = command.substring(0, command.indexOf(" "));
				long jobId = new Integer(command.substring(cmd.length()+1).trim()).longValue();
				
				if (this.sendAssignRequest(jobId, transacted))
					this.console.addNewLine("\t\t\tDONE");
				else
					this.console.addError("\t\t\tFAILED");
			}
			catch (NumberFormatException nfEx)
			{
				nfEx.printStackTrace();
			}
		}
		else if (command.startsWith("info "))
		{
			try
			{	
				String cmd = command.substring(0, command.indexOf(" "));
				long jobId = new Integer(command.substring(cmd.length()+1).trim()).longValue();
				this.console.add("Sending request for informations on job: "+jobId);
				
				if (this.sendInfoRequest(jobId, transacted))
					this.console.addNewLine("\t\t\tDONE");
				else
					this.console.addError("\t\t\tFAILED");
			}
			catch (NumberFormatException nfEx)
			{
				nfEx.printStackTrace();
			}
		}
		else
			this.console.addError("Unknown command. Known commands to this System: 'assign <job-id>', 'info <job-id>' and 'stop'");
	}
	
	protected boolean sendAssignRequest(long jobId, boolean transacted)
	{
		Properties prop = new Properties();
		prop.setProperty("cmd", "assign");
		return this.send(serverQueue, jobId, prop, transacted);
	}
	
	protected boolean sendInfoRequest(long jobId, boolean transacted)
	{
		Properties prop = new Properties();
		prop.setProperty("cmd", "info");
		return this.send(serverQueue, jobId, prop, transacted);
	}
	
	protected boolean send(Destination dest, Serializable obj, Properties prop, boolean transacted)
	{
		boolean success = false;
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		try
		{
			InitialContext jndiContext = new InitialContext();
			ConnectionFactory factory = (ConnectionFactory)jndiContext.lookup("dst.Factory");
			connection = factory.createConnection();
			session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(dest);
			
			ObjectMessage msg = session.createObjectMessage();
			Enumeration<?> keys = prop.propertyNames();
			while(keys.hasMoreElements())
			{
				String key = keys.nextElement().toString();
				String val = prop.getProperty(key);
				msg.setStringProperty(key, val);
			}
			msg.setObject(obj);
			msg.setJMSReplyTo(this.myQueue);
			producer.send(msg);
			success = true;
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (JMSException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (producer != null)
			{
				try 
				{
					producer.close();
				} 
				catch (JMSException e) 
				{
					e.printStackTrace();
				}
			}
			if (session != null)
			{
				try 
				{
					session.close();
				} 
				catch (JMSException e) 
				{
					e.printStackTrace();
				}
			}
			if (connection != null)
			{
				try 
				{
					connection.close();
				} 
				catch (JMSException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return success;
	}

	@Override
	public void onMessage(Message message)
	{
		try
		{
			String cmd = message.getStringProperty("cmd");
			if (message instanceof ObjectMessage)
			{
				ObjectMessage objMsg = (ObjectMessage)message;
			
				if (("assign").equals(cmd))
				{
					TaskDTO task = (TaskDTO)objMsg.getObject();
					this.console.addNewLine("Task-id for job("+task.getJobId()+") is: "+task.getId());
				}
				else if ("info".equals(cmd))
				{
					TaskDTO task = (TaskDTO)objMsg.getObject();	
					this.console.addNewLine("Info received: "+task.toString());
				}
				else if ("processingNotPossible".equals(cmd))
				{
					TaskDTO task = (TaskDTO)objMsg.getObject();
					this.console.addNewLine("Notification on execution: "+task.getId()+". Task was denied by: "+task.getRatedBy());
				}
				else if ("processed".equals(cmd))
				{
					TaskDTO task = (TaskDTO)objMsg.getObject();
					this.console.addNewLine("Notification on execution: "+task.toString()+" has finished");
				}
				else
					this.console.addError("Unknown command found: "+cmd);
			}
			else
				this.console.addError("Unknown message-type received: "+message.getClass().getName()+" with command: " +cmd);
		}
		catch (JMSException jmsEx)
		{
			jmsEx.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		Scheduler.getInstance();
	}
}
