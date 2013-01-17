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

package dst3.dynload.sample;

import dst3.depinj.Component;
import dst3.depinj.ComponentId;
import dst3.depinj.IInjectionController;
import dst3.depinj.InjectionControllerImpl;
import dst3.depinj.Inject;
import dst3.depinj.InjectionException;
import dst3.depinj.ScopeType;
import dst3.dynload.IPluginExecutable;

@Component(scope=ScopeType.PROTOTYPE)
public class ControllerWithInjections implements IPluginExecutable
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

	@Override
	public void execute() 
	{
		System.out.println();
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("                                    Starting execution: ");
		System.out.println("   ");
		System.out.println("A, D and E are singleton-components, all others are prototype-components");
		System.out.println("   ");
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		A a = null;
		System.out.println("   ");
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        try
        {
        	System.out.println("Creating new singleton E:");
	        E e = new E();
	        e.output();
        }
        catch(InjectionException Ex)
        {
        	System.err.println("ERROR: "+Ex.getLocalizedMessage());
        }
		catch(NullPointerException npEx)
		{
			npEx.printStackTrace();
		}
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        try 
        {
			Thread.sleep(2*1000);
		} 
        catch (InterruptedException e) 
        {
			e.printStackTrace();
		}
		System.out.println("   ");
		System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		try
		{
			System.out.println("Creating new prototype B: ");
			B b = new B();
	        b.output();
		}
		catch(InjectionException Ex)
		{
			System.err.println("ERROR: "+Ex.getLocalizedMessage());
		}
		catch(NullPointerException npEx)
		{
			npEx.printStackTrace();
		}
		System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        try 
        {
			Thread.sleep(2*1000);
		} 
        catch (InterruptedException e) 
        {
			e.printStackTrace();
		}
        System.out.println("   ");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        try
        {
        	System.out.println("Creating new singleton A: ");
	        a = new A();
	        a.output();
        }
        catch(InjectionException Ex)
        {
        	System.err.println("ERROR: "+Ex.getLocalizedMessage());
        }
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        try 
        {
			Thread.sleep(2*1000);
		} 
        catch (InterruptedException e) 
        {
			e.printStackTrace();
		}
		System.out.println("   ");
		System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		try
		{
			System.out.println("Creating new prototype B: ");
			B b = new B();
	        b.output();
		}
		catch(InjectionException Ex)
		{
			System.err.println("ERROR: "+Ex.getLocalizedMessage());
		}
		catch(NullPointerException npEx)
		{
			npEx.printStackTrace();
		}
		System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        try 
        {
			Thread.sleep(2*1000);
		} 
        catch (InterruptedException e) 
        {
			e.printStackTrace();
		}
        System.out.println("   ");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        try
        {
        	System.out.println("Calling singleton A again: ");
	        a.output();
        }
        catch(InjectionException Ex)
        {
        	System.err.println("ERROR: "+Ex.getLocalizedMessage());
        }
		catch(NullPointerException npEx)
		{
			npEx.printStackTrace();
		}
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        try 
        {
			Thread.sleep(2*1000);
		} 
        catch (InterruptedException e) 
        {
			e.printStackTrace();
		}
        System.out.println("   ");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        try
        {
        	System.out.println("Creating new singleton A: ");
        	a = new A();
	        a.output();
        	System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        }
        catch(InjectionException Ex)
        {
        	System.err.println("ERROR: "+Ex.getLocalizedMessage());
        	System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }
		catch(NullPointerException npEx)
		{
			npEx.printStackTrace();
		}
        try 
        {
			Thread.sleep(2*1000);
		} 
        catch (InterruptedException e) 
        {
			e.printStackTrace();
		}
        System.out.println();	
	}
}
