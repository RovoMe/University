/* Injection-Setup
 *   ( ==> = inheritance, --> injection)
 * 
 *        D <---+
 *  G     ^     |
 *  ^     |     E <---+    
 *  |     |     ^     |
 *  |     |     |     |
 *  F ==> A ==> B --> C
 *   
 */

package dst3.depinj.sample;

import dst3.depinj.Component;
import dst3.depinj.ComponentId;
import dst3.depinj.IInjectionController;
import dst3.depinj.InjectionControllerImpl;
import dst3.depinj.Inject;
import dst3.depinj.ScopeType;

@Component(scope=ScopeType.PROTOTYPE)
public class ControllerWithInjections
{
    @ComponentId
    private Long id;
    
    @Inject//(specificType = SimpleInterfaceImpl.class)
    private SimpleInterface si;
    
    public void setId(Long id)
    {
    	this.id = id;
    }
    
    public Long getId()
    {
    	return this.id;
    }
    
    public void callSi()
    {
        si.fooBar();
    }
    
    public static void taskA()
    {
        IInjectionController ic = InjectionControllerImpl.getInstance();
        ControllerWithInjections cwi = new ControllerWithInjections();
        ic.initialize(cwi);
        cwi.callSi();
        
        System.out.println("   ");
        System.out.println("INITIALIZING NEW B:  ");
        System.out.println("   ");
        
        B b = new B();
        ic.initialize(b);
        b.output();
        
        System.out.println("   ");
        System.out.println("INITIALIZING NEW A:   ");
        System.out.println("   ");
        
        A a = new A();
        ic.initialize(a);
        a.output();
        
        System.out.println("   ");
        System.out.println("INVOKING B AGAIN:   ");
        System.out.println("   ");
        
        b.output();
    }
    
    public static void taskB()
    {
        ControllerWithInjections cwi = new ControllerWithInjections();
        cwi.callSi();
        
        System.out.println("   ");
        System.out.println("INITIALIZING NEW B:   ");
        System.out.println("   ");
        
        B b = new B();
        b.output();

        System.out.println("   ");
        System.out.println("INITIALIZING NEW A:   ");
        System.out.println("   ");
        
        new A().output();
        
        System.out.println("   ");
        System.out.println("INVOKING B AGAIN:   ");
        System.out.println("   ");
        
        b.output();
    }
}
