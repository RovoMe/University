package dst1.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"bankCode", "accountNo"})})
/**
 * Find all users who have an active membership for a specific grid and assigned at least x jobs 
 * to it (given the name of the grid and the number of jobs).
 * 
 * Find the most active user(s), i.e., the user(s) who assigned the largest amount of jobs.
 */
@NamedQueries({
	@NamedQuery(
			name="findUserWithMembershipInGrid", 
			query="SELECT u FROM User u LEFT JOIN u.memberships m LEFT JOIN m.grid g WHERE g.name = :grid AND (SELECT Count(j) FROM Job j WHERE j.user = u) >= :jobs"
	),
	@NamedQuery(
			name="findMostActiveUser", 
			// thanks to: http://forums.digitalpoint.com/showthread.php?t=776336
			query="SELECT u, Count(u) AS c FROM User u LEFT JOIN u.jobs j WHERE u.id = j.user.id GROUP BY u.id ORDER BY c DESC"
	)
})
@org.hibernate.annotations.Table(appliesTo="User", indexes = { @org.hibernate.annotations.Index(name="idx_username", columnNames = { "username" } ) } )
public class User extends Person implements Serializable
{
	@Transient
	private static final long serialVersionUID = -1216951952080029385L;
	private String username;
	private String password;
	private String accountNo;
	private String bankCode;
	private List<Job> jobs;
	private List<Membership> memberships;
	
	public User()
	{
		
	}
	
	public User(String firstName, String lastName, String username, String password, String accountNo, String bankCode, Address address)
	{
		super(firstName, lastName, address);
		this.setUsername(username);
		this.setPassword(password);
		this.setAccountNo(accountNo);
		this.setBankCode(bankCode);
	}
	
	public void setUsername(String name)
	{
		this.username = name;
	}
	
	@Column(unique=true, nullable=false)
	public String getUsername()
	{
		return this.username;
	}
	
	public void setPassword(String pw)
	{
		this.password = pw;
	}
	
	public String getPassword()
	{
		return this.password;
	}

	public void setBankCode(String bankCode)
	{
		this.bankCode = bankCode;
	}
	
	public String getBankCode()
	{
		return this.bankCode;
	}
	
	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}
	
	public String getAccountNo()
	{
		return this.accountNo;
	}
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, mappedBy="user")
	public List<Job> getJobs()
	{
		return this.jobs;
	}
	
	public void setJobs(List<Job> jobs)
	{
		this.jobs = jobs;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")
	public List<Membership> getMemberships()
	{
		return this.memberships;
	}
	
	public void setMemberships(List<Membership> memberships)
	{
		this.memberships = memberships;
	}
}
