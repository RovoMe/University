package dst2.ejb.interfaces;

/**
 * <p>Is the basic local interface for a testing bean. It provides just a few 
 * simple methods to test the functionality of an EJB system.</p>
 * 
 * @author Roman Vottner
 */
public interface TestingLocal
{
	/**
	 * <p>Initializes a couple of mocked testing data and persists them using
	 * the provided infrastructure</p>
	 */
	public void init();
	
	/**
	 * <p>Deletes all previously persisted data from the backing database.</p>
	 * @return
	 */
	public int unload();
}
