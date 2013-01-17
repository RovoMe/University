package dst2.client;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import dst2.ejb.dto.TaskDTO;

public class Computer implements MessageListener
{
	private String name;
	private String cluster;
	private String complexity;
	private Map<Long, TaskDTO> taskList;
	private Queue serverQueue;
	private Topic myTopic;
	private ConsoleOutput console;
	
	public Computer(String name, String cluster, String complexity)
	{
		this.name = name;
		this.cluster = cluster;
		this.complexity = complexity;
		this.taskList = new HashMap<>();

		Scanner scanner = null;
		Connection connection = null;
		Session session = null;
		try
		{
			InitialContext jndiContext = new InitialContext();
			ConnectionFactory factory = (ConnectionFactory)jndiContext.lookup("dst.Factory");
			this.myTopic = (Topic)jndiContext.lookup("topic.dst.ComputerTopic");
			this.serverQueue = (Queue)jndiContext.lookup("queue.dst.ServerQueue");
			
			connection = factory.createConnection();
			connection.setClientID(name);
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			javax.jms.TopicSubscriber consumer = session.createDurableSubscriber(this.myTopic, this.name, 
					"cmd = 'processTask' AND ratedBy = '"+this.cluster+"' AND TaskComplexity ='"+this.complexity+"'", false);
			consumer.setMessageListener(this);
			connection.start();
			
			scanner = new Scanner(System.in);
			String command = "";
			boolean done = false;
			System.out.println("\n Computer-Application("+name+")");
			
			this.console = new ConsoleOutput("computer("+name+"|"+cluster+")", System.out, System.err);
			Thread consolThread = new Thread(this.console);
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
	
	private void processInput(String command)
	{
		if (command.startsWith("processed "))
		{
			this.console.add("Sending server information on successfully processing task ");
			long taskId = new Long(command.substring("processed ".length())).longValue();
			System.out.print("TaskId: "+taskId);
			TaskDTO task = this.taskList.get(taskId);

			if (this.sendProcessedResponse(task, false))
			{
				this.console.addNewLine("\t\t\tDONE");
				this.taskList.remove(task.getId());
			}
			else
				this.console.addNewLine("\t\t\tFAILED");
		}
		else
			System.err.println("Unknown command. Known commands to this System: 'processed <task-id>' and 'stop'");
	}
		
	private boolean sendProcessedResponse(TaskDTO task, boolean transacted)
	{
		Properties prop = new Properties();
		prop.setProperty("cmd", "processed");
		return this.send(this.serverQueue, task, prop, transacted);
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
			msg.setJMSReplyTo(this.myTopic);
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
				ObjectMessage msg = (ObjectMessage)message;
				TaskDTO task = (TaskDTO)msg.getObject();
				
				this.taskList.put(task.getId(), task);
				
				this.console.addNewLine("Task received: "+task.toString());
			}
			else
				this.console.addError("Unknown message-type received: "+message.getClass().getName()+" with command: " +cmd);
		}
		catch (JMSException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		if (args.length == 3)
			new Computer(args[0], args[1], args[2]);
	}
}
