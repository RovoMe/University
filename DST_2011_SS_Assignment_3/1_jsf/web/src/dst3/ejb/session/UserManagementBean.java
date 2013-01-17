package dst3.ejb.session;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateful;
//import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import dst3.ejb.entity.Address;
import dst3.ejb.entity.User;
import dst3.ejb.interfaces.UserManagementLocal;

@Stateful
@Local(UserManagementLocal.class)
//@Interceptors(AuditInterceptorBean.class)
//@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UserManagementBean implements UserManagementLocal
{
	@PersistenceContext
	private EntityManager em;
	private boolean loggedIn;
	private User user;
	
	@Override
	public boolean isUserNameAvailable(String userName)
	{
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
		query.setParameter("username", userName);
		System.out.println(">>>>> Username: "+userName);
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
	public boolean addUser(String userName, String password, String firstName,
			String lastName, String streetName, String city, String zipCode,
			String accountNo, String bankCode)
	{
		Address address = new Address(streetName, city, zipCode);
		User user = new User(firstName, lastName, userName, password, accountNo, bankCode, address);
		try
		{
			em.persist(user);
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	@Override
	public boolean login(String username, String password)
	{
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
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
