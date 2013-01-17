package dst2.ejb.session;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import dst2.ejb.entity.Address;
import dst2.ejb.entity.User;
import dst2.ejb.interfaces.UserManagementLocal;

/**
 * <p>Stateful bean which manages users within the system.</p>
 * <p>It provides a method to add new users to the system, to check if a
 * user name is currently available and to manage the current session of
 * a user.</p>
 * 
 * @author Roman Vottner
 */
@Stateful
@Local(UserManagementLocal.class)
//@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UserManagementBean implements UserManagementLocal
{
	private boolean loggedIn;
	private User user;
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public boolean isUserNameAvailable(String userName)
	{
		TypedQuery<User> query = this.em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
		query.setParameter("username", userName);
		try
		{
			List<User> user = query.getResultList();
			if (user == null || user.size()== 0)
				return true;
			return false;
		}
		catch(NoResultException nsrEx)
		{
			return true;
		}
	}

	@Override
	public synchronized boolean addUser(String userName, String password, String firstName,
			String lastName, String streetName, String city, String zipCode,
			String accountNo, String bankCode)
	{
		if (this.isUserNameAvailable(userName))
		{
			Address address = new Address(streetName, city, zipCode);
			User user = new User(firstName, lastName, userName, password, accountNo, bankCode, address);
			try
			{
				this.em.persist(user);
				return true;
			}
			catch(Exception ex)
			{
				return false;
			}
		}
		else
			return false;
	}
	
	@Override
	public boolean login(String username, String password)
	{
		TypedQuery<User> query = this.em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
		query.setParameter("username", username);
		try
		{
			User user = query.getSingleResult();
			if (user.getPassword().equals(password))
			{
				this.loggedIn = true;
				this.user = user;
			}
			else
			{
				this.loggedIn = false;
				this.user = null;
			}
		}
		catch (NoResultException nrEx)
		{
			this.loggedIn = false;
			this.user = null;
		}
		return this.loggedIn;
	}
	
	@Override
	public void logout()
	{
		this.loggedIn = false;
		this.user = null;
	}
	
	@Override
	public boolean isLoggedIn()
	{
		return this.loggedIn;
	}
	
	@Override
	public User getLoggedInUser()
	{
		return this.user;
	}
}
