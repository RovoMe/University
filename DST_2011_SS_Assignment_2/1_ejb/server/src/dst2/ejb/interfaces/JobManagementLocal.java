package dst2.ejb.interfaces;

import java.util.List;
import java.util.Set;
import dst2.ejb.entity.Computer;
import dst2.ejb.entity.Grid;
import dst2.ejb.entity.User;
import dst2.ejb.exceptions.EmptyJobListException;
import dst2.ejb.exceptions.NotEnoughCPUsAvailableException;
import dst2.ejb.exceptions.UserNotLoggedInException;

public interface JobManagementLocal
{
	/**
	 * Adds a job to the temporary queue waiting for a user to {@link #submitJobs(long)} 
	 * @param gridId Unique identifier of the grid
	 * @param numberOfCPUs Amount of needed CPUs for this job
	 * @param workflow Name of the work flow
	 * @param parameters List of parameters needed for the work flow
	 * @throws IllegalArgumentException Thrown if an invalid Id for the grid was specified
	 * @throws NotEnoughCPUsAvailableException Thrown if the amount of needed CPUs for a job 
	 *                                         exceeds the number of currently available CPUs
	 */
	public void addJob(long gridId, int numberOfCPUs, String workflow, List<String> parameters) 
		throws IllegalArgumentException, NotEnoughCPUsAvailableException;
	
	/**
	 * Returns a list of all computers belonging to a certain grid
	 * @param grid The grid computers are linked to
	 * @return A list of all computers which belong to a certain grid. If none is available, 
	 *         an empty list is returned
	 */
	public List<Computer> findAllComputersForGrid(Grid grid);
	
	/**
	 * Returns a list of currently usable computers. If one computer is currently executing
	 * a job, it is not available.
	 * @param grid Unique identifier of a grid computers are linked to
	 * @return Returns a list of all currently available computers. If none is available at the
	 *         moment, an empty list is returned.
	 */
	public List<Computer> findUsableComputers(Grid grid);
	
	/**
	 * Returns the number of all CPUs of a grid
	 * @param grid Unique identifier of a grid
	 * @return Returns the number of CPUs the grid controls
	 */
	public int getNumberOfCPUsForGrid(Grid grid);
	
	/**
	 * Returns the number of currently available CPUs of a grid. If a computer is
	 * executing a job, all of its CPUs are not available, regardless of the saturation
	 * of its CPUs
	 * @param grid Unique identifier of a grid
	 * @return Returns the number of currently available CPUs the grid controls
	 */
	public int getNumberOfAvailableCPUsForGrid(Grid grid);
	
	/**
	 * Removes all of the temporarily stored jobs of a grid
	 * @param gridId Unique identifier of a grid
	 * @throws IllegalArgumentException Thrown if an invalid ID for the grid was specified
	 */
	public void removeAllJobsForGrid(long gridId) throws IllegalArgumentException;
	
	/**
	 * Returns the amount of currently stored jobs of a grid
	 * @param gridId Unique identifier of a grid
	 * @return Returns the amount of currently temporarily stored jobs of a grid
	 * @throws IllegalArgumentException Thrown if an invalid ID for the grid was specified
	 */
	public int getAmountOfTemporaryStoredJobsForGrid(long gridId) throws IllegalArgumentException;
	
	/**
	 * Returns the total amount of not-yet finished jobs for a grid
	 * @param gridId Unique identifier of a grid
	 * @return Returns the total amount of jobs for a grid
	 * @throws IllegalArgumentException Thrown if an invalid ID for the grid was specified
	 */
	public int getAmountOfJobsForGrid(long gridId) throws IllegalArgumentException;
	
	/**
	 * Returns a set of all currently available grids
	 * @return Returns a set of all currently available grids
	 */
	public Set<Grid> getAvailableGrids();
	
	/**
	 * Submits all temporarily stored jobs and hands it on to further scheduling. Requires the user
	 * to be logged in at the system
	 * @param gridId Unique identifier of a grid
	 * @throws IllegalArgumentException Thrown if an invalid ID for the grid was specified
	 * @throws EmptyJobListException Thrown if no job was temporarily stored for the grid
	 * @throws NotEnoughCPUsAvailableException Thrown if not enough CPUs are currently available
	 * @throws UserNotLoggedInException Thrown if the current user has not logged in before
	 */
	public void submitJobs(long gridId, User user) throws IllegalArgumentException, EmptyJobListException, NotEnoughCPUsAvailableException, UserNotLoggedInException;
}
