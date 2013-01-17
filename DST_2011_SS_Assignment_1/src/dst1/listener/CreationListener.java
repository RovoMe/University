package dst1.listener;

import java.util.Date;
import javax.persistence.PrePersist;
import dst1.model.Computer;

public class CreationListener
{
	@PrePersist
	void preCreate(Object entity)
	{
		if (entity instanceof Computer)
		{
			Computer comp = (Computer)entity;
			comp.setCreationDate(new Date(System.currentTimeMillis()));
			comp.setLastUpdate(new Date(System.currentTimeMillis()));
		}
	}
}
