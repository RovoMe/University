package dst2.ejb.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import dst2.ejb.dto.AuditDTO;
import dst2.ejb.dto.GridDTO;
import dst2.ejb.dto.TempJobsDTO;
import dst2.ejb.dto.UserDTO;
import dst2.ejb.entity.Grid;
import dst2.ejb.entity.User;
import dst2.ejb.exceptions.EmptyJobListException;
import dst2.ejb.exceptions.NotEnoughCPUsAvailableException;
import dst2.ejb.exceptions.UserNotLoggedInException;
import dst2.ejb.interfaces.GeneralManagement;
import dst2.ejb.interfaces.GeneralManagementLocal;
import dst2.ejb.interfaces.GridManagement;
import dst2.ejb.interfaces.JobExecutionSimulatorLocal;
import dst2.ejb.interfaces.JobManagementLocal;
import dst2.ejb.interfaces.TestingLocal;
import dst2.ejb.interfaces.UserManagementLocal;

@Stateful
@Remote(GridManagement.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class GridManagementFacade implements GridManagement
{
	@EJB
	private TestingLocal testing;
	@EJB 
	private GeneralManagementLocal gml;
	@EJB
	private GeneralManagement gm;
	@EJB
	private JobManagementLocal jm;
	@EJB
	private JobExecutionSimulatorLocal jes;
	@EJB
	private UserManagementLocal um;
	@PersistenceContext
	private EntityManager em;
	private final String jobExecutionSimulator = "JobExecutionSimulator";
	
	private boolean loggedIn = false;
	private User user;
	
	/**
	 * TransaktionsManagement:
	 * Der ejb-Container löst die Verbindung zwischen Client und Stateless Session Bean
	 * nach jedem Methodenaufruf, auch wenn der Client weiterhin eine Referenz auf das 
	 * Business Interface der Bean hält. Möchte der Client, dass mehrere Methodenaufrufe
	 * einer Stateless Session Bean innerhalb desselben Transaktionscontextes abläuft,
	 * so muss dieser die Tansaktion selbst zur Verfügung stellen.
	 * 
	 * Bei Stateful Session Beans werden mehrere Methoden innerhalb einer Transaktion 
	 * ausgeführt, ohne dass die Transaktion vom Client verwaltet werden muss.
	 */
	
	public GridManagementFacade ()
	{
		
	}
	
	@Override
	public void init()
	{
		this.testing.init();
		this.jes.init(this.jobExecutionSimulator);
	}
	
	@Override
	public int unload()
	{
		this.jes.unload(this.jobExecutionSimulator);
		return this.testing.unload();
	}
	
	@Override
	public List<GridDTO> getGridList()
	{
		TypedQuery<Grid> query = this.em.createQuery("SELECT g FROM Grid g", Grid.class);
		return this.transformGrid(query.getResultList());
	}
	
	private List<GridDTO> transformGrid(List<Grid> grids)
	{
		List<GridDTO> dtoList = new ArrayList<>();
		for (Grid grid : grids)
		{
			GridDTO gridDTO = new GridDTO(grid.getId(), 
					grid.getName(), 
					grid.getLocation(),
					grid.getCostPerCPUMinute().doubleValue(), 
					this.jm.getNumberOfAvailableCPUsForGrid(grid));
//			System.out.println(">>>>> "+gridDTO.toString());
			dtoList.add(gridDTO);
		}
		return dtoList;
	}
	
	@Override
	public long getIdForGrid(String name)
	{
		TypedQuery<Grid> query = this.em.createQuery("SELECT g FROM Grid g WHERE g.name = :name", Grid.class);
		query.setParameter("name", name);
		Grid g = query.getSingleResult();
		return g.getId();
	}
	
	@Override
	public boolean isUserNameAvailable(String userName)
	{
		return this.um.isUserNameAvailable(userName);
	}

	@Override
	public boolean registerUser(UserDTO newUser)
	{
		if (newUser != null)
			return this.um.addUser(newUser.getUserName(), 
					newUser.getPassword(), 
					newUser.getFirstName(), 
					newUser.getLastName(), 
					newUser.getStreetName(), 
					newUser.getCity(), 
					newUser.getZIPCode(), 
					newUser.getAccountNo(), 
					newUser.getBankCode());
		return false;
	}

	@Override
	public boolean login(String username, String password)
	{
		this.loggedIn = this.um.login(username, password);
		this.user = this.um.getLoggedInUser();
		return loggedIn;
	}
	
	@Override
	@Remove(retainIfException=true)
	public void logout()
	{
		this.um.logout();
		this.loggedIn = false;
		this.user = null;
	}
	
	@Override
	public void addJob(long gridId, int numberOfCPUs, String workflow, List<String> parameters) 
		throws IllegalArgumentException, NotEnoughCPUsAvailableException
	{
		this.jm.addJob(gridId, numberOfCPUs, workflow, parameters);
	}
	
	@Override
	public List<TempJobsDTO> getTempJobData()
	{
		List<TempJobsDTO> tmpJobList = new ArrayList<>();
		for (Grid grid : this.jm.getAvailableGrids())
		{
			TempJobsDTO tempJobs = new TempJobsDTO();
			tempJobs.setGridId(grid.getId());
			tempJobs.setNumTempJobs(this.jm.getAmountOfTemporaryStoredJobsForGrid(grid.getId()));
			System.out.println(">>>>> "+tempJobs.toString());
			tmpJobList.add(tempJobs);
		}
		return tmpJobList;
	}
	
	@Override
	public String setPriceForJobNumber(int numJobs, BigDecimal price)
	{
		return this.gml.setPriceForJobNumber(numJobs, price);
	}

	@Override
	public int getAmountOfTemporaryStoredJobsForGrid(long gridId)
	{
		return this.jm.getAmountOfTemporaryStoredJobsForGrid(gridId);
	}

	@Override
	public int getAmountOfJobsForGrid(long gridId)
	{
		return this.jm.getAmountOfJobsForGrid(gridId);
	}

	@Override
	public void submitJobs(long gridId) throws IllegalArgumentException, EmptyJobListException, NotEnoughCPUsAvailableException, UserNotLoggedInException
	{
		this.jm.submitJobs(gridId, user);
	}

	@Override
	public void removeAllJobsForGrid(long gridId)
	{
		this.jm.removeAllJobsForGrid(gridId);
	}

	@Override
	public Future<String> getBill(String userName) throws IllegalArgumentException
	{
		return this.gm.getBill(userName);
	}

	@Override
	public List<AuditDTO> getAuditLog()
	{
		return this.gml.getAuditLog();
	}
	
	@Override
	public boolean isUserLoggedIn()
	{
		return this.loggedIn;
	}
}