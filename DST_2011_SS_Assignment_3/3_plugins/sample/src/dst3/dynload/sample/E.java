package dst3.dynload.sample;

import dst3.depinj.Component;
import dst3.depinj.ComponentId;
import dst3.depinj.Inject;
import dst3.depinj.ScopeType;

@Component(scope=ScopeType.SINGLETON)
public class E
{
    @ComponentId
    private Long id;
    
    @Inject
    private D d;
    
    public Long getId()
    {
        return this.id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public void setD(D d)
    {
        this.d = d;
    }
    
    public D getD()
    {
        return this.d;
    }
    
    public void output()
    {
    	System.out.print("E --> "); d.output();
        System.out.println("E: "+id);
    }
}
