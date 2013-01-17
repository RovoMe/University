package dst3.ejb.jfs;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import dst3.ejb.dto.AuditDTO;
import dst3.ejb.interfaces.GeneralManagementLocal;

@ManagedBean(name="audit")
@RequestScoped
public class AuditBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@EJB
	private GeneralManagementLocal gm;
	
	public AuditBean()
	{
		
	}
	
	public List<AuditDTO> getAuditLog()
	{
		return this.gm.getAuditLog();
	}
}
