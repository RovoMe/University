package dst2.ejb.interfaces;

import java.math.BigDecimal;
import dst2.ejb.entity.PriceStep;

/**
 * <p>A local interface which specifies method needed for the management of
 * prices for executing a certain amount of tasks. The price for an additional
 * tasks depends on the defined price steps and the number of tasks placed with
 * the system in the past.</p>
 * <p>{@link #getFee(int)} calculates the amount of money to pay for executing
 * a certain amount of tasks in dependence of the number of tasks placed with 
 * the system before and the defined prices for a certain amount of historically
 * executed tasks.</p>
 * 
 * @author Roman Vottner
 */
public interface PriceManagementLocal
{
	/**
	 * <p>Adds a new price step to the system. A price step defines how much
	 * money a user has to pay for executing his job depending on his business
	 * history with the system. A user who already placed some tasks with the
	 * system in the past is supposed to pay less for his new tasks than a new
	 * user.</p>
	 * <p>A step therefore marks the amount of necessary tasks to set the new
	 * price for a further task to a define value. </p>
	 * 
	 * @param step The new price step to add to the system, if a price step for
	 *             the specified amount already exists the step will be updated
	 *             to reflect the new price.
	 */
	public void storePriceStep(PriceStep step);
	
	/**
	 * <p>Adds a new price step to the system. A price step defines how much
	 * money a user has to pay for executing his job depending on his business
	 * history with the system. A user who already placed some tasks with the
	 * system in the past is supposed to pay less for his new tasks than a new
	 * user.</p>
	 * <p>A step therefore marks the amount of necessary tasks to set the new
	 * price for a further task to a define value. </p>
	 * 
	 * @param numberOfHistoricalJobs The number of tasks placed for a user in 
	 *                               history with the system
	 * @param price The new price a user will be charged for executing further
	 *              tasks depending on the number of tasks placed in history
	 *              with the system
	 */
	public void storePriceStep(int numberOfHistoricalJobs, BigDecimal price);
	
	/**
	 * <p>Calculates the fee a user has to pay for the tasks executed
	 * by the system.</p>
	 * 
	 * @param amountOfJobs The number of jobs executed by the system
	 * @return The fee the user has to pay for the execution of the 
	 *         placed tasks
	 */
	public BigDecimal getFee(int amountOfJobs);
}
