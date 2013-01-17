package dst2.ejb;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Properties;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dst2.ejb.dto.TaskDTO;
import dst2.ejb.entity.Task;
import dst2.ejb.entity.TaskComplexity;
import dst2.ejb.entity.TaskStatus;

@MessageDriven(mappedName = "queue.dst.ServerQueue", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class Server implements MessageListener
{
	@Resource(mappedName="dst.Factory")
	private ConnectionFactory factory;
	
	@Resource(mappedName="queue.dst.ClusterQueue")
	private Queue clusterQueue;
	@Resource(mappedName="topic.dst.ComputerTopic")
	private Topic computerTopic;
	@Resource(mappedName="queue.dst.SchedulerQueue")
	private Queue schedulerQueue;
	@Resource(mappedName="queue.dst.ServerQueue")
	private Queue myQueue;
	
	private Connection con;
	
	@PersistenceContext
	private EntityManager em;
	
	public Server()
	{

	}
	
	@Override
	public void onMessage(Message message)
	{
		boolean transacted = false;
		try
		{
			String cmd = message.getStringProperty("cmd");
			if(message instanceof ObjectMessage)
			{
				ObjectMessage objMsg = (ObjectMessage)message;

				// New job assigned by the scheduler
				if (cmd.equals("assign"))
				{
					System.out.println(">>>> Received message to assign a new job");
					this.gotAssignRequest(objMsg, transacted);
					System.out.println(">>>> assigment done");
				}
				// info request is received from scheduler
				else if (cmd.equals("info"))
				{
					System.out.println(">>>> Received message on information concerning a job");
					this.gotInfoRequest(objMsg, transacted);
					System.out.println(">>>> info sent");
				}
				// answer-message from the cluster
				else if (cmd.equals("accept_response"))
				{
					System.out.println(">>>> Received a response for accepting the job");
					this.gotAssignResponse(objMsg, transacted);
					System.out.println(">>>> Sent reply");
				}
				// yet an answer from the cluster
				else if (cmd.equals("deny_response"))
				{
					System.out.println(">>>> Received message that job was declined");						
					this.gotDenyRespone(objMsg, transacted);
					System.out.println(">>>> Sent response ");
				}
				// received from a computer
				else if (cmd.equals("processed"))
				{
					System.out.println(">>>> Received message that job was processed successfully");
					this.gotProcessResponse(objMsg, transacted);
					System.out.println(">>>> Sent reply");
				}
				else
					System.out.println(">>>> Unknown command received: "+cmd);
			}
			else
				System.err.println(">>>> Unknown message-type received: "+message.getClass().getName()+" with command: " +cmd);
		}
		catch (JMSException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void gotAssignRequest(ObjectMessage assignMsg, boolean transacted) throws JMSException
	{
		long jobId = (Long)assignMsg.getObject();

		Task task = new Task();
		task.setJobId(jobId);
		task.setTaskStatus(TaskStatus.ASSIGNED);
		task.setTaskComplexity(TaskComplexity.UNRATED);
		em.persist(task);
		em.flush();
		em.refresh(task);
		
		System.out.println(">>>> TaskId: "+task.getId()+" JobId: "+
				task.getJobId()+" TaskStatus: "+
				task.getTaskStatus()+" RatedBy: "+
				task.getRatedBy()+" TaskComplexity: "+
				task.getTaskComplexity());
			
		TaskDTO taskDTO = this.getTaskAsDTO(task);
		this.sendRatingRequest(taskDTO, transacted);
		
		System.out.print(">>>> Sending Reply");
		Properties prop = new Properties();
		prop.setProperty("cmd", "assign");
		this.reply(assignMsg, taskDTO, prop, transacted);
		System.out.println(">>>> sending done");
	}
	
	protected void gotInfoRequest(ObjectMessage infoMsg, boolean transacted) throws JMSException
	{
		long taskId = (Long)infoMsg.getObject();
		
		Task task = em.find(Task.class, taskId);
		if (task != null)
		{
			Properties prop = new Properties();
			prop.setProperty("cmd", "info");
			this.reply(infoMsg, this.getTaskAsDTO(task), prop, transacted);
		}
		else
			System.out.println(">>>> Could not find task with id: "+taskId);
	}
	
	protected void gotAssignResponse(ObjectMessage msg, boolean transacted) throws JMSException
	{
		TaskDTO taskDTO = (TaskDTO)msg.getObject();
		Task task = em.find(Task.class, taskDTO.getId());
		if (task != null)
		{
			task.setRatedBy(taskDTO.getRatedBy());
			task.setTaskComplexity(TaskComplexity.valueOf(taskDTO.getTaskComplexity()));
			task.setTaskStatus(TaskStatus.READY_FOR_PROCESSING);
			task = em.merge(task);
			taskDTO.setTaskStatus(TaskStatus.READY_FOR_PROCESSING.name());
			
			this.sendProcessRequest(taskDTO, transacted);
		}
		else
			System.err.println(">>>> Could not find a task with id: "+taskDTO.getId());
	}
	
	protected void gotDenyRespone(ObjectMessage msg, boolean transacted) throws JMSException
	{		
		TaskDTO taskDTO = (TaskDTO)msg.getObject();
		Task task = em.find(Task.class, taskDTO.getId());
		if (task != null)
		{
			task.setRatedBy(taskDTO.getRatedBy());
			task.setTaskStatus(TaskStatus.PROCESSING_NOT_POSSIBLE);
			task = em.merge(task);
			
			Properties prop = new Properties();
			prop.setProperty("cmd", "processingNotPossible");
			this.sendTo(schedulerQueue, taskDTO, prop, transacted);
		}
		else
			System.err.println(">>>> Could not find a task with id: "+taskDTO.getId());
	}

	protected void gotProcessResponse(ObjectMessage msg, boolean transacted) throws JMSException
	{
		TaskDTO taskDTO = (TaskDTO)msg.getObject();
		Long taskId = taskDTO.getId();
		
		Task task = em.find(Task.class, taskId);
		if (task != null)
		{
			task.setTaskStatus(TaskStatus.PROCESSED);
			task = em.merge(task);
			
			Properties prop = new Properties();
			prop.setProperty("cmd", "processed");
			this.sendTo(schedulerQueue, this.getTaskAsDTO(task), prop, transacted);
		}
		else
		{
			System.err.println(">>>> Could not retrieve Task id: "+taskId);
		}
	}
	
	protected void sendProcessRequest(TaskDTO task, boolean transacted)
	{
		try
		{
			System.out.print(">>>> Sending processing request to "+computerTopic.getTopicName());
			Properties prop = new Properties();
			prop.setProperty("cmd","processTask");
			prop.setProperty("ratedBy", task.getRatedBy());
			prop.setProperty("TaskComplexity", task.getTaskComplexity());
			this.sendTo(computerTopic, task, prop, transacted);
			
			System.out.println(">>>> DONE");
		}
		catch (JMSException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void sendRatingRequest(TaskDTO task, boolean transacted)
	{
		try
		{
			System.out.print(">>>> Sending rating request to "+clusterQueue.getQueueName());
			Properties prop = new Properties();
			prop.setProperty("cmd", "rateTask");
			this.sendTo(clusterQueue, task, prop, transacted);
			System.out.println(">>>> DONE");
		}
		catch (JMSException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void sendTo(Destination responseDest, Serializable obj, Properties properties, boolean transacted)
	{
		try
		{
			if (responseDest instanceof Queue)
				System.out.print(">>>>\t\tSending message to queue: "+((Queue)responseDest).getQueueName());
			else if (responseDest instanceof Topic)
				System.out.print(">>>>\t\tSending message to topic: "+((Topic)responseDest).getTopicName());
			
			if (this.con == null)
			{
				this.con = factory.createConnection();
				this.con.start();
			}
			Session session = this.con.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
			MessageProducer scheduler = session.createProducer(responseDest);
			
			ObjectMessage retMsg = session.createObjectMessage();
			Enumeration<?> keys = properties.propertyNames();
			while (keys.hasMoreElements())
			{
				String key = (String)keys.nextElement();
				String prop = properties.getProperty(key);
				retMsg.setStringProperty(key, prop);
			}
			retMsg.setObject(obj);
			retMsg.setJMSReplyTo(myQueue);
			scheduler.send(retMsg);
			scheduler.close();
			session.close();
			
			System.out.println(">>>>\t\tSending DONE");
		}
		catch (JMSException e)
		{
			System.err.println(">>>>\t\tSending-Error");
			e.printStackTrace();
		}
	}
	
	protected void reply(Message msg, TaskDTO task, Properties prop, boolean transacted)
	{
		try
		{
			this.sendTo(msg.getJMSReplyTo(), task, prop, transacted);
		}
		catch (JMSException e)
		{
			e.printStackTrace();
		}
	}

	private TaskDTO getTaskAsDTO(Task task)
	{
		return new TaskDTO(task.getId(), 
				task.getJobId(), 
				task.getTaskStatus().name(), 
				task.getRatedBy(), 
				task.getTaskComplexity().name());
	}
	
}
