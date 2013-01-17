package dst2.ejb.interfaces;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Future;
import dst2.ejb.dto.AuditDTO;

public interface GeneralManagement
{
	public String setPriceForJobNumber(int amountOfHistoricalJobs, BigDecimal price);
	public BigDecimal getPriceForJobNumber(int amountOfHistoricalJobs);
	public Future<String> getBill(String username) throws IllegalArgumentException;
	public List<AuditDTO> getAuditLog();
}
