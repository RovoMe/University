package dst3.depinj;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>This class is an implementation of {@link IInjectionController}. 
 * Its task is to inject instances of classes into {@link Inject}-annotated
 * fields.</p>
 * <p>Therefore every class is scanned for a {@link Component} annotation and
 * a {@link ComponentId}-annotation if it was declared as component. If the
 * first annotation could not be found and {@link #initialize(Object)} was 
 * called, an {@link InjectionException} will be raised as this method only
 * accepts {@link Component}-annotated objects.</p>
 * <p>If a {@link Component}-annotated object does not have a {@link ComponentId}
 * -annotation an {@link InjectionException} will be thrown as every {@link Component}
 * needs a unique {@link ComponentId}. This implementation sets a unique identifier
 * for every instantiated object. First it tries to set the unique identifier via
 * a set-method, which maps the fields name to get plug the field name starting with
 * an upper letter. This is due to inheritance to allow parent-classes to receive
 * their unique identifier. If there is no such method, this implementation injects
 * the identifier directly into the field.</p>
 * <p>If this {@link Component} is valid and contains {@link Inject}-annotations
 * InjectionControllerImpl tries to load the needed classes and to inject valid
 * instances into the annotated fields. Currently only injections into fields
 * are possible. In case an interface is marked as injection-target the application-
 * class path is looked for some classes that implement this interface. If only
 * one implementing class could be found, this class gets instantiated and injected
 * into the field.</p>
 * 
 * @see Component
 * @see ComponentId
 * @see Inject
 * @see ClassFinder#findImplementingClasses(Class, ClassLoader)
 * 
 * @author Roman Vottner
 */
public class InjectionControllerImpl implements IInjectionController
{
    private static InjectionControllerImpl me;
    private Map<Class<?>,Object> singletonRef = 
            Collections.synchronizedMap(new HashMap<Class<?>, Object>());
    private Set<Object> initializations = Collections.synchronizedSet(new LinkedHashSet<Object>());
    private Long uniqueId = 0L;
    
    private InjectionControllerImpl()
    {
    	
    }
    
    public static InjectionControllerImpl getInstance()
    {
        if (me == null)
            me = new InjectionControllerImpl();
        return me;
    }
    
    @Override
    public synchronized void initialize(Object obj) throws InjectionException
    {
        // every Object to initialize has to be annotated with "@Component"
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Component.class))
            throw new InjectionException("Object '"+obj.toString()+"' is not a component: "+obj);
        // check for multiple initialization calls for the same objects
        if (!this.initializations.contains(obj))
        {
//        	System.out.println("[InjectionControllerImpl.initialize] initializing "+obj);
        	this.initializations.add(obj);
        	this.initializeObject(obj, true);
        }
    }
    
    /**
     * <p>This method checks an object for inconsistencies like missing or multiple
     * {@link ComponentId}-fields and propagates injection of needed instances
     * to {@link #injectFields(Object, Field, List)}</p>
     * 
     * @param obj
     * @param throwForSuperclass
     */
    private void initializeObject(Object obj, boolean throwForSuperclass)
    {
        // every Object to initialize has to be annotated with "@Component"
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Component.class))
        	return;
        
        // every "@Component" annotated class needs one(!) "@ComponentId" of type Long
        int  foundId = 0;
        Field componentId = null;
        boolean required = false;
        List<Field> injectFields = new ArrayList<Field>();
        for (Field f : clazz.getDeclaredFields())
        {
            if (f.isAnnotationPresent(ComponentId.class))
            {
                if (!f.getType().equals(Long.class))
                    throw new InjectionException("Invalid ComponentId-Type found. ComponentId needs to be of type Long");
                foundId++;
                componentId = f;
            }
            if (f.isAnnotationPresent(Inject.class))
            {
            	Inject inject = f.getAnnotation(Inject.class);
            	required = inject.required();
                injectFields.add(f);
            }
        }
        if (foundId == 0)
            throw new InjectionException("No field annotated with @ComponentId found.");
        else if (foundId > 1)
            throw new InjectionException("More than one @ComponentId-fields found.");
        
        // so we do have a component-class
        // check if we have a Singleton or Prototype class
        Component comp = clazz.getAnnotation(Component.class);
        if (comp.scope().equals(ScopeType.SINGLETON))
        {
            // a singleton is created only once
            if (this.singletonRef.containsKey(obj.getClass()))
            {
            	if (throwForSuperclass)
                    throw new InjectionException("Cannot initialize further instances of a singleton class: "+obj);
            }
            else
            {
//            	System.out.println("[InjectionControllerImpl.initializeObject] adding "+obj+" to singleton-list");
                this.singletonRef.put(obj.getClass(), obj);
            }
        }
        
        this.injectFields(obj, componentId, injectFields, required);
    }
          
    /**
     * Returns all fields of super classes which have been annotated with
     * {@Inject}.
     * @param obj Instance, fields of super classes should be returned for
     * @return {@link List} of {@link Field}s which have been annotated with
     *         {@link Inject} in super classes.
     */
    private List<Field> getSuperClassFields(Object obj, boolean required)
    {
        List<Field> superClassFields = new ArrayList<Field>();
        Class<?> clazz = obj.getClass();
        for (Class<?> supClass = clazz.getSuperclass(); supClass != null; supClass = supClass.getSuperclass())
        {
            if (supClass!= null && supClass.isAnnotationPresent(Component.class))
            {
//              System.out.println("[InjectionControllerImpl.getSuperClassFields] Found Super-Class of "+clazz.getName()+" with Component-annotation: "+supClass.getCanonicalName());
                int found = 0;
                for (Field f : supClass.getDeclaredFields())
                {
                    if (f.isAnnotationPresent(ComponentId.class))
                    {
                        if (!f.getType().equals(Long.class) && required)
                            throw new InjectionException("Invalid ComponentId-Type found. ComponentId needs to be of type Long");
                        found++;
                    }
                    if (f.isAnnotationPresent(Inject.class))
                        superClassFields.add(f);
                }
                if (found == 0 && required)
                    throw new InjectionException("No field annotated with @ComponentId found.");
                else if (found > 1 && required)
                    throw new InjectionException("More than one @ComponentId-fields found.");
            }
        }
        return superClassFields;
    }
    
    /**
     * <p>Injects instances into {@link Field}s which have been annotated with
     * {@link Inject}. This method sets the {@link ComponentId} for a 
     * {@link Component}-annotated class too.</p>
     * <p>If the field to inject is an interface an implementing class is being
     * looked for via the {@link ClassFinder#findImplementingClasses}-method. If
     * none or more than one could be found an InjectionException will be thrown
     * if the injection is required </p>
     *
     * @param obj Object whose field(s) need to be injected.
     * @param componentId {@link ComponentId}-annotated field of this object.
     * @param injectFields {@link List} of {@link Field}s to inject instances to.
     * @see ClassFinder#findImplementingClasses(Class, ClassLoader)
     */
    private void injectFields(Object obj, Field componentId, List<Field> injectFields, boolean required)
    {
        try
        {
        	// and add its annotated fields to this object
        	// as those fields need to be injected too
            List<Field> superClassFields = this.getSuperClassFields(obj, required);
            for (Field f : superClassFields)
            	if (!injectFields.contains(f))
            		injectFields.add(f);
        	
            for (Field f : injectFields)
            {
                Inject inject = f.getAnnotation(Inject.class);
                
                // Test if a specified sub-type should be injected 
                // if none was specified use the fields type
                Class<?> toInject = null;
                if (!inject.specificType().equals(Inject.DEFAULT.class))
                    toInject = inject.specificType();
                else
                	toInject = f.getType();
//                System.out.println("[InjectionControllerImpl.injectFields] Injecting: "+toInject.getCanonicalName()+" into "+f.getName());
                
                // Set the specified type (or the fields type) as to be loaded
                Class<?> toLoad = toInject;
                // I don't handle abstract classes here
                if (toInject.isInterface())
                {
                    // As interfaces can't be instantiated we have to find some
                    // implementations of this interface
                    List<Class<?>> implementingClasses = 
                    	ClassFinder.getInstance().findImplementingClasses(toInject, 
                    			obj.getClass().getClassLoader());
                    
                    // If no implementations could be found, raise an error
                    // If there are more than one implementations to use, there
                    // is no certainty of which one to use, so raise an error too
                    if (implementingClasses.isEmpty() && inject.required())
                        throw new InjectionException("No implementation for "
                                +toInject.getCanonicalName()+" could be found!");
                    else if (implementingClasses.size() > 1 && inject.required())
                        throw new InjectionException("More than one implementation for "
                                +toInject.getCanonicalName()+" was found. "
                                + "Please specify which implementation you like to use "
                                + "bye adding the specificType-attribute to the "
                                + "annotation.");
                    
                    // as there is only one implementation expected, set its class
                    // to be loaded instead of the interface-class
                    if (!implementingClasses.isEmpty() || inject.required())
                    	toLoad = implementingClasses.get(0);
                }
                
                Object injObj = null;
                // Test if this class is a SINGLETON-component, if so, try to load
                // the object from the cache
                Component comp = toLoad.getAnnotation(Component.class);
                // an injection-field for an interface was found
                // which was declared as not required, so no exception may be
                // thrown
                if (comp == null)
                	return;
                
                if (comp.scope().equals(ScopeType.SINGLETON))
                    injObj = this.getSingletonInstance(toLoad);
                
                // either this class is scope PROTOTYPE or the SINGLETON was not
                // loaded before
                if (injObj == null)
                	// we use the class loader of the original object
                	// if this is a class from a plug-in, the actual class 
                	// loader is the plug-in class loader               	
                    injObj = this.loadClass(toLoad, inject.required());
                
                // start injecting sub-injections recursively
            	this.initializeObject(injObj, false);
            	
                String methodName = "set"+f.getName().substring(0, 1).toUpperCase()+f.getName().substring(1);
                try
                {
                	Method m = obj.getClass().getMethod(methodName, f.getType());
                	m.setAccessible(true);
                	m.invoke(obj, injObj);
                }
                catch(NoSuchMethodException nsmEx)
                {
	                f.setAccessible(true);
	                f.set(obj, injObj);
                }
				catch (InvocationTargetException e)
				{
					e.printStackTrace();
				}
            }
            // Set the unique id
            componentId.setAccessible(true);
            if (componentId.get(obj) == null)
            {
            	Long id = ++uniqueId;
                String compId = "set"+componentId.getName().substring(0, 1).toUpperCase()+componentId.getName().substring(1);
                try
				{
      				Method m = obj.getClass().getMethod(compId, Long.class);
					m.setAccessible(true);
					m.invoke(obj, id);
				}
				catch (NoSuchMethodException e)
				{
					componentId.set(obj, id);
				}
				catch (Exception e)
				{
					throw new InjectionException(e);
				}      
//				System.out.println("[InjectionControllerImpl.injectFields] "+obj.toString()+" has now id "+id);
            }
        }
        catch (IllegalArgumentException ex)
        {
            throw new InjectionException(ex);
        }
        catch (IllegalAccessException ex)
        {
            throw new InjectionException(ex);
        }
    }
    
    /**
     * <p>Instantiates a new instance of {@link Class} provided by the 
     * classToLoad argument. Therefore the default-constructor will be
     * used.</p>
     * <p>If this class could not get instantiated correctly a 
     * {@link InjectionException} will be thrown unless required was set
     * to false.</p>
     * @param classToLoad {@link Class} which needs to be loaded
     * @param required If set to true, all exceptions will be forwarded. 
     * @return An initialized object of the provided {@link Class}
     */
    private <T> T loadClass(Class<T> classToLoad, boolean required)
    {
        try
        {   	
            T injObj = classToLoad.newInstance();
            if (!classToLoad.isInstance(injObj))
                throw new InjectionException("Could not instantiate "+
                        classToLoad.getName()+"!");

//            System.out.println("[InjectionControllerImpl.loadClass] Initialized object: "+injObj
//            	+" loader: "+injObj.getClass().getClassLoader());
            return injObj;
        }
        catch (IllegalAccessException ex)
        {
            if (required)
                throw new InjectionException(ex);
        }        
        catch (InstantiationException e) 
        {
            // if field is required and there are any problems injecting
            // the field throw an exception
            if (required)
                throw new InjectionException(e);
		}
        return null;
    }
    
	@Override
    public synchronized <T> T getSingletonInstance(Class<T> clazz) throws InjectionException
    {
		@SuppressWarnings("unchecked")
    	T ret = (T)singletonRef.get(clazz);
        return ret;
    }
    
}
