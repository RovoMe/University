package dst1;

import dst1.model.*;
import dst1.query.CriteriaQuery;
import dst1.interceptor.SQLInterceptor;
import dst1.listener.LURPListener;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.Session;

public class Main 
{
	private static EntityManager em = null;
	
//	private static Date testStartTime = null;
//	private static Date testEndTime = null;
	
	private Main() 
	{
		super();
	}

	public static void main(String[] args) 
	{
		dst01();
		dst02a();
		dst02b();
		dst02c();
		dst03();
		dst04a();
		dst04b();
		dst04c();
		dst04d();
	}

	public static void dst01() 
	{		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("grid");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Computer comp1 = new Computer("Rechner 1", 4, "AUT-VIE@11201"); em.persist(comp1);
		Computer comp2 = new Computer("Rechner 2", 6, "AUT-VIE@11202"); em.persist(comp2);
		Computer comp3 = new Computer("Rechner 3", 8, "AUT-VIE@10401");	em.persist(comp3);
		Computer comp4 = new Computer("Dortmund 1", 8, "GER-DTM@441351"); em.persist(comp4);
		Computer comp5 = new Computer("Dortmund 2", 4, "GER-DTM@441352"); em.persist(comp5);
		
		List<Computer> computers1 = new Vector<Computer>();
		computers1.add(comp1);
		computers1.add(comp2);
		computers1.add(comp3);
		
		List<Computer> computers2 = new Vector<Computer>();
		computers2.add(comp4);
		computers2.add(comp5);
		
		List<Computer> execComputer = new Vector<Computer>();
		execComputer.add(comp2);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		Address a1 = new Address("Musterstraﬂe", "Wien", "1010"); em.persist(a1);
		Address a2 = new Address("Serverallee", "Dortmund", "44135"); em.persist(a2);
		Address a3 = new Address("Admin-Straﬂe", "Wien", "1010"); em.persist(a3);
		Address a4 = new Address("Fritzweg", "Wien","1210"); em.persist(a4);
		
		User u1 = new User("Roman","Vottner","Rovo","password","0105011", "1234567890", a1);
		em.persist(u1);
		User u2 = new User("Max", "Mustermann", "MMuster", "muster", "1234567", "1111111111", a2);
		em.persist(u2);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		Vector<String> param2 = new Vector<String>();
		param2.add("arg1");
		param2.add("arg2");
		
		Vector<String> param = new Vector<String>();
		param.add("a");
		param.add("b");
		param.add("c");
		
		Environment env = new Environment("test", param); em.persist(env);
		Environment env2 = new Environment("test2", param2); em.persist(env2);

		Job job1 = new Job(false, env, u1); em.persist(job1);

		Execution exec = new Execution(new Date(System.currentTimeMillis()-30*60*1000), null, JobStatus.RUNNING, job1, execComputer);
		em.persist(exec);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		Grid grid1 = new Grid("GRID VIE", "AUT-VIE@1040", new BigDecimal(0.50)); em.persist(grid1);
		Grid grid2 = new Grid("GRID GER", "GER-DTM@44135", new BigDecimal(1.2)); em.persist(grid2);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		Admin admin1 = new Admin("Adi", "Adminsky", a3); em.persist(admin1);
		Admin admin2 = new Admin("Hans-Dieter","Sysadmin",a4); em.persist(admin2);
		
		Cluster cluster1 = new Cluster("University Cluster", admin1, grid1, computers1);
		em.persist(cluster1);
		Cluster cluster2 = new Cluster("Dortmund Cluster", admin2, grid2, computers2);
		em.persist(cluster2);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		Membership member1 = new Membership(grid1, u1, 0.05); em.persist(member1);
		Membership member2 = new Membership(grid1, u2, 0.02); em.persist(member2);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		
		/////////////////////////////////////////////////////////////////////////////////
		
//		Computer comp1 = new Computer("Rechner 1", 4, "AUT-VIE@11201");
//		Computer comp2 = new Computer("Rechner 2", 6, "AUT-VIE@11202");
//		Computer comp3 = new Computer("Rechner 3", 8, "AUT-VIE@10401");	
//		Computer comp4 = new Computer("Musterrechner", 4, "AUT-VIE@1040");
//		Computer comp5 = new Computer("Frankfurt 1", 6, "GER-FRA@603111");
//		Computer comp6 = new Computer("Frankfurt 2", 8, "GER-FRA@603112");
//		Computer comp7 = new Computer("Dortmund 1", 8, "GER-DTM@441351");
//		Computer comp8 = new Computer("Dortmund 2", 4, "GER-DTM@441352");
//		Computer comp9 = new Computer("Dortmund 3", 5, "GER-DTM@441353");
//		
//		em.persist(comp1);		em.persist(comp2);		em.persist(comp3);
//		em.persist(comp4);		em.persist(comp5);		em.persist(comp6);
//		em.persist(comp7);		em.persist(comp8);		em.persist(comp9);
//		
//		/////////////////////////////////////////////////////////////////////////////////
//		
//		List<Computer> computers1 = new Vector<Computer>();
//		computers1.add(comp1);
//		computers1.add(comp2);
//		
//		List<Computer> computers2 = new Vector<Computer>();
//		computers2.add(comp3);
//		computers2.add(comp4);
//		
//		List<Computer> computers3 = new Vector<Computer>();
//		computers3.add(comp1);
//		computers3.add(comp3);
//		
//		List<Computer> computers4 = new Vector<Computer>();
//		computers4.add(comp5);
//		computers4.add(comp6);
//		
//		List<Computer> computers5 = new Vector<Computer>();
//		computers5.add(comp7);
//		computers5.add(comp8);
//		computers5.add(comp9);
//		
//		/////////////////////////////////////////////////////////////////////////////////
//		
//		Address a1 = new Address("Andersengasse", "Wien", "1120" );
//		Address a2 = new Address("Admin-Straﬂe", "Wien", "1010");
//		Address a3 = new Address("Musterstraﬂe", "Wien", "1010");
//		Address a4 = new Address("Fritzweg", "Wien","1210");
//		Address a5 = new Address("Serverallee", "Dortmund", "44135");
//		
//		User u1 = new User("Roman","Vottner","Rovo","password","0105011", "1234567890", a1);
//		User u2 = new User("Max", "Mustermann", "MMuster", "muster", "1234567", "1111111111", a3);
//		User u3 = new User("Lisa","Mustermann","LMuster", "lisa","2345678","2222222222",a3);
//		User u4 = new User("Fritz", "Fritsch", "Fritzi", "fRITZ", "8530271", "098765432", a4);
//		
//		em.persist(u1);
//		em.persist(u2);
//		em.persist(u3);
//		em.persist(u4);
//		
//		userID = u1.getId();
//		
//		/////////////////////////////////////////////////////////////////////////////////
//		
//		Vector<String> param2 = new Vector<String>();
//		param2.add("arg1");
//		param2.add("arg2");
//		
//		Vector<String> param = new Vector<String>();
//		param.add("a");
//		param.add("b");
//		param.add("c");
//		
//		Vector<String> param3 = new Vector<String>();
//		param.add("x=1");
//		param.add("y=2");
//		
//		Environment env = new Environment("test", param);
//		Environment env2 = new Environment("test2", param2);
//		Environment env3 = new Environment("test3", param3);
//		
//		em.persist(env);
//		em.persist(env2);
//		em.persist(env3);
//		
//		Job job1 = new Job(true, env, u1);
//		Job job2 = new Job(false, env2, u1);
//		Job job3 = new Job(true, env2, u2);
//		Job job4 = new Job(false, env, u1);
//		Job job5 = new Job(false, env3, u1);
//		Job job6 = new Job(false, env, u2);
//		Job job7 = new Job(true, env3, u3);
//		Job job8 = new Job(false, env, u4);
//		Job job9 = new Job(true, env, u4);
//		Job job10 = new Job(false, env3, u4);
//		
//		em.persist(job1);
//		em.persist(job2);
//		em.persist(job3);
//		em.persist(job4);
//		em.persist(job5);
//		em.persist(job6);
//		em.persist(job7);
//		em.persist(job8);
//		em.persist(job9);
//		em.persist(job10);
//		
//		Execution exec = new Execution(new Date(System.currentTimeMillis()-90000), 
//				new Date(System.currentTimeMillis()-6000), 
//				JobStatus.FINISHED, job1, computers2);
//			
//		Execution exec2 = new Execution(new Date(System.currentTimeMillis()-20000),
//				new Date(System.currentTimeMillis()), JobStatus.SCHEDULED, job2, computers1 );
//		
//		testStartTime = new Date(System.currentTimeMillis()-25000);
//		testEndTime = new Date(System.currentTimeMillis()-9000);
//		Execution exec3 = new Execution(testStartTime, testEndTime, JobStatus.FINISHED, job3, computers3);
//		
//		Execution exec4 = new Execution(new Date(System.currentTimeMillis()-26000), 
//				new Date(System.currentTimeMillis()), JobStatus.RUNNING, job5, computers5);
//		
//		Execution exec5 = new Execution(new Date(System.currentTimeMillis()-1), 
//				new Date(System.currentTimeMillis()), JobStatus.RUNNING, job6, computers4);
//		
//		Execution exec6 = new Execution(new Date(System.currentTimeMillis()-1), 
//				new Date(System.currentTimeMillis()), JobStatus.SCHEDULED, job8, computers2);
//		
//		Execution exec7 = new Execution(new Date(System.currentTimeMillis()-1), 
//				new Date(System.currentTimeMillis()), JobStatus.FINISHED, job4, computers1);
//		
//		em.persist(exec);
//		em.persist(exec2);
//		em.persist(exec3);
//		em.persist(exec4);
//		em.persist(exec5);
//		em.persist(exec6);
//		em.persist(exec7);
//		
//		/////////////////////////////////////////////////////////////////////////////////
//		
//		Grid grid1 = new Grid("GRID VIE", "AUT-VIE@1040", new BigDecimal(0.50));
//		Grid grid2 = new Grid("GRID GER", "GER-DTM@44135", new BigDecimal(1.2));
//		
//		em.persist(grid1);
//		em.persist(grid2);
//		
//		/////////////////////////////////////////////////////////////////////////////////
//		
//		Admin admin1 = new Admin("Adi", "Adminsky", a2);
//		Admin admin2 = new Admin("Hans-Dieter","Sysadmin",a5);
//		
//		em.persist(admin1);
//		em.persist(admin2);
//		
//		Cluster cluster1 = new Cluster("Home-Cluster", admin1,grid1, computers1);
//		Cluster cluster2 = new Cluster("University Cluster", admin1, grid1, computers2);
//		Cluster cluster3 = new Cluster("Frankfurt Cluster", admin2, grid2, computers4);
//		List<Cluster> subCluster = new Vector<Cluster>();
//		subCluster.add(cluster3);
//		Cluster cluster4 = new Cluster("Dortmund Cluster", admin2, grid2, computers5, subCluster);
//		
//		em.persist(cluster1);
//		em.persist(cluster2);
//		em.persist(cluster3);
//		em.persist(cluster4);
//		
//		/////////////////////////////////////////////////////////////////////////////////
//		
//		Membership member1 = new Membership(grid1, u1, 0.05);
//		Membership member2 = new Membership(grid1, u2, 0.02);
//		Membership member3 = new Membership(grid1, u3, 0);
//		Membership member4 = new Membership(grid2, u4, 0.10);
//		
//		em.persist(member1);		
//		em.persist(member2);		
//		em.persist(member3);
//		em.persist(member4);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		em.getTransaction().commit();
		
		System.out.println("Data created and persisted");
	}
	
	public static void dst02a() 
	{		
		try
		{
			/**
			 * SELECT u FROM User u LEFT JOIN u.memberships m LEFT JOIN m.grid g 
			 * 		WHERE g.name = :grid AND 
			 * 		(SELECT Count(j) FROM Job j WHERE j.user = u) >= :jobs
			 */
			long count = 2L;
			String gridName = "GRID VIE";
			System.out.println("1. Query:\nFind all users who have an active membership for a specific grid ("+gridName+
					") and assigned at least "+count+" jobs to it \n(given the name of the grid and the number of jobs).\n");
			Query query = em.createNamedQuery("findUserWithMembershipInGrid",User.class);
			query.setParameter("grid",gridName);
			query.setParameter("jobs", count);
			
			List<?> users = query.getResultList();
			if (users == null)
				System.out.println("List of users is null");
			if (users.isEmpty())
				System.out.println("List of users is empty");
			
			for (Object obj : users)
			{
				
				if (obj instanceof User)
				{
					User u = (User)obj;
					System.out.println("Found user: "+u.getFirstName()+" "+u.getLastName());
				}
				else
				{
					System.out.println("Found something: "+obj);
					Object[] ob = (Object[])obj;
					System.out.println("Found user: "+ob[0]+" with count: "+ob[1]);
				}
			}
			System.out.println();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		/**
		 * SELECT u, Count(u) AS c FROM User u LEFT JOIN u.jobs j 
		 * 		WHERE u.id = j.user.id GROUP BY u.id ORDER BY c DESC
		 */
		System.out.println("2. Query: Find the most active user(s), i.e., the user(s) who assigned the largest amount of jobs.\n");
		try
		{
			Query query2 = em.createNamedQuery("findMostActiveUser");
			@SuppressWarnings("unchecked")
			List<Object[]> activeUsers = query2.getResultList();
			System.out.println(activeUsers.size()+" User(s) found:");
			long maxJobs = 0;
			for (Object[] obj : activeUsers)
			{
				if (obj[0] instanceof User)
				{
					User u = (User)obj[0];
					Long anz = (Long)obj[1];
					if (maxJobs == 0)
						maxJobs = anz;
					if (maxJobs == anz)
						System.out.println("Found user: "+u.getFirstName()+" "+u.getLastName()+" with "+anz+" jobs!");
				}
			}
			System.out.println();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void dst02b() 
	{
		/**
		 * Find all computers in Vienna (location field starts with 'AUT-VIE') and, for each of them, 
		 * calculate the total usage (sum of end date minus start date for all executions of this computer).
		 */		
		try
		{
			String loc = "AUT-VIE";
			String location = "%"+loc+"%";
			System.out.println("3. Query: \nFind all computers in Vienna (location field starts with '"+loc+"') and, for each of them, \ncalculate the total usage (sum of end date minus start date for all executions of this computer).\n");
			Query query = em.createNamedQuery("findComputerByLocation");
			query.setParameter("location", location);
			List<?> result = query.getResultList();
			if (result == null)
				System.out.println("Result was null");
			else if (result.isEmpty())
				System.out.println("No result could be found");
			else
			{
				long totalTime = 0L;
				for (Object obj : result)
				{
					if(obj instanceof Computer)
					{
						long computerTotalTime = 0L;
						Computer c = (Computer)obj;
						em.refresh(c);
						List<Execution> exec = c.getExecution();
						if (exec == null || exec.size() == 0)
						{
							System.err.println("Can't retrieve executions for computer "+c.getName());
							continue;
						}
						for (Execution e : exec)
						{
							if (e.getEndTime() != null && e.getStartTime()!= null)
							{
								long time = e.getEndTime().getTime()-e.getStartTime().getTime();
								System.out.println(time);
								computerTotalTime += time;
							}
							else
							{
								System.out.println("No start and end time defined for Execution "+e.getId());
							}
						}
						System.out.println("Total usage for computer '"+c.getName()+"' is: "+computerTotalTime);
						totalTime += computerTotalTime;
					}
				}
				System.out.println("\nTotal usage of all computers located in "+loc+" is: "+totalTime+"\n");
			}
			System.out.println();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void dst02c() 
	{
		CriteriaQuery query = new CriteriaQuery(em);
		/**
		 * Find all jobs that were created by a specified user which execute a given workflow 
		 * (user is given by his username, and the workflow as workflow name).
		 */
		System.out.println("4. Query: \nFind all jobs that were created by a specified user which execute a given workflow \n(user is given by his username, and the workflow as workflow name\n");
		try
		{
			String name = "Rovo";
			String workflowName = "test";
			List<?> result = query.findJobsByUser(name, workflowName);
			if (result == null || result.size() == 0)
				System.err.println("No result was found");
			else
			{
				for (Object obj : result)
				{
					if (obj instanceof Job)
					{
						Job job = (Job)obj;
						System.out.println("Found job for '"+name+"' with workflow-name '"+workflowName+"' with job-id: "+job.getId());
					}
					else
						System.out.println(obj);
				}
			}
			System.out.println();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		/**
		 * Use Query by Example to find all jobs with JobStatus FINISHED and a specified 
		 * start and finish date.
		 */
		System.out.println("5. Query\nUse Query by Example to find all jobs with JobStatus FINISHED and a specified start and finish date.\n");
		try
		{
//			List<?> result = query.findFinishedJobs(testStartTime, testEndTime);
			List<?> result = query.findFinishedJobs(null, null);
			if (result == null || result.size() == 0)
				System.err.println("No result was found");
			else
			{
				for (Object obj : result)
				{
					if (obj instanceof Job)
					{
						Job job = (Job)obj;
						System.out.println("Found job with id: "+job.getId());
					}
					else
						System.out.println("Found somethin: "+obj.toString());
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println();
	}

	public static void dst03() 
	{		
		GregorianCalendar cal = new GregorianCalendar();
		
		System.out.println("create both a valid and an invalid instance for both entities. \n"+
				"Invalid instances should violate every single constraint. Use a javax.validation.Validator \n"+
				"to validate each instance and print out every existing error message.");
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		System.out.println("\nCreating new computer-object");
		
		cal.set(2011, 2, 1);
		Computer comp = new Computer("Test 1", 4, "AUT-VIE@1120");
		comp.setCreationDate(cal.getTime());
		comp.setLastUpdate(cal.getTime());
		
		Set<ConstraintViolation<Computer>> comp1Violations = validator.validate(comp);
		
		em.getTransaction().begin();
		if (comp1Violations.isEmpty())
		{
			em.persist(comp);
			//em.getTransaction().commit();
//			System.out.println("Object created and persisted successfully");
			System.out.println("Object created successfully");
			em.getTransaction().rollback();
			
		}
		else
		{
			em.getTransaction().rollback();
			System.out.println("Invalid object found, the object violates some restrictions.");
			
			for (ConstraintViolation<Computer> cv : comp1Violations)
				System.out.println("\t"+cv.getMessage()+": "+cv.getInvalidValue());
			System.out.println();
		}
	
		System.out.println("\nCreating new computer-object");
		
		cal.set(2011, 3, 1);
		Computer comp2 = new Computer("Tes2", 1, "daham");
		comp2.setCreationDate(cal.getTime());
		comp2.setLastUpdate(cal.getTime());
		
		Set<ConstraintViolation<Computer>> comp2Violations = validator.validate(comp2);
		em.getTransaction().begin();
		if (comp2Violations.isEmpty())
		{
			em.persist(comp2);
//			em.getTransaction().commit();
//			System.out.println("Object created and persisted successfully");
			System.out.println("Object created successfully");
			em.getTransaction().rollback();
		}
		else
		{
			em.getTransaction().rollback();
			System.out.println("Invalid object found, the object violates some restrictions.");
			
			for (ConstraintViolation<Computer> cv : comp2Violations)
				System.out.println("\t"+cv.getMessage()+": "+cv.getInvalidValue());
			System.out.println();
		}
		
	}

	public static void dst04a() 
	{
		EntityTransaction tx = em.getTransaction();
		try
		{
			System.out.println();
			tx.begin();
		
			User user = em.find(User.class, 1L );
			Job job = new Job(false,null, user);
			job.setNumCPUs(5);
			
			// EntityManager does not contain job - job is in state new
			System.out.println("job created! EntityManager contains job: "+em.contains(job));
		
			List<Job> jobs = user.getJobs();
			// EntityManager still does not contain job - job is still in state new
			jobs.add(job);
			System.out.println("job added! EntityManager contains job: "+em.contains(job));
			// now EntityManager contains job - job is in state managed
//			em.persist(job);
			em.flush();
			System.out.println("EntityManager flushed! EntityManager contains job: "+em.contains(job));
			// updating entity state - job is in state managed
			// as the change is within a managed context this change will get 
			// persisted to db after a call to flush
			job.setPaid(true);
			System.out.println("job updated! EntityManager contains job: "+em.contains(job));

			// save the job-Id to enable finding of the created job-instance later
			long jobID = job.getId();
			
			// load and manage a job with the specified ID - status of tmpJob is managed
			Job tmpJob = em.find(Job.class, jobID);
			System.out.println("Find the job in the DB! Is the Job marked as paid in the DB?: "+tmpJob.isPaid());

			// loads data from an Entity from the Database, any currently not written (committed or flushed)
			// changes will get deleted - status of job is managed
			em.refresh(job);
			System.out.println("job in EntityManager refreshed. EntityManager contains job: "+em.contains(job));
			
			// find and loads the job-entity and sets its status to managed
			tmpJob = em.find(Job.class, jobID);
			System.out.println("Find the job in the DB! Is the Job marked as paid in the DB?: "+tmpJob.isPaid());
			
			// change an attribute of our job-class to leafs the status as managed
			job.setPaid(true);
			System.out.println("job updated! Entity Manager contains job: "+em.contains(job));
			// now write the update to the db. state of job is still managed
			em.flush();
			System.out.println("EntityManager flushed! EntityManager contains job: "+em.contains(job));
			
			// Job is now in state detached
			em.detach(job);
			System.out.println("job detached! EntityManager contains job: "+em.contains(job));
			
			// find sets the status of tmpJob to managed
			tmpJob = em.find(Job.class, jobID);
			System.out.println("find the job in the DB! Is the Job marked as paid in the DB?: "+tmpJob.isPaid());
			
			// job status is currently detached - a merge will change status from detached to managed
			System.out.println("before merging job: EntityManager contains job: "+em.contains(job));
			job = em.merge(job);
			System.out.println("after merging job: EntityManager contains job: "+em.contains(job));
			
			// remove transfer the status into removed and detached
			// the data is only deleted from DB if a flush or a commit occurs!		
			// referenced data has to be removed beforehand removing job
			// else a 'deleted entity passed to persist' error will be thrown
			
			System.out.println("\tTrying to delete job: "+job.getId());
			jobs = user.getJobs();
			List<Job> tmp = new Vector<Job>();
			for (Job j : jobs)
				if (j.getId() == job.getId())
					tmp.add(j);
			for (Job j : tmp)
			{
				System.out.println("\tRemoving job "+j.getId()+" from user: "+user.getFirstName()+" "+user.getLastName());
				jobs.remove(j);
			}
			
			user.setJobs(jobs);
			// status should be removed afterwards, but it seems that the removed job is in state detached!
			/*
			 * EntityManager.remove() will remove an objects state from the database. Of course, your application 
			 * might still hold a reference to a deleted object. You can think of remove() as making a persistent 
			 * instance new (aka transient) again. It is not detached, and a merge would result in an insertion.
			 */
			em.remove(job);
			System.out.println("Removing job! EntityManager contains job: "+em.contains(job));
			
//			// this can be undone with a new persist call
//			em.persist(job);
//			System.out.println("Persisting job! EntityManager contains job: "+em.contains(job));
			
			em.flush();
			System.out.println("flushing EntityManager's data! EntityManager contains job: "+em.contains(job));
		
			// state of job is detached - so make it managed again
			job = em.merge(job);
			System.out.println("merging job! EntityManager contains job: "+em.contains(job));
			System.out.println("\tjob has id: "+job.getId());
			
			// and remove the managed job again - state will be detached and not removed afterwards
			em.remove(job);
			System.out.println("Removing job! EntityManager contains job: "+em.contains(job));
			
			tx.commit();
			// some infos on the web say, that persist only works for new transactions,
			// but this seems not to be the case as errors occur when persisting a removed
			// entity from a former transaction
			System.out.println("Starting new transaction");
			tx.begin();
			
			// status of job will be managed
			job = em.merge(job);
			System.out.println("merging job! EntityManager contains job: "+em.contains(job));
			System.out.println("\tjob has id: "+job.getId());
			
			tx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			tx.rollback();
		}
	}

	public static void dst04b() 
	{
		System.out.println("\nProvide an entity listener which overwrites the lastUpdate-field every time an already \nexisting computer entity is updated. To show that your solution works, update some of \nthe already persisted computer entities");
		em.getTransaction().begin();
		try
		{
			Computer comp = em.find(Computer.class, 1L);
			System.out.println("LastUpdate before update: "+comp.getLastUpdate());
			comp.setCPUs(5);
			em.merge(comp);
			for (long i=0; i<10000000; i++)
				;
			em.getTransaction().commit();
			System.out.println("LastUpdate after update: "+comp.getLastUpdate());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	public static void dst04c() 
	{
		System.out.println();
		LURPListener.getInstance().getStats();
	}

	public static void dst04d() 
	{
		SQLInterceptor interceptor = new SQLInterceptor();
		Session session = em.unwrap(Session.class);
		session = session.getSessionFactory().openSession(interceptor);
		System.out.println("\nImplement the provided dst1.interceptor.SQLInterceptor and count the number of selects for computers and executions.");
		interceptor.reset();
		dst02b();
		interceptor.getSummary();
	}
}