package dst2.ejb.interfaces;

import javax.interceptor.InvocationContext;

public interface AuditInterceptor
{
	public Object audit(InvocationContext invocation) throws Exception;
}
