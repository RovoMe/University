package dst3.ejb.interfaces;

import javax.interceptor.InvocationContext;

public interface AuditInterceptorLocal
{
	public Object audit(InvocationContext invocation) throws Exception;
}
