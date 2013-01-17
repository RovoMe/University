package dst3.ejb.session;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import dst3.ejb.entity.PriceStep;
import dst3.ejb.interfaces.PriceManagementLocal;

@Singleton
@Local(PriceManagementLocal.class)
@Interceptors(AuditInterceptorBean.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Startup
public class PriceManagementBean implements PriceManagementLocal
{
	@PersistenceContext
	private EntityManager em;
	private List<PriceStep> priceSteps;
	
	@PostConstruct
	public void init()
	{
		TypedQuery<PriceStep> query = em.createQuery("SELECT ps FROM PriceStep ps ORDER BY ps.numberOfHistoricalJobs ASC",PriceStep.class);
		try
		{
			this.priceSteps = query.getResultList();
		}
		catch (NoResultException nrEx)
		{
			this.priceSteps = new ArrayList<PriceStep>();
		}
	}
	
	@Override
	@Lock(LockType.WRITE)
	public void storePriceStep(PriceStep step)
	{
		int index = 0;
		for (PriceStep ps : priceSteps)
		{
			if (step.getNumberOfHistoricalJobs() < ps.getNumberOfHistoricalJobs())
			{
				this.priceSteps.add(index,step);
				break;
			}
			else
				index++;
		}
		if (index == priceSteps.size())
			this.priceSteps.add(step);
		em.persist(step);
	}
	
	@Override
	@Lock(LockType.WRITE)
	public void storePriceStep(int numberOfHistoricalJobs, BigDecimal price)
	{
		PriceStep step = new PriceStep(numberOfHistoricalJobs, price);
		this.storePriceStep(step);
	}
	
	@Override
	@Lock(LockType.READ)
	public BigDecimal getFee(int jobs) throws IllegalArgumentException
	{
		if (jobs < 0)
			throw new IllegalArgumentException("There cannot be a price for a negative amount of executed jobs");
		
		BigDecimal minPrice = null;
		int maxAmount = 0;
		for (PriceStep step : priceSteps)
		{
			if (jobs <= step.getNumberOfHistoricalJobs())
				return step.getPrice();
			
			// if no PriceStep for a specified amount of jobs can be found,
			// assume that with increasing executed jobs the price gets lower and lower
			// so interpolate a price for PriceSteps beyond the yet defined upper 
			// amount of jobs
			if (maxAmount == 0 || maxAmount < step.getNumberOfHistoricalJobs())
				maxAmount = step.getNumberOfHistoricalJobs();
		
			if (minPrice == null || minPrice.doubleValue() > step.getPrice().doubleValue())
				minPrice = step.getPrice();
		}
		// f.e. maximum defined upper bound for jobs is 100, now a price step for 1000
		// jobs should be returned. So minPrice is only 10% of the last defined price
		return new BigDecimal(maxAmount/jobs).multiply(minPrice);
	}

}
