package dst2.ejb.session.helper;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dst2.ejb.entity.Audit;

@Stateless
@Local
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AuditPersister
{
	@PersistenceContext
	private EntityManager em; 
	
	public void persistAudit(Audit audit)
	{
		if (audit == null)
			throw new IllegalArgumentException();
		
		try
		{
			em.persist(audit);
		}
		catch(Exception ex)
		{
			System.err.println(ex.getLocalizedMessage());
		}
	}
}
