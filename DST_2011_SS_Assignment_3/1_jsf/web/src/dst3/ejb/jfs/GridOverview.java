package dst3.ejb.jfs;

import dst3.ejb.dto.GridDTO;
import dst3.ejb.entity.Grid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="overview")
@RequestScoped
public class GridOverview implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<GridDTO> dtoList = null;
    
    @ManagedProperty(value="#{jobManager}")
	private JobManagerBean jobManagerBean;
    @ManagedProperty(value="#{login}")
    private LoginBean loginBean;
    
    public void setJobManagerBean(JobManagerBean jobManagerBean)
    {
        this.jobManagerBean = jobManagerBean;
    }
    
    public void setLoginBean(LoginBean loginBean)
    {
        this.loginBean = loginBean;
    }
    
    @PostConstruct
    public void init()
    {
        System.out.println(">>>>> init GridOverview: "+loginBean.getUserName());
        // http://stackoverflow.com/questions/2090033/why-jsf-calls-getters-multiple-times
        this.dtoList = new ArrayList<GridDTO>();
        for (Grid grid : this.jobManagerBean.getAllGrids())
        {
            GridDTO gridDTO = new GridDTO(grid.getId(), 
                    grid.getName(), 
                    grid.getLocation(),
                    grid.getCostPerCPUMinute().doubleValue(), 
                    this.jobManagerBean.getNumberOfAvailableCPUsForGrid(grid.getId()));
            this.dtoList.add(gridDTO);
        }
    }
    
	// returns a list of currently existing grids in the system
	public List<GridDTO> getGridList()
	{	
		return dtoList;
	}	
}
