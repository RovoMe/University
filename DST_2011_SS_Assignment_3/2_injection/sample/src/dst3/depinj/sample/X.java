package dst3.depinj.sample;

import dst3.depinj.Component;
import dst3.depinj.ComponentId;
import dst3.depinj.ScopeType;

@Component(scope=ScopeType.SINGLETON)
public class X
{
    @ComponentId
    private Long id;
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public Long getId()
    {
        return this.id;
    }
    
    public void output()
    {
        System.out.println("X: "+id);
    }
}