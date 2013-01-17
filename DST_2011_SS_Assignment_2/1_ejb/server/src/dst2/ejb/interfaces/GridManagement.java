package dst2.ejb.interfaces;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Future;
import dst2.ejb.dto.AuditDTO;
import dst2.ejb.dto.GridDTO;
import dst2.ejb.dto.TempJobsDTO;
import dst2.ejb.dto.UserDTO;
import dst2.ejb.exceptions.EmptyJobListException;
import dst2.ejb.exceptions.NotEnoughCPUsAvailableException;
import dst2.ejb.exceptions.UserNotLoggedInException;

public interface GridManagement
{
	public void init();
	public int unload();
	
	public List<GridDTO> getGridList();
	public long getIdForGrid(String name);
	
	public boolean isUserNameAvailable(String userName);
	public boolean registerUser(UserDTO newUser);
	public boolean login(String username, String password);
	public void logout();
	
	public void addJob(long gridId, int numberOfCPUs, String workflow, List<String> parameters) 
		throws IllegalArgumentException, NotEnoughCPUsAvailableException;
	
	public List<TempJobsDTO> getTempJobData();
	
	public String setPriceForJobNumber(int numJobs, BigDecimal price);
	
	public int getAmountOfTemporaryStoredJobsForGrid(long gridId);
	public int getAmountOfJobsForGrid(long gridId);
	public void submitJobs(long gridId) throws IllegalArgumentException, EmptyJobListException, NotEnoughCPUsAvailableException, UserNotLoggedInException;
	public void removeAllJobsForGrid(long gridId);
	
	public Future<String> getBill(String userName) throws IllegalArgumentException;
	
	public List<AuditDTO> getAuditLog();
	
	public boolean isUserLoggedIn();
}
