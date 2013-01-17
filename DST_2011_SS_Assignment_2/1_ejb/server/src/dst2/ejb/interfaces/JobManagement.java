package dst2.ejb.interfaces;

import java.util.List;
import dst2.ejb.exceptions.EmptyJobListException;
import dst2.ejb.exceptions.NotEnoughCPUsAvailableException;
import dst2.ejb.exceptions.UserNotLoggedInException;

public interface JobManagement
{
	public boolean login(String username, String password);
	public void logout();
	public void addJob(long gridId, int numberOfCPUs, String workflow, List<String> parameters) 
		throws IllegalArgumentException, NotEnoughCPUsAvailableException;
	public void removeAllJobsForGrid(long gridId) throws IllegalArgumentException;
	public int getAmountOfTemporaryStoredJobsForGrid(long gridId) throws IllegalArgumentException;
	public int getAmountOfJobsForGrid(long gridId) throws IllegalArgumentException;
	public void submitJobs(long gridId) throws IllegalArgumentException, EmptyJobListException, UserNotLoggedInException;
}
