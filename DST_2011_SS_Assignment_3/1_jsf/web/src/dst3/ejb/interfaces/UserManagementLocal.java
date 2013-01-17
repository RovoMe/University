package dst3.ejb.interfaces;

import dst3.ejb.entity.User;

public interface UserManagementLocal
{
	public boolean isUserNameAvailable(String userName);
	public boolean addUser(String userName, String password, String firstName, String lastName,
			String streetName, String city, String zipCode, String acountNo, String bankCode);
	public boolean login(String username, String password);
	public void logout();
	public boolean isLoggedIn();
	public User getLoggedInUser();
}
