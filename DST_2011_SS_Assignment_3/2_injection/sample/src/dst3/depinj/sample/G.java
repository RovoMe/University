package dst3.depinj.sample;

import dst3.depinj.Component;
import dst3.depinj.ComponentId;

@Component
public class G
{
	@ComponentId
	private Long id;
	
    public Long getId()
    {
        return this.id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public void output()
    {
    	System.out.println("G: "+id);
    }
}
