package dst3.ejb.interfaces;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Future;
import dst3.ejb.dto.AuditDTO;

public interface GeneralManagementLocal
{
	public String setPriceForJobNumber(int amountOfHistoricalJobs, BigDecimal price);
	public BigDecimal getPriceForJobNumber(int amountOfHistoricalJobs);
	public Future<String> getBill(String username) throws IllegalArgumentException,  Exception;
	public List<AuditDTO> getAuditLog();
}
