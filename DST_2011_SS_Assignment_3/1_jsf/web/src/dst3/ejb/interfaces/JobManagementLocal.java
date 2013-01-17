package dst3.ejb.interfaces;

import java.util.List;
import dst3.ejb.entity.Computer;
import dst3.ejb.entity.Grid;
import dst3.ejb.entity.User;
import dst3.ejb.exceptions.EmptyJobListException;
import dst3.ejb.exceptions.NotEnoughCPUsAvailableException;
import dst3.ejb.exceptions.UserNotLoggedInException;

public interface JobManagementLocal
{
	/**
	 * Adds a job to the temporary queue waiting for a user to 
     * {@link #submitJobs(long)} 
	 * @param gridId Unique identifier of the grid
	 * @param numberOfCPUs Amount of needed CPUs for this job
	 * @param workflow Name of the work flow
	 * @param parameters List of parameters needed for the work flow
	 * @throws IllegalArgumentException Thrown if an invalid Id for the grid 
     *                                  was specified
	 * @throws NotEnoughCPUsAvailableException Thrown if the amount of needed 
     *                                         CPUs for a job exceeds the number 
     *                                         of currently available CPUs
	 */
	public void addJob(long gridId, int numberOfCPUs, 
                       String workflow, List<String> parameters) throws 
            IllegalArgumentException, 
            NotEnoughCPUsAvailableException;
	
	/**
	 * Returns a list of all computers belonging to a certain grid
	 * @param gridId The Id of the grid computers are linked to
	 * @return A list of all computers which belong to a certain grid. If none 
     *         is available an empty list is returned
	 */
	public List<Computer> findAllComputersForGrid(long gridId);
	
	/**
	 * Returns a list of currently usable computers. If one computer is 
     * currently executing a job, it is not available.
	 * @param gridId Unique identifier of a grid computers are linked to
	 * @return Returns a list of all currently available computers. If none is 
     *         available at the moment, an empty list is returned.
	 */
	public List<Computer> findUsableComputers(long gridId);
	
	/**
	 * Returns the number of all CPUs of a grid
	 * @param gridId Unique identifier of a grid
	 * @return Returns the number of CPUs the grid controls
	 */
	public int getNumberOfCPUsForGrid(long gridId);
	
	/**
	 * Returns the number of currently available CPUs of a grid. If a computer 
     * is executing a job, all of its CPUs are not available, regardless of the 
     * saturation of its CPUs
	 * @param gridId Unique identifier of a grid
	 * @return Returns the number of currently available CPUs the grid controls
	 */
	public int getNumberOfAvailableCPUsForGrid(long gridId);
	
	/**
	 * Removes all of the temporarily stored jobs of a grid
	 * @param gridId Unique identifier of a grid
	 * @throws IllegalArgumentException Thrown if an invalid ID for the grid 
     *                                  was specified
	 */
	public void removeAllJobsForGrid(long gridId) throws 
            IllegalArgumentException;
	
	/**
	 * Returns the amount of currently stored jobs of a grid
	 * @param gridId Unique identifier of a grid
	 * @return Returns the amount of currently temporarily stored jobs of a grid
	 * @throws IllegalArgumentException Thrown if an invalid ID for the grid was 
     *                                  specified
	 */
	public int getAmountOfTemporaryStoredJobsForGrid(long gridId) throws 
            IllegalArgumentException;
	
	/**
	 * Returns the total amount of not-yet finished jobs for a grid
	 * @param gridId Unique identifier of a grid
	 * @return Returns the total amount of jobs for a grid
	 * @throws IllegalArgumentException Thrown if an invalid ID for the grid 
     *                                  was specified
	 */
	public int getAmountOfJobsForGrid(long gridId) throws 
            IllegalArgumentException;
	
	/**
	 * Returns a list of all currently available grids this instance has some 
     * jobs stored for.
	 * @return Returns a list of all currently available grids
	 */
	public List<Grid> getAvailableGrids();
	
    /**
     * Returns a list of all grids known to the system. This method differs from
     * {@link #getAvailableGrids()} in that this list returns all grids of the
     * system whereas {@link #getAvailableGrids()} only returns those grids it
     * has stored some jobs for.
     * @return Returns all grids of the system.
     */
	public List<Grid> getAllGrids();

	/**
	 * Submits all temporarily stored jobs and hands it on to further scheduling. 
     * Requires the user to be logged in at the system
	 * @param user An object representing the logged in user
	 * @throws EmptyJobListException Thrown if no job was temporarily stored for 
     *                               the grid
	 * @throws NotEnoughCPUsAvailableException Thrown if not enough CPUs are 
     *                                         currently available
	 * @throws UserNotLoggedInException Thrown if the current user has not 
     *                                  logged in, and therefore user is null
	 */
	public void submitAllJobs(User user) throws 
            UserNotLoggedInException, 
            EmptyJobListException, 
            NotEnoughCPUsAvailableException;
}
