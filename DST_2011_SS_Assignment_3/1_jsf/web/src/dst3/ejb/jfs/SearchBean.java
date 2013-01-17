package dst3.ejb.jfs;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import dst3.ejb.ws.JobDTO;
import dst3.ejb.ws.JobSearchPT;
import dst3.ejb.ws.JobSearchService;

@ManagedBean(name="search")
@SessionScoped
public class SearchBean implements Serializable
{
	private static final long serialVersionUID = 6008809392963211313L;
	private String searchText;
	private List<JobDTO> resultList;
	
	public String getSearchText()
	{
		return this.searchText;
	}
	
	public void setSearchText(String searchText)
	{
		this.searchText = searchText;
	}
	
	public List<JobDTO> getResultList()
	{
		return this.resultList;
	}
	
	public void doSearch()
	{
		JobSearchService search = new JobSearchService();
		JobSearchPT portType = search.getJobSearchPTPort();
		resultList = portType.search(searchText);
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "m");
		try
		{
			context.getExternalContext().redirect(bundle.getString("result_page"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.searchText = "";
	}
}
