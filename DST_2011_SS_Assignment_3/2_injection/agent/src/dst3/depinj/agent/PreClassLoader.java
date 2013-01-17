package dst3.depinj.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import dst3.depinj.Component;
import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtNewConstructor;

public class PreClassLoader implements ClassFileTransformer
{
	private static PreClassLoader instance = null;
	
	/**
     * The underlying Java Instrumentation object
     */
	protected Instrumentation instrumentation = null;
	/**
     * A Javassist ClassPool object used for loading, and then instrumenting, classes
     */
	protected ClassPool classPool = null;
	/**
     * The names of the classes that we've already instrumented so that we don't
     * implement them twice
     */
	protected Set<String> instrumentedClasses = new HashSet<String>();
	/**
     * A list of class prefixes that should not be instrumented, e.g. java.lang
     */
    protected List<String> classesToSkip = new ArrayList<String>();
	    
    /**
     * Creates a new JavaAssistTransformer and sets up the Javassist environment
     *
     * @param instrumentation           The Java Instrumentation object to which we
     *                                  add ourselves as a transformer
     */
	private PreClassLoader(Instrumentation instrumentation)
	{
		this.instrumentation = instrumentation;
		
		this.classPool = ClassPool.getDefault();
		
		 // Build the list of class prefixes to skip
        classesToSkip.add( "com.geekcap.openapm" );
        classesToSkip.add( "javax." );
        classesToSkip.add( "java." );
        classesToSkip.add( "sun." );
        classesToSkip.add( "com.sun." );
        classesToSkip.add( "org.jdom" );
        classesToSkip.add( "org.apache." );
		
		this.instrumentation.addTransformer(this);
	}
	
    public static void premain(String options, Instrumentation instrumentation)
    {
        PreClassLoader.getInstance(instrumentation);
    }
	
	public static PreClassLoader getInstance(Instrumentation instrumentation)
	{
		if (instance == null)
			instance = new PreClassLoader(instrumentation);
		return instance;
	}
    
	/**
     * This method is invoked by the JVM for on all transformers to provide a mechanism for
     * the transformer to modify the bytecode of classes as they are being loaded by the
     * JVM.
     *
     * @param loader                        The ClassLoader that is loading this class
     * @param className                     The name of the class being loaded
     * @param classBeingRedefined           If this is triggered by a redefine or retransform,
     *                                      the class being redefined or retransformed; if this is a class load, null
     * @param protectionDomain              The protection domain of the class being defined or redefined
     * @param classfileBuffer               The input byte buffer in class file format - must not be modified
     *
     * @return                              A well-formed class file buffer (the result of the transform), or null if no transform is performed.
     *
     * @throws IllegalClassFormatException  If the input does not represent a well-formed class file
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, 
        Class<?> classBeingRedefined, ProtectionDomain protectionDomain, 
        byte[] classfileBuffer) throws IllegalClassFormatException
    {       
        // The class name contains slashes instead of periods, so use transform those to
        // dots for readability within the trace
        className = className.replace("/", ".");
        
        // Only instrument a class once
        if( this.instrumentedClasses.contains( className ) )
        {
            return null;
        }
        this.instrumentedClasses.add( className );
        
        // Skip in the list of class prefixes to skip
        for( String classToSkip : classesToSkip )
        {
            if( className.startsWith( classToSkip ) )
            {
                return null;
            }
        }
        
//      System.out.println("className: "+className);
 	
        // Returning null means that no transformation was done.
        try
    	{
        	// Create a Javassist CtClass from the bype code
        	this.classPool.insertClassPath(new ByteArrayClassPath(className, classfileBuffer));
			CtClass cc = this.classPool.getCtClass(className);

			if (!cc.isFrozen())
			{
				// only inject into classes that are annotated with @Component
				if (!cc.isInterface() && cc.hasAnnotation(Component.class))
				{									
					System.out.println("[PreClassLoader.transform] Modifying class: "+className);
					
					CtConstructor constructor;
					if (cc.getConstructors().length == 0)
					{
						constructor = CtNewConstructor.defaultConstructor(cc);
						cc.addConstructor(constructor);
					}
					else
						constructor = cc.getDeclaredConstructor(null);
					
					// Only instrument the constructor in this class, not in the super class 
					// because the super class will be instrumented separately
					if (constructor.getLongName().startsWith(className))
					{
						constructor.insertAfter("dst3.depinj.IInjectionController ic = dst3.depinj.InjectionControllerImpl.getInstance();"+
					                            "ic.initialize(this);");
					}
				}
				
				byte[] newClassfileBuffer = cc.toBytecode();
				return newClassfileBuffer;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
        
        return null;
    }
    
}
