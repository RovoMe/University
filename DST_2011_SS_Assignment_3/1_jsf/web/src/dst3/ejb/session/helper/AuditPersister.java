package dst3.ejb.session.helper;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dst3.ejb.entity.Audit;

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
		
		em.persist(audit);
	}
}
