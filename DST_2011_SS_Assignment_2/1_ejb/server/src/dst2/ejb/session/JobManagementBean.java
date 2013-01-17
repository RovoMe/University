package dst2.ejb.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import dst2.ejb.entity.Cluster;
import dst2.ejb.entity.Computer;
import dst2.ejb.entity.Environment;
import dst2.ejb.entity.Execution;
import dst2.ejb.entity.Grid;
import dst2.ejb.entity.Job;
import dst2.ejb.entity.JobStatus;
import dst2.ejb.entity.User;
import dst2.ejb.exceptions.EmptyJobListException;
import dst2.ejb.exceptions.NotEnoughCPUsAvailableException;
import dst2.ejb.interfaces.JobManagement;
import dst2.ejb.interfaces.JobManagementLocal;
import dst2.ejb.interfaces.UserManagementLocal;
import dst2.ejb.exceptions.UserNotLoggedInException;
import javax.ejb.*;
import javax.interceptor.Interceptors;

@Stateful
@Remote(JobManagement.class)
@Local(JobManagementLocal.class)
@Interceptors(AuditInterceptorBean.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class JobManagementBean implements JobManagement, JobManagementLocal
{
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	@EJB
	private UserManagementLocal um;
	
	private boolean loggedIn = false;
	private User user;
	private Map<Grid, Map<Job,List<Computer>>> storedJobs = new HashMap<Grid, Map<Job, List<Computer>>>();
	
	@Override
	public boolean login(String username, String password)
	{
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
		query.setParameter("username", username);
		try
		{
			User user = query.getSingleResult();
			if (user.getPassword().equals(password))
			{
				this.loggedIn = true;
				this.user = user;
			}
			else
			{
				this.loggedIn = false;
				this.user = null;
			}
		}
		catch (NoResultException nrEx)
		{
			this.loggedIn = false;
			this.user = null;
		}
		return this.loggedIn;
	}
	
	@Override
	public void logout()
	{
		this.loggedIn = false;
	}

	@Override
	public void addJob(long gridId, int numberOfCPUs, String workflow, List<String> parameters) 
		throws IllegalArgumentException, NotEnoughCPUsAvailableException
	{
		if (storedJobs == null)
			storedJobs = new HashMap<Grid, Map<Job, List<Computer>>>();
		
		Grid grid = em.find(Grid.class, gridId);
		if (grid == null)
			throw new IllegalArgumentException("Grid unknown to the system");

		// find all computers which are currently not assigned to any execution
		// and calculate the maximum amount of CPUs available
		List<Computer> computersToUse = this.findUsableComputers(grid);
			
		for (Computer comp : computersToUse)
			System.out.println(">>>> usable Computer: "+comp.getId()+" "+comp.getName());
		
		int sumCPUs = 0;
		for (Computer computer : computersToUse)
			sumCPUs += computer.getCPUs();
		
		System.out.println(">>>> NumCPUs: "+sumCPUs+" available, "+numberOfCPUs+" needed\n");
		
		// check if enough CPUs are available at the moment
		if (sumCPUs >= numberOfCPUs)
		{				
			// assign free computers to execute the job
			// this would be a pretty decent job for a knapsack algorithm
			// but currently the first available computer is taken, then the next - 
			// until the needed CPUs for this task are reached.
			List<Computer> execComputers = new Vector<Computer>();
			int cpusUsed = 0;
			for (Computer computer : computersToUse)
			{
				cpusUsed += computer.getCPUs();
				System.out.println(">>>>> using Computer "+computer.getId()+" "+computer.getName());
				execComputers.add(computer);
				if (cpusUsed >= numberOfCPUs)
					break;
			}
			
			Environment env = new Environment(workflow, parameters);
			Job job = new Job(false, env, user);
			
			Map<Job, List<Computer>> computersForJob = storedJobs.get(grid);
			if (computersForJob == null)
				computersForJob = new HashMap<Job, List<Computer>>();
			computersForJob.put(job, execComputers);
			storedJobs.put(grid, computersForJob);
		}
		else
		{
			// inform the user that there aren't enough CPUs available at the moment
			throw new NotEnoughCPUsAvailableException("Not enough CPUs available: "+sumCPUs+" available, "+numberOfCPUs+" needed");
		}
	}

	public List<Computer> findAllComputersForGrid(Grid grid)
	{
		TypedQuery<Computer> query = em.createQuery("SELECT c FROM Computer c LEFT JOIN FETCH c.execution WHERE c.cluster.grid.id = :gridId",Computer.class);
		query.setParameter("gridId", grid.getId());
		return query.getResultList();
	}
	
	public List<Computer> findUsableComputers(Grid grid)
	{
		List<Computer> computersToUse = new ArrayList<Computer>();
		// as computer only get marked as used when the temporary list gets submitted
		// computers of a grid have first being collected from the db. afterwards 
		// computers stored in the temporary list have to be eliminated from those 
		// collected before.
		// the remaining computers are those available at the moment!			
		for (Computer computer : this.findAllComputersForGrid(grid))
		{
			boolean usable = true;
			// if the computer has no execution listed, it is assumed available
			if (computer.getExecution() == null || computer.getExecution().size() == 0)
			{
				// TODO: find solution for computer.getExecution() returning always null ...
				System.out.println(">>>>> Computer "+computer.getId()+" has no execution yet");
				// check if the computer is currently stored in the temporary list
				// if not, it is usable
				usable = !this.isComputerInTemporaryList(grid, computer);
			}
			else
			{
				System.out.println(">>>>> Computer has execution");
				// we have executions for a computer. if all executions are finished
				// the computer is available again
				for (Execution exec : computer.getExecution())
				{
					if (exec.getJobStatus() != JobStatus.FINISHED)
					{
						System.out.println(">>>>> Computer "+computer.getId()+" has an unfinished Job");
						usable=false;
						break;
					}
				}
				// check if it is stored in the temporary list
				if (usable)
				{
					System.out.println(">>>>> Computer usable, checking temporary list");
					usable = !this.isComputerInTemporaryList(grid, computer);
				}
			}
			System.out.println(">>>>> Computer "+computer.getId()+" is usable: "+usable);
			if (usable)
				computersToUse.add(computer);
		}
		return computersToUse;
	}
	
	private boolean isComputerInTemporaryList(Grid grid, Computer computer)
	{
		if (storedJobs.get(grid) != null && storedJobs.get(grid).size() > 0)
		{
			// run through all jobs and compare the temporarily assigned computers
			// with the computer we are looking for
			Set<Job> jobs = storedJobs.get(grid).keySet();
			if (jobs != null && jobs.size() > 0 )
			{
				for (Job job : jobs)
				{
					List<Computer> comps = storedJobs.get(grid).get(job);
					for (Computer comp : comps)
						if (comp.getId() == computer.getId())
							return true;
				}
			}
		}
		// no computer matching our target computer has been found
		return false;
	}

	public int getNumberOfCPUsForGrid(Grid grid)
	{
		int sumCPUs = 0;
		for (Computer comp : this.findAllComputersForGrid(grid))
			sumCPUs += comp.getCPUs();
		return sumCPUs;
	}
	
	public int getNumberOfAvailableCPUsForGrid(Grid grid)
	{
		int sumCPUs = 0;
		for (Computer comp : this.findUsableComputers(grid))
			sumCPUs += comp.getCPUs();
		return sumCPUs;
	}

	@Override
	public void removeAllJobsForGrid(long gridId) throws IllegalArgumentException
	{
		Grid grid = em.find(Grid.class, gridId);
		if (grid == null)
			throw new IllegalArgumentException("Grid unknown to the system");
		
		storedJobs.put(grid, new HashMap<Job, List<Computer>>());		
	}

	@Override
	public int getAmountOfTemporaryStoredJobsForGrid(long gridId) throws IllegalArgumentException
	{
		Grid grid = em.find(Grid.class, gridId);
		if (grid == null)
			throw new IllegalArgumentException("Grid unknown to the system");
		
		Map<Job, List<Computer>> computersForJob = storedJobs.get(grid);
		if (computersForJob != null)
			return computersForJob.keySet().size();
		return 0;
	}
	
	@Override
	public int getAmountOfJobsForGrid(long gridId) throws IllegalArgumentException
	{
		Grid grid = em.find(Grid.class, gridId);
		if (grid == null)
			throw new IllegalArgumentException("Grid unknown to the system");
		
		int foundJobs = 0;
		TypedQuery<Execution> jobQuery = em.createQuery("SELECT e FROM Execution e WHERE e.status <> dst2.ejb.entity.JobStatus.FINISHED", Execution.class);
		List<Execution> executions = jobQuery.getResultList();
		for (Execution exec : executions)
		{
			boolean executionInGrid = false;
			List<Computer> computers = exec.getComputers();
			for (Computer comp : computers)
			{
				TypedQuery<Cluster> clusterQuery = em.createQuery("SELECT c.cluster FROM Computer c WHERE c.id = :compId",Cluster.class);
				clusterQuery.setParameter("compId", comp.getId());
				Cluster cluster = clusterQuery.getSingleResult(); 
				Grid g = cluster.getGrid();
				if (g.getId() == gridId)
					executionInGrid = true;
			}
			if (executionInGrid)
				foundJobs++;
		}
		return foundJobs+this.getAmountOfTemporaryStoredJobsForGrid(gridId);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Remove(retainIfException=true)
	public void submitJobs(long gridId) throws IllegalArgumentException, EmptyJobListException, UserNotLoggedInException
	{
		if (loggedIn || um.isLoggedIn())
		{
			Grid grid = em.find(Grid.class, gridId);
			if (grid== null)
				throw new IllegalArgumentException("Grid unknown to the system");
			
			Map<Job, List<Computer>> computersForJob = storedJobs.get(grid);
			if (computersForJob != null)
			{
				for (Job job : computersForJob.keySet())
				{
					List<Computer> execComputers = computersForJob.get(job);
					if (execComputers != null && job != null)
					{
						if (user != null)
							job.setUser(user);
						else if(um.isLoggedIn())
							job.setUser(um.getLoggedInUser());
						
						Execution exec = new Execution(null, null, JobStatus.SCHEDULED, job, execComputers);
						em.persist(exec);
					}
					else
						throw new EmptyJobListException("No jobs have been added previously or list of jobs has already been executed");
				}
				em.flush();
				storedJobs.put(grid, new HashMap<Job, List<Computer>>());
			}
			else
				throw new EmptyJobListException("No storedJobs for grid: "+grid+" found");
		}
		else
			throw new UserNotLoggedInException("You have to be logged in to submit jobs");
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Remove(retainIfException=true)
	public void submitJobs(long gridId, User user) throws IllegalArgumentException, EmptyJobListException, UserNotLoggedInException
	{
		if (user != null)
		{
			Grid grid = em.find(Grid.class, gridId);
			if (grid== null)
				throw new IllegalArgumentException("Grid unknown to the system");
			
			Map<Job, List<Computer>> computersForJob = storedJobs.get(grid);
			if (computersForJob != null)
			{
				for (Job job : computersForJob.keySet())
				{
					List<Computer> execComputers = computersForJob.get(job);
					if (execComputers != null && job != null)
					{
						job.setUser(user);
						job = em.merge(job);
						Execution exec = new Execution(null, null, JobStatus.SCHEDULED, job, execComputers);
						em.persist(exec);
					}
					else
						throw new EmptyJobListException("No jobs have been added previously or list of jobs has already been executed");
				}
				em.flush();
				storedJobs.put(grid, new HashMap<Job, List<Computer>>());
			}
			else
				throw new EmptyJobListException("No storedJobs for grid: "+grid+" found");
		}
		else
			throw new UserNotLoggedInException("You have to be logged in to submit jobs");
	}

	@Override
	public Set<Grid> getAvailableGrids()
	{
		return storedJobs.keySet();
	}
}
