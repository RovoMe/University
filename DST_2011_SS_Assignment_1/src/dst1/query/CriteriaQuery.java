package dst1.query;

import org.hibernate.*;
import org.hibernate.criterion.*;
import dst1.model.*;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

public class CriteriaQuery
{	
	private EntityManager em = null;
	
	public CriteriaQuery(EntityManager em)
	{
		this.em = em;
	}
	
	/**
	 * Find all jobs that were created by a specified user which execute a given workflow 
	 * (user is given by his username, and the workflow as workflow name).
	 */
	public List<?> findJobsByUser(String username, String workflowName)
	{
		final Session session = em.unwrap(Session.class);
		try
		{
			Criteria job = session.createCriteria(Job.class, "j");
			Criteria user = job.createCriteria("user", "u");
			Criteria env = job.createCriteria("environment", "env");

			if (username != null && !username.equals(""))
				user.add(Restrictions.eq("username", username));
			
			if (workflowName != null && !workflowName.equals(""))
				env.add(Restrictions.eq("workflow", workflowName));

			return job.list();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Use Query by Example to find all jobs with JobStatus FINISHED and a specified start 
	 * and finish date.
	 */
	public List<?> findFinishedJobs(Date start, Date finish)
	{
		final Session session = em.unwrap(Session.class);
		try
		{			
			Criteria crit = session.createCriteria(Job.class, "j");
			
			Execution exec = new Execution();
			exec.setJobStatus(JobStatus.FINISHED);
			if (start != null)
				exec.setStartTime(start);
			if (finish != null)
				exec.setEndTime(finish);

			Criteria execCrit = crit.createCriteria("execution","exec");
			execCrit.add(Example.create(exec));
			
			return crit.list();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
}
