package dst3.ejb.session;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import dst3.ejb.entity.Audit;
import dst3.ejb.entity.Parameter;
import dst3.ejb.interfaces.AuditInterceptorLocal;
import dst3.ejb.session.helper.AuditPersister;

@Stateless
@Local(AuditInterceptorLocal.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AuditInterceptorBean implements AuditInterceptorLocal, Serializable
{	
	private static final long serialVersionUID = 9175895073265960088L;
	
	@EJB
	private AuditPersister auditPersister;
	
	@AroundInvoke
	public Object audit(InvocationContext invocation) throws Exception
	{
		Audit audit = new Audit();
		audit.setMyClass(invocation.getTarget().getClass().getName());
		audit.setMethod(invocation.getMethod().getName());	
		int index = 0;
		
		Object[] params = invocation.getParameters();
		if (params != null && params.length > 0)
		{
			List<Parameter> parameters = new ArrayList<Parameter>();
			for (Object obj : params)
			{
				Parameter param = new Parameter();
				param.setIndex(++index);
				if (obj != null)
				{
					param.setClassName(obj.getClass().getName());
					param.setValue(obj.toString());
				}
				else
				{
					param.setClassName(null);
					param.setValue(null);
				}
				parameters.add(param);
			}
			audit.setParameters(parameters);
		}
		audit.setTime(new Date());
		
		Object retVal = new Object();
		try
		{
			// send invocation-call to the target-object
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
