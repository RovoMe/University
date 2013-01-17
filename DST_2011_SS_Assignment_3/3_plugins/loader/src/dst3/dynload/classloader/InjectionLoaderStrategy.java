package dst3.dynload.classloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javassist.ByteArrayClassPath;
import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtNewConstructor;
import javassist.NotFoundException;
import dst3.depinj.Component;
import dst3.depinj.ScopeType;

public class InjectionLoaderStrategy implements IClassLoaderStrategy
{
	private IClassLoaderStrategy strategy = null;
	private File jarFile = null;
	/**
     * The names of the classes that we've already instrumented so that we don't
     * implement them twice
     */
	private Set<String> instrumentedClasses = new HashSet<String>();
	/**
     * A list of class prefixes that should not be instrumented, e.g. java.lang
     */
    private List<String> classesToSkip = new ArrayList<String>();
    
	public InjectionLoaderStrategy(IClassLoaderStrategy strategy)
	{
		this.strategy = strategy;
		
		 // Build the list of class prefixes to skip
        classesToSkip.add( "com.geekcap.openapm" );
        classesToSkip.add( "javax." );
        classesToSkip.add( "java." );
        classesToSkip.add( "sun." );
        classesToSkip.add( "com.sun." );
        classesToSkip.add( "org.jdom" );
        classesToSkip.add( "org.apache." );
	}
	
	public void setJarFile(File jarFile)
	{
		this.jarFile = jarFile;
	}
	
	@Override
	public byte[] findClassBytes(String className)
	{
        // Only instrument a class once
        if( this.instrumentedClasses.contains( className ) )
        {
            return null;
        }
        this.instrumentedClasses.add( className );
        
        // Skip in the list of class prefixes to skip
        for( String classToSkip : this.classesToSkip )
        {
            if( className.startsWith( classToSkip ) )
            {
                return null;
            }
        }
		
		byte[] strategyBytes = this.strategy.findClassBytes(className);
		ClassPool cp = ClassPool.getDefault();
		cp.insertClassPath(new ClassClassPath(this.getClass()));
		try
		{
			cp.insertClassPath(this.jarFile.getAbsolutePath());
		}
		catch (NotFoundException e1)
		{
			e1.printStackTrace();
		}
		cp.appendClassPath(new ByteArrayClassPath(className, strategyBytes));
		
		try
		{
			CtClass cc = cp.get(className);
			if (!cc.isFrozen())
			{
				if (cc.hasAnnotation(Component.class))
				{											
					CtConstructor constructor;
					if (cc.getConstructors().length == 0)
					{
						constructor = CtNewConstructor.defaultConstructor(cc);
						cc.addConstructor(constructor);
					}
					else
						constructor = cc.getDeclaredConstructor(null);
									
					// Only instrument the default constructor in this class, 
					// not in the super class because the super class will be 
					// instrumented separately
					if (constructor.getLongName().startsWith(className))
					{
//						System.out.println("[InjectionLoaderStrategy::findClassBytes] Modifying class: "+className+"\n");
						
						String code = 			
						"dst3.depinj.IInjectionController ic = dst3.depinj.InjectionControllerImpl.getInstance(); "+
						"ic.initialize(this);";
						
						constructor.insertAfter(code);
					}
		        }
			}
			return cc.toBytecode();
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (CannotCompileException e)
		{
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return strategyBytes;
	}

	@Override
	public URL findResourceURL(String resourceName)
	{

		return strategy.findResourceURL(resourceName);
	}

	@Override
	public Enumeration<URL> findResourcesEnum(String resourceName)
	{

		return strategy.findResourcesEnum(resourceName);
	}

	@Override
	public String findLibraryPath(String libraryName)
	{

		return strategy.findLibraryPath(libraryName);
	}
	
	public boolean isSingleton(String name)
	{
		byte[] strategyBytes = strategy.findClassBytes(name);
		ClassPool cp = ClassPool.getDefault();
		cp.insertClassPath(new ByteArrayClassPath(name, strategyBytes));
		
		try
		{
			CtClass cc = cp.get(name);
			if (cc.hasAnnotation(Component.class))
			{
				Component comp = (Component)cc.getAnnotation(Component.class);
				if (comp.scope().equals(ScopeType.SINGLETON))
					return true;
			}
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
