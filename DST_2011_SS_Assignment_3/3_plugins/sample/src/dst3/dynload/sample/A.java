package dst3.dynload.sample;

import dst3.depinj.Component;
import dst3.depinj.ComponentId;
import dst3.depinj.Inject;
import dst3.depinj.ScopeType;

@Component(scope=ScopeType.SINGLETON)
public class A extends F
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
    	super.setId(id);
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
    	System.out.print("A == "); super.output();
    	System.out.print("A ==> "); getG().output();
    	System.out.print("A --> "); d.output();
        System.out.println("A: "+id);
    }
}
