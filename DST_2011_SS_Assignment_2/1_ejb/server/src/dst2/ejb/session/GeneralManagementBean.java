package dst2.ejb.session;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Future;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import dst2.ejb.dto.AuditDTO;
import dst2.ejb.dto.ParameterDTO;
import dst2.ejb.entity.Audit;
import dst2.ejb.entity.Cluster;
import dst2.ejb.entity.Computer;
import dst2.ejb.entity.Execution;
import dst2.ejb.entity.Grid;
import dst2.ejb.entity.Job;
import dst2.ejb.entity.Membership;
import dst2.ejb.entity.Parameter;
import dst2.ejb.entity.User;
import dst2.ejb.interfaces.GeneralManagement;
import dst2.ejb.interfaces.GeneralManagementLocal;
import dst2.ejb.interfaces.PriceManagementLocal;

@Stateless
@Remote(GeneralManagement.class)
@Local(GeneralManagementLocal.class)
public class GeneralManagementBean implements GeneralManagement, GeneralManagementLocal
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private PriceManagementLocal priceManager;

	@Override
	public String setPriceForJobNumber(int numberOfJobs, BigDecimal price)
	{
		this.priceManager.storePriceStep( numberOfJobs, price);
		return "Setting new price for "+ numberOfJobs+" jobs to: "+price.toString();
	}
	
	@Override
	public BigDecimal getPriceForJobNumber(int amountOfJobs)
	{
		return this.priceManager.getFee(amountOfJobs);
	}
	
	@Override
	@Asynchronous
	public Future<String> getBill(String username) throws IllegalArgumentException
	{
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username",User.class);
		query.setParameter("username", username);
		User user = query.getSingleResult();
		if (user == null)
			throw new IllegalArgumentException("Unknown username");
		
		// Get the amount of jobs for the specified user
		TypedQuery<Job> jobAmountQuery = em.createQuery("SELECT j FROM Job j WHERE j.user.username = :username", Job.class);
		jobAmountQuery.setParameter("username", username);
		List<Job> jobHistory = jobAmountQuery.getResultList();
		double setupCost = this.priceManager.getFee(jobHistory.size()).doubleValue();
		
		// Get all open (not paid) jobs from a user
		TypedQuery<Job> jobQuery = em.createQuery("SELECT j FROM Job j JOIN FETCH j.execution WHERE j.execution.end IS NOT NULL AND j.isPaid = false AND j.user.username = :username", Job.class);
		jobQuery.setParameter("username", username);
		List<Job> notPaidJobs = jobQuery.getResultList();
		double totalPrice=0;
		String bill = "Bill for "+user.getFirstName()+" "+user.getLastName()+"\n";
		NumberFormat formatter = new DecimalFormat("###,###.##");
		for (Job job : notPaidJobs)
		{
			// find the grid the job got executed		
			TypedQuery<Execution> execQuery = em.createQuery("SELECT e FROM Execution e JOIN FETCH e.computers WHERE e.job.id = :jobId", Execution.class);
			
			execQuery.setParameter("jobId", job.getId());
			List<Execution> executions = execQuery.getResultList();
			
			Computer execComputer = null;
			for (Execution exec : executions)
			{
				for (Computer c : exec.getComputers())
				{
					execComputer = c;
					break;
				}
			}
			
			TypedQuery<Cluster> compQuery = em.createQuery("SELECT comp.cluster FROM Computer comp WHERE comp.id = :compId", Cluster.class);
			compQuery.setParameter("compId", execComputer.getId());
			Cluster cluster = compQuery.getSingleResult();
			
			TypedQuery<Grid> gridQuery = em.createQuery("SELECT c.grid FROM Cluster c WHERE c.id = :clusterId", Grid.class);
			gridQuery.setParameter("clusterId",cluster.getId());
			Grid grid = gridQuery.getSingleResult();
			
			em.refresh(job);
			int execTimeInSec = job.getExecutionTime()/(1000);
			int numCPUs = job.getNumCPUs();
			Execution exec = job.getExecution();
			int compUsed = exec.getComputers().size();
			double executionPrice = execTimeInSec*(grid.getCostPerCPUMinute().doubleValue()/60)*numCPUs;
			
			// if the user has an active membership with the grid
			// a discount has to be granted
			for (Membership m : user.getMemberships())
			{
				if (m.getGrid().equals(grid))
				{
					executionPrice-=executionPrice*m.getDiscount();
					break;
				}
			}	
			bill += "Job "+job.getId()+"\trun on "+compUsed+" computers\tSetup: "+formatter.format(setupCost)+"\tExecution: "+formatter.format(executionPrice)+" ("+execTimeInSec+" sec ON "+numCPUs+"CPUs)\n";
			totalPrice+=setupCost+executionPrice;
			job.setPaid(true);
		}
		bill += "\t\t\t\t\t\tTotal Price: "+formatter.format(totalPrice);
		return new AsyncResult<String>(bill);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<AuditDTO> getAuditLog()
	{
		TypedQuery<Audit> query = em.createQuery("SELECT a FROM Audit a ORDER BY a.time", Audit.class);
		List<Audit> log = query.getResultList();
		
		List<AuditDTO> logList = new Vector<AuditDTO>();
		for (Audit audit : log)
		{
			AuditDTO auditDTO = new AuditDTO();
			auditDTO.setId(audit.getId());
			auditDTO.setMethod(audit.getMethod());
			auditDTO.setTime(audit.getTime());
			auditDTO.setValue(audit.getValue());
			auditDTO.setFailure(auditDTO.getFailure());
			
			List<ParameterDTO> parameters = new Vector<ParameterDTO>();
			for(Parameter p : audit.getParameters())
			{	
				parameters.add(new ParameterDTO(p.getIndex(), p.getClassName(), p.getValue()));
			}
			auditDTO.setParameters(parameters);
			
			logList.add(auditDTO);
		}
		return logList;
	}
}
