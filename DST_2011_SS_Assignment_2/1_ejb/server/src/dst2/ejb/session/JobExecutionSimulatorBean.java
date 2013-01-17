package dst2.ejb.session;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dst2.ejb.entity.Execution;
import dst2.ejb.entity.JobStatus;
import dst2.ejb.interfaces.JobExecutionSimulatorLocal;

@Stateless
@Local(JobExecutionSimulatorLocal.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JobExecutionSimulatorBean implements JobExecutionSimulatorLocal 
{
	@PersistenceContext
	private EntityManager em;
	@Resource
	private TimerService timerService;
	
	@Override
	public void init(String name)
	{
		System.out.println("Initializing JobExecutionSimulator - jobs get executed every minute");
		Calendar time = Calendar.getInstance();
		time.add(Calendar.SECOND, 60); // add 1 Minute to the current time
		Date date = time.getTime();
		this.unload(name);
		timerService.createTimer(date, 1000*60,name);
	}
	
	@Override
	public void unload(String name)
	{
		System.out.println("unloading JobExecutionSimulator");
		for (Object obj : timerService.getTimers())
		{
			Timer timer = (Timer)obj;
			String scheduled = (String) timer.getInfo();
			if (scheduled.equals("JobExecutionSimulator"))
				timer.cancel();
		}
	}
	
	@Timeout
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void maintenance(Timer timer) 
	{
		TypedQuery<Execution> query = em.createQuery("SELECT e FROM Execution e WHERE e.end IS null AND e.status = dst2.ejb.entity.JobStatus.RUNNING", Execution.class);
		query.setMaxResults(1);
		try
		{
			Execution exec = query.getSingleResult();
			exec.setEndTime(new Date(System.currentTimeMillis()));
			exec.setJobStatus(JobStatus.FINISHED);
			exec.getJob().setExecutionTime((int)(exec.getEndTime().getTime()-exec.getStartTime().getTime()));
		}
		catch (NoResultException nrEx)
		{
			
		}
		
		// Set the next scheduled job as running
		TypedQuery<Execution> nextExecution = em.createQuery("SELECT e FROM Execution e WHERE e.end IS NULL AND e.status = dst2.ejb.entity.JobStatus.SCHEDULED", Execution.class);
		nextExecution.setMaxResults(1);
		try
		{
			Execution nextExec = nextExecution.getSingleResult();
			nextExec.setStartTime(new Date());
			nextExec.setJobStatus(JobStatus.RUNNING);
		}
		catch(NoResultException nrEx)
		{
			
		}
	}

}
