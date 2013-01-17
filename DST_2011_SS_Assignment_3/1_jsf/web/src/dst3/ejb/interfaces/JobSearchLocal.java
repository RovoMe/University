package dst3.ejb.interfaces;

import java.util.List;
import dst3.ejb.dto.JobDTO;

public interface JobSearchLocal
{
	public List<JobDTO> search(String searchString);
}
