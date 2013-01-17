package dst2.ejb.session;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import dst2.ejb.entity.Audit;
import dst2.ejb.entity.Parameter;
import dst2.ejb.interfaces.AuditInterceptor;
import dst2.ejb.interfaces.AuditInterceptorLocal;
import dst2.ejb.session.helper.AuditPersister;

@Stateless
@Remote(AuditInterceptor.class)
@Local(AuditInterceptorLocal.class)
public class AuditInterceptorBean implements AuditInterceptor, AuditInterceptorLocal
{	
	@EJB
	private AuditPersister auditPersister;
	
	@AroundInvoke
	public Object audit(InvocationContext invocation) throws Exception
	{
		Audit audit = new Audit();
		audit.setMethod(invocation.getMethod().getName());	
		int index = 0;
		
		Object[] params = invocation.getParameters();
		if (params != null && params.length > 0)
		{
			List<Parameter> parameters = new Vector<Parameter>();
			for (Object obj : params)
			{
				Parameter param = new Parameter();
				param.setIndex(++index);
				param.setClassName(obj.getClass().getName());
				param.setValue(obj.toString());
				parameters.add(param);
			}
			audit.setParameters(parameters);
		}
		audit.setTime(new Date());
		
		Object retVal = new Object();
		try
		{
			retVal = invocation.proceed();
			if (retVal != null)
				audit.setValue(retVal.toString());
			else
				audit.setValue(null);
		}
		catch (Exception ex)
		{
			audit.setFailure(ex.getMessage());
			throw ex;
		}
		finally
		{
			this.auditPersister.persistAudit(audit);
		}
		return retVal;
	}
}
