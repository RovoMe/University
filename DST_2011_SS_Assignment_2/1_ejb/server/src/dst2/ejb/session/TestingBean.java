package dst2.ejb.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import dst2.ejb.entity.*;
import dst2.ejb.interfaces.JobExecutionSimulatorLocal;
import dst2.ejb.interfaces.Testing;
import dst2.ejb.interfaces.TestingLocal;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * <p>TestingBean is, as it name suggests, a simple bean for testing the functionality
 * of an EJB bean.</p>
 * <p>The {@link #init()} method mocks some basic testing data and persists them using 
 * the injected {@link EntityManager}. {@link #test()} returns just a simple 'test' 
 * string to check if the bean is actually accessible while  {@link #getIdForGrid(String)} 
 * returns the ID of a certain grid name. All created and persisted data can be dumped 
 * using {@link #unload()}</p>
 * 
 * @author Roman Vottner
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(TestingLocal.class)
@Remote(Testing.class)
public class TestingBean implements Testing, TestingLocal
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private JobExecutionSimulatorLocal simulator;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void init()
	{		
		Computer comp1 = new Computer("Rechner 1", 4, "AUT-VIE@11201"); this.em.persist(comp1);
		Computer comp2 = new Computer("Rechner 2", 6, "AUT-VIE@11202"); this.em.persist(comp2);
		Computer comp3 = new Computer("Rechner 3", 8, "AUT-VIE@10401");	this.em.persist(comp3);
		Computer comp4 = new Computer("Dortmund 1", 8, "GER-DTM@441351"); this.em.persist(comp4);
		Computer comp5 = new Computer("Dortmund 2", 4, "GER-DTM@441352"); this.em.persist(comp5);
		
		List<Computer> computers1 = new ArrayList<Computer>();
		computers1.add(comp1);
		computers1.add(comp2);
		computers1.add(comp3);
		
		List<Computer> computers2 = new ArrayList<Computer>();
		computers2.add(comp4);
		computers2.add(comp5);
		
		List<Computer> execComputer = new ArrayList<Computer>();
		execComputer.add(comp1);
		
		List<Computer> execComputer2 = new ArrayList<Computer>();
		execComputer2.add(comp5);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		Address a1 = new Address("Musterstrasse", "Wien", "1010");
		Address a2 = new Address("Serverallee", "Dortmund", "44135");
		Address a3 = new Address("Admin-Strasse", "Wien", "1010");
		Address a4 = new Address("Fritzweg", "Wien","1210");
		
		User u1 = new User("Roman","Vottner","Rovo","password","0105011", "1234567890", a1);
		this.em.persist(u1);
		User u2 = new User("Max", "Mustermann", "MMuster", "muster", "1234567", "1111111111", a2);
		this.em.persist(u2);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		List<String> param2 = new ArrayList<String>();
		param2.add("arg1");
		param2.add("arg2");
		
		List<String> param = new ArrayList<String>();
		param.add("a");
		param.add("b");
		param.add("c");
		
		Environment env = new Environment("test", param); this.em.persist(env);
		Environment env2 = new Environment("test2", param2); this.em.persist(env2);

		Job job1 = new Job(false, env, u1); this.em.persist(job1);
		Job job2 = new Job(false, env2, u2); this.em.persist(job2);
		
		Execution exec = new Execution(new Date(System.currentTimeMillis()-30*60*1000), null, JobStatus.RUNNING, job1, execComputer);
		this.em.persist(exec);
		Execution exec2 = new Execution(null, null, JobStatus.SCHEDULED, job2, execComputer2);
		this.em.persist(exec2);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		Grid grid1 = new Grid("GRID VIE", "AUT-VIE@1040", new BigDecimal(0.50)); this.em.persist(grid1);
		Grid grid2 = new Grid("GRID GER", "GER-DTM@44135", new BigDecimal(1.2)); this.em.persist(grid2);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		Admin admin1 = new Admin("Adi", "Adminsky", a3); this.em.persist(admin1);
		Admin admin2 = new Admin("Hans-Dieter","Sysadmin",a4); this.em.persist(admin2);
		
		Cluster cluster1 = new Cluster("University Cluster", admin1, grid1, computers1);
		this.em.persist(cluster1);
		Cluster cluster2 = new Cluster("Dortmund Cluster", admin2, grid2, computers2);
		this.em.persist(cluster2);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		Membership member1 = new Membership(grid1, u1, 0.05); this.em.persist(member1);
		Membership member2 = new Membership(grid1, u2, 0.02); this.em.persist(member2);
		
		/////////////////////////////////////////////////////////////////////////////////
				
		this.simulator.init("test");
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public String test()
	{
		return "test";
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long getIdForGrid(String name)
	{
		TypedQuery<Grid> query = this.em.createQuery("SELECT g FROM Grid g WHERE g.name = :name", Grid.class);
		query.setParameter("name", name);
		Grid g = query.getSingleResult();
		
		return g.getId();
	}

	@Override
	public int unload()
	{
		this.simulator.unload("test");
		
		int deletedRows = 0;
		deletedRows += em.createQuery("DELETE FROM Computer").executeUpdate();
		deletedRows += em.createQuery("DELETE FROM Execution").executeUpdate();
		deletedRows += em.createQuery("DELETE FROM Job").executeUpdate();
		deletedRows += em.createQuery("DELETE FROM Membership").executeUpdate();
		deletedRows += em.createQuery("DELETE FROM User").executeUpdate();
		deletedRows += em.createQuery("DELETE FROM Environment").executeUpdate();
		deletedRows += em.createQuery("DELETE FROM Cluster").executeUpdate();
		deletedRows += em.createQuery("DELETE FROM Grid").executeUpdate();
		deletedRows += em.createQuery("DELETE FROM Admin").executeUpdate();
		deletedRows += em.createQuery("DELETE FROM Person").executeUpdate();
		deletedRows += em.createQuery("DELETE FROM PriceStep").executeUpdate();
		deletedRows += em.createQuery("DELETE FROM Audit").executeUpdate();
		this.em.clear();
		this.em.flush();
		
		return deletedRows;
	}
}
