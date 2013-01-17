package dst3.ejb.interfaces;

import java.math.BigDecimal;
import dst3.ejb.entity.PriceStep;

public interface PriceManagementLocal
{
	public void storePriceStep(PriceStep step);
	public void storePriceStep(int numberOfHistoricalJobs, BigDecimal price);
	public BigDecimal getFee(int amountOfJobs);
}
