package dst2.ejb.interfaces;

/**
 * <p>Is the basic remote interface for a testing bean. It provides just a few 
 * simple methods to test the functionality of an EJB system.</p>
 * 
 * @author Roman Vottner
 */
public interface Testing
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
	
	/**
	 * <p>Simple method to retrieve s 'test' string</p>
	 * @return A simple 'test' string
	 */
	public String test();
	
	/**
	 * <p>Returns the unique ID for a certain grid-name</p>
	 * @param name The name of the grid whose ID should be returned
	 * @return The unique ID of the provided grid name
	 */
	public long getIdForGrid(String name);
}
