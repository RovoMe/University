package dst3.dynload.sample;

import dst3.depinj.Component;
import dst3.depinj.ComponentId;
import dst3.depinj.Inject;

@Component
public class F
{
	@ComponentId
	private Long id;
	
	@Inject
	private G g;
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Long getId()
	{
		return this.id;
	}
	
    public void setG(G g)
    {
        this.g = g;
    }
    
    public G getG()
    {
        return this.g;
    }
	
	public void output()
	{
		System.out.print("F --> "); g.output();
		System.out.println("F: "+id);
	}
}
