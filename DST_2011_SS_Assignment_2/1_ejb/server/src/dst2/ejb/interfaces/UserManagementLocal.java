package dst2.ejb.interfaces;

import dst2.ejb.entity.User;

/**
 * <p>A local interface for the management of users within the system.</p>
 * 
 * @author Roman Vottner
 */
public interface UserManagementLocal
{
	/**
	 * <p>Checks if the specified user name is currently available</p>
	 * 
	 * @param userName The user name to check for its availability within 
	 *                 the system
	 * @return true if the user name is currently available, false otherwise
	 */
	public boolean isUserNameAvailable(String userName);
	
	/**
	 * <p>Registers a new user with the system</p>
	 * 
	 * @param userName The name of the user within the system
	 * @param password The password of the user
	 * @param firstName The users real first name
	 * @param lastName The users real last name
	 * @param streetName The users street name
	 * @param city The users city he or she lives in
	 * @param zipCode The ZIP code of the city the user lives in
	 * @param acountNo The account number of the user
	 * @param bankCode The bank code of the users bank
	 * @return true if the user could be added to the system, false otherwise 
	 *         (f.e. in case of the user's name already exists)
	 */
	public boolean addUser(String userName, String password, String firstName, String lastName,
			String streetName, String city, String zipCode, String acountNo, String bankCode);
	
	/**
	 * <p>Tries to authenticate a user with the provided credentials to get
	 * access to certain extended functionalities.</p>
	 * 
	 * @param username The name of the user in the system
	 * @param password The users' password
	 * @return true if the users' name and password match a certain registered
	 *         user of the system; false otherwise 
	 */
	public boolean login(String username, String password);
	
	/**
	 * <p>Notifies the system that the session for the current user should be 
	 * ended</p>
	 */
	public void logout();
	
	/**
	 * <p>Defines if a system user has successfully logged in within this session.</p>
	 * @return true if the current user has logged in to the system successfully, 
	 *         false otherwise
	 */
	public boolean isLoggedIn();
	
	/**
	 * <p>Returns a {@link User} object for the currently logged in user</p>
	 * @return A {@link User} object representing the currently logged in user
	 */
	public User getLoggedInUser();
}
