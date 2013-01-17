package dst2.client;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import dst2.ejb.dto.TaskDTO;

public class Cluster implements MessageListener
{
	private String name;
	private TaskDTO task;
	private Message message;
	private MessageConsumer consumer;
	private Queue serverQueue;
	private Queue myQueue;
	private ConsoleOutput console;
	
	public Cluster(String name)
	{
		this.name = name;
		
		Scanner scanner = null;
		Connection connection = null;
		Session session = null;
		try
		{
			InitialContext jndiContext = new InitialContext();
			ConnectionFactory factory = (ConnectionFactory)jndiContext.lookup("dst.Factory");

			this.myQueue = (Queue)jndiContext.lookup("queue.dst.ClusterQueue");
			this.serverQueue = (Queue)jndiContext.lookup("queue.dst.ServerQueue");
			
			connection = factory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			this.consumer = session.createConsumer(myQueue);
			this.consumer.setMessageListener(this);
			
			System.out.println("\n Cluster-Application("+name+")");
			String command = "";
			boolean done = false;

			scanner = new Scanner(System.in);
			this.console = new ConsoleOutput("cluster("+name+")", System.out, System.err);
			Thread consolThread = new Thread(console);
			consolThread.start();
			
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
	
	protected void processInput(String command) throws JMSException
	{
		boolean transacted = false;
		if (command.startsWith("accept "))
		{
			if (this.task != null)
			{
				String complexity = command.substring("accept ".length());
				if (complexity.toUpperCase().equals("EASY") || 
						complexity.toUpperCase().equals("CHALLENGING"))
				{
					this.task.setTaskComplexity(complexity);
					this.task.setRatedBy(this.name);
					
					this.sendProcessAccept(this.message.getJMSReplyTo(), this.task, transacted);
				}
				else
					this.console.addError("Complexity must either be 'EASY' or 'CHALLENGING'");
			}
			else
				this.console.addError("No data to accept available");
		}
		else if (command.equals("deny"))
		{
			
			if (this.task != null)
			{
				this.task.setRatedBy(this.name);
				this.sentProcessDeny(this.message.getJMSReplyTo(), this.task, transacted);
			}
			else
				this.console.addError("No data to deny available");
		}
		else
			this.console.addError("Unknown command. Known commands to this system: 'accept <task-complexity>', 'deny' and 'stop'");
	}
	
	protected void sendProcessAccept(Destination recipient, TaskDTO task, boolean transacted)
	{
		try
		{
			this.console.add("Sending processing acceptance to "+((Queue)recipient).getQueueName());
			Properties prop = new Properties();
			prop.setProperty("cmd", "accept_response");
			this.send(this.serverQueue, task, prop, transacted);
				
			this.console.addNewLine("\t\t\tDONE");
		}
		catch (JMSException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void sentProcessDeny(Destination recipient, TaskDTO task, boolean transacted)
	{
		try
		{
			this.console.add("Sending processing denial to "+((Queue)recipient).getQueueName());
			Properties prop = new Properties();
			prop.setProperty("cmd", "deny_response");
			this.send(this.serverQueue, task, prop, transacted);
				
			this.console.addNewLine("\t\t\tDONE");
		}
		catch (JMSException e)
		{
			e.printStackTrace();
		}
	}

	protected void send(Destination dest, Serializable obj, Properties prop, boolean transacted)
	{
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
	}
	
	@Override
	public void onMessage(Message message)
	{
		try
		{
			if ("rateTask".equals(message.getStringProperty("cmd")))
			{
				if (message instanceof ObjectMessage)
				{
					ObjectMessage objMsg = (ObjectMessage)message;
					TaskDTO task = (TaskDTO)objMsg.getObject();
					
					this.console.addNewLine("Please rate the task: ");
					this.console.addNewLine(task.toString());
					
					this.task = task;
					this.consumer.setMessageListener(null);
					this.message = message;
				}
			}
		}
		catch (JMSException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		if (args.length == 1)
			new Cluster(args[0]);
	}
}
