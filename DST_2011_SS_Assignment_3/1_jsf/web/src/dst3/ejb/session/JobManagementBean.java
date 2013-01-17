package dst3.ejb.session;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import dst3.ejb.entity.Cluster;
import dst3.ejb.entity.Computer;
import dst3.ejb.entity.Environment;
import dst3.ejb.entity.Execution;
import dst3.ejb.entity.Grid;
import dst3.ejb.entity.Job;
import dst3.ejb.entity.JobStatus;
import dst3.ejb.entity.User;
import dst3.ejb.exceptions.EmptyJobListException;
import dst3.ejb.exceptions.NotEnoughCPUsAvailableException;
import dst3.ejb.exceptions.UserNotLoggedInException;
import dst3.ejb.interfaces.JobManagement;
import dst3.ejb.interfaces.JobManagementLocal;
import javax.ejb.*;
import javax.interceptor.Interceptors;

@Stateful
@Local(JobManagementLocal.class)
@Remote(JobManagement.class)
@Interceptors(AuditInterceptorBean.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class JobManagementBean implements JobManagementLocal
{
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	private Map<Long, Map<Job,List<Computer>>> storedJobs = 
            Collections.synchronizedMap(new HashMap<Long, Map<Job, List<Computer>>>());
	
	public JobManagementBean()
	{

	}
	
	@Override
	public void addJob(long gridId, int numberOfCPUs, String workflow, List<String> parameters) 
		throws IllegalArgumentException, NotEnoughCPUsAvailableException
	{
		Grid grid = em.find(Grid.class, gridId);
		if (grid == null)
			throw new IllegalArgumentException("Grid unknown to the system");

		System.out.println(">>>>> Adding job for grid: "+grid.getId()+" "+grid.getName());
		
		// find all computers which are currently not assigned to any execution
		// and calculate the maximum amount of CPUs available
		List<Computer> computersToUse = this.findUsableComputers(gridId);
		
		int sumCPUs = 0;
		for (Computer computer : computersToUse)
			sumCPUs += computer.getCPUs();
		
		// check if enough CPUs are available at the moment
		if (sumCPUs >= numberOfCPUs)
		{				
			// assign free computers to execute the job
			// this would be a pretty decent job for a knapsack algorithm
			// but currently the first available computer is taken, then the next - 
			// until the needed CPUs for this task are reached.
			List<Computer> execComputers = new ArrayList<Computer>();
			int cpusUsed = 0;
			for (Computer computer : computersToUse)
			{
				cpusUsed += computer.getCPUs();
				execComputers.add(computer);
				if (cpusUsed >= numberOfCPUs)
					break;
			}
			
			Environment env = new Environment(workflow, parameters);
			Job job = new Job(false, env, null);
            em.persist(job);
            
			Map<Job, List<Computer>> computersForJob = this.storedJobs.get(grid.getId());
			if (computersForJob == null)
			{
//                System.out.println(">>>>> Creating new map for grid: "+grid.getName());
				computersForJob = Collections.synchronizedMap(new HashMap<Job, List<Computer>>());
			}
			computersForJob.put(job, execComputers);
//			System.out.println(">>>>> Currently stored jobs in grid("+gridId+"): "+computersForJob.keySet().size());
			this.storedJobs.put(gridId, computersForJob);
//			System.out.println(">>>>> Currently stored grids: "+this.storedJobs.size());
		}
		else
		{
            System.err.println("not enough CPSs available: "+sumCPUs+" available, "+numberOfCPUs+" needed");
			// inform the user that there aren't enough CPUs available at the moment
			throw new NotEnoughCPUsAvailableException("Not enough CPUs available: "+sumCPUs+" available, "+numberOfCPUs+" needed");
		}
	}

	@Override
	public List<Computer> findAllComputersForGrid(long gridId)
	{
		TypedQuery<Computer> query = em.createQuery("SELECT c FROM Computer c WHERE c.cluster.grid.id = :gridId",Computer.class);
		query.setParameter("gridId", gridId);
		return query.getResultList();
	}
	
	@Override
	public List<Computer> findUsableComputers(long gridId)
	{
		List<Computer> computersToUse = new ArrayList<Computer>();
		List<Computer> computers = this.findAllComputersForGrid(gridId);
		// as computer only get marked as used when the temporary list gets submitted
		// computers of a grid have first being collected from the db. afterwards 
		// computers stored in the temporary list have to be eliminated from those 
		// collected before.
		// the remaining computers are those available at the moment!	
        
		for (int i=0; i < computers.size(); i++)
		{
            Computer computer = computers.get(i);
            
			em.refresh(computer);
			boolean usable = true;
			// if the computer has no execution listed, it is assumed available
			if (computer.getExecution() == null || computer.getExecution().isEmpty())
			{
				// check if the computer is currently stored in the temporary list
				// if not, it is usable
				usable = !this.isComputerInTemporaryList(gridId, computer);
			}
			else
			{
				// we have executions for a computer. if all executions are finished
				// the computer is available again
                List<Execution> executions = computer.getExecution();
                // Due to ConcurrentModificationException I had to switch back to
                // old-school iteration as the new for-loop does not allow objects
                // to be modified, and as I need eventually updates to those objects
                // this step is neceessary 
                for (int j=0; j < executions.size(); j++)
                {
                    Execution exec = executions.get(j);
                    em.refresh(exec);
                    if (JobStatus.FINISHED != exec.getJobStatus())
                        usable=false;
                }
				// check if it is stored in the temporary list
				if (usable)
					usable = !this.isComputerInTemporaryList(gridId, computer);
			}
			
			if (usable)
				computersToUse.add(computer);
		}
		return computersToUse;
	}
	
	private boolean isComputerInTemporaryList(long gridId, Computer computer)
	{
		if (storedJobs.get(gridId) != null && storedJobs.get(gridId).size() > 0)
		{
			// run through all jobs and compare the temporarily assigned computers
			// with the computer we are looking for
			Set<Job> jobs = storedJobs.get(gridId).keySet();
			if (jobs != null && jobs.size() > 0 )
			{
				for (Job job : jobs)
				{
					List<Computer> comps = storedJobs.get(gridId).get(job);
					for (Computer comp : comps)
						if (comp.getId() == computer.getId())
							return true;
				}
			}
		}
		// no computer matching our target computer has been found
		return false;
	}

	@Override
	public int getNumberOfCPUsForGrid(long gridId)
	{
		int sumCPUs = 0;
		for (Computer comp : this.findAllComputersForGrid(gridId))
			sumCPUs += comp.getCPUs();
		return sumCPUs;
	}
	
	@Override
	public int getNumberOfAvailableCPUsForGrid(long gridId)
	{
		int sumCPUs = 0;
        List<Computer> computers = this.findUsableComputers(gridId);
        for (int i=0; i<computers.size(); i++)
        {
            Computer comp = computers.get(i);
            em.refresh(comp);
            sumCPUs += comp.getCPUs();
        }
		return sumCPUs;
	}
	
	@Override
	public void removeAllJobsForGrid(long gridId) throws IllegalArgumentException
	{
			Grid grid = em.find(Grid.class, gridId);
			if (grid == null)
				throw new IllegalArgumentException("Grid unknown to the system");
			
			storedJobs.put(gridId, Collections.synchronizedMap(new HashMap<Job, List<Computer>>()));	
	}

	@Override
	public int getAmountOfTemporaryStoredJobsForGrid(long gridId) throws IllegalArgumentException
	{
		Grid grid = em.find(Grid.class, gridId);
		if (grid == null)
			throw new IllegalArgumentException("Grid unknown to the system");
		
		Map<Job, List<Computer>> computersForJob = storedJobs.get(grid.getId());
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
		for (int i=0; i < executions.size(); i++)
		{
            Execution exec = executions.get(i);
			em.refresh(exec);
			boolean executionInGrid = false;
			List<Computer> computers = exec.getComputers();
            for (int j=0; j < computers.size(); j++)
			{
                Computer comp = computers.get(j);
				em.refresh(comp);
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
	public List<Grid> getAvailableGrids()
	{
		List<Grid> gridList = new ArrayList<Grid>();
		for (Long gridId : storedJobs.keySet())
			gridList.add(em.find(Grid.class, gridId));
		return gridList;
	}
	
	@Override
	public List<Grid> getAllGrids()
	{
		TypedQuery<Grid> query = em.createQuery("SELECT g FROM Grid g", Grid.class);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean isComputerUsable(List<Computer> computers)
	{
		for (int i=0; i < computers.size(); i++)
		{
            Computer comp = computers.get(i);
			em.refresh(comp);
			for (Execution exec : comp.getExecution())
				if (!exec.getJobStatus().equals(JobStatus.FINISHED))
					return false;
		}
		return true;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void submitAllJobs(User user) throws 
            UserNotLoggedInException, EmptyJobListException, NotEnoughCPUsAvailableException
	{
		if (user != null)
		{
			System.out.println(">>>>> submitting jobs for user: "+user.getUsername());
			for (Grid grid : this.getAvailableGrids())
			{
				Map<Job, List<Computer>> computersForJob = storedJobs.get(grid.getId());
				if (computersForJob != null)
				{
					for (Job job : computersForJob.keySet())
					{
						List<Computer> execComputers = computersForJob.get(job);
						boolean computersUsable = this.isComputerUsable(execComputers);
						if (execComputers != null && computersUsable)
						{
							job.setUser(user);
							job = em.merge(job);
							Execution exec = new Execution(null, null, JobStatus.SCHEDULED, job, execComputers);
							em.persist(exec);
						}
						else if (!computersUsable)
							throw new NotEnoughCPUsAvailableException("There are not enough CPUs available to fulfill the request to execute a job on grid "+grid.getId()+" | "+grid.getName());
						else
							throw new EmptyJobListException("No jobs have been added previously or list of jobs has already been executed");
					}
					em.flush();
					storedJobs.put(grid.getId(), Collections.synchronizedMap(new HashMap<Job, List<Computer>>()));
				}
				else
					throw new EmptyJobListException("No storedJobs for grid: "+grid+" found");
			}
		}
		else
			throw new UserNotLoggedInException("You have to be logged in to submit jobs");
	}
}
