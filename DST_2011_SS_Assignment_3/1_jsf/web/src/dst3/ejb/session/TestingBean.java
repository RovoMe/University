package dst3.ejb.session;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dst3.ejb.entity.*;
import dst3.ejb.interfaces.TestingLocal;

@Stateless
@Local(TestingLocal.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TestingBean implements TestingLocal
{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void init()
	{		
		Computer comp1 = new Computer("Rechner 1", 4, "AUT-VIE@11201"); em.persist(comp1);
		Computer comp2 = new Computer("Rechner 2", 4, "AUT-VIE@11202"); em.persist(comp2);
		Computer comp3 = new Computer("Rechner 3", 4, "AUT-VIE@10401");	em.persist(comp3);
		Computer comp4 = new Computer("Dortmund 1", 2, "GER-DTM@441351"); em.persist(comp4);
		Computer comp5 = new Computer("Dortmund 2", 2, "GER-DTM@441352"); em.persist(comp5);
		
		List<Computer> computers1 = new ArrayList<Computer>();
		computers1.add(comp1);
		computers1.add(comp2);
		computers1.add(comp3);
		
		List<Computer> computers2 = new ArrayList<Computer>();
		computers2.add(comp4);
		computers2.add(comp5);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		Address a1 = new Address("Serverallee", "Dortmund", "44135");
		Address a2 = new Address("Admin-Strasse", "Wien", "1010");
		Address a3 = new Address("Fritzweg", "Wien","1210");
		
		User u = new User("Max", "Mustermann", "MMuster", "muster", "1234567", "1111111111", a1);
		em.persist(u);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		Grid grid1 = new Grid("GRID VIE", "AUT-VIE@1040", new BigDecimal("0.50")); em.persist(grid1);
		Grid grid2 = new Grid("GRID DTM", "GER-DTM@44135", new BigDecimal("1.2")); em.persist(grid2);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		Admin admin1 = new Admin("Adi", "Adminsky", a2); em.persist(admin1);
		Admin admin2 = new Admin("Hans-Dieter","Sysadmin",a3); em.persist(admin2);
		
		Cluster cluster1 = new Cluster("University Cluster", admin1, grid1, computers1);
		em.persist(cluster1);
		Cluster cluster2 = new Cluster("Dortmund Cluster", admin2, grid2, computers2);
		em.persist(cluster2);
		
		/////////////////////////////////////////////////////////////////////////////////
		
		Membership member1 = new Membership(grid1, u, 0.05); em.persist(member1);
	}
	
	public int unload()
	{
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
		em.clear();
		em.flush();
		return deletedRows;
	}
}
