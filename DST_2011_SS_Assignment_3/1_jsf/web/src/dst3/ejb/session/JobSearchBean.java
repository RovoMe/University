package dst3.ejb.session;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.ws.Action;
import dst3.ejb.dto.JobDTO;
import dst3.ejb.entity.Computer;
import dst3.ejb.entity.Execution;
import dst3.ejb.entity.JobStatus;
import dst3.ejb.interfaces.JobSearchLocal;

@Stateless
@WebService(serviceName="jobSearchService", targetNamespace="ws.ejb.dst3", name="JobSearchPT")
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public class JobSearchBean implements JobSearchLocal
{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@WebMethod(operationName="search", action="urn:Search")
	@Action(input="urn:Search", 
			output="urn:SearchResponse")
	@WebResult(name="searchResult")
	public List<JobDTO> search(@WebParam(name="searchString") String searchString)
	{
		List<JobDTO> result = new ArrayList<JobDTO>();
		List<Long> foundJobs = new ArrayList<Long>();
		TypedQuery<Computer> query = em.createQuery("SELECT c FROM Computer c LEFT JOIN FETCH c.execution WHERE LOWER(c.cluster.grid.name) LIKE :gridName", Computer.class);
		query.setParameter("gridName", "%"+searchString.toLowerCase()+"%");
		for (Computer comp : query.getResultList())
		{
			em.refresh(comp);
			for (Execution exec : comp.getExecution())
			{
				if (!exec.getJobStatus().equals(JobStatus.FINISHED) && 
                    // if a job requires more than one computer, 
                    // JobDTO might be already insert!
                        !foundJobs.contains(exec.getJob().getId()))
				{
					System.out.println(">>>>> search found unfinished job: "+exec.getJob().getId());
					JobDTO jobDTO = new JobDTO();
					jobDTO.setId(exec.getJob().getId());
					jobDTO.setStartTime(exec.getStartTime());
					jobDTO.setEndTime(exec.getEndTime());
					jobDTO.setUserName(exec.getJob().getUser().getUsername());
					result.add(jobDTO);
					foundJobs.add(exec.getJob().getId());
				}
			}
		}
		return result;
	}

}
