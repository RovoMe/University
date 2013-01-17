package dst3.dynload.sample;

import dst3.depinj.Component;
import dst3.depinj.ComponentId;
import dst3.depinj.ScopeType;

@Component(scope=ScopeType.SINGLETON)
public class SimpleInterfaceImpl implements SimpleInterface
{
    @ComponentId
    private Long id;
    
    @Override
    public void fooBar()
    {
        System.out.println("[SimpleInterfaceImpl] id: "+id+" fooBar called!");
    }
    
}
