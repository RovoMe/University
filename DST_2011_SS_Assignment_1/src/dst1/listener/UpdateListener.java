package dst1.listener;

import java.util.Date;
import javax.persistence.PreUpdate;
import dst1.model.Computer;

public class UpdateListener
{
	@PreUpdate
	void preUpdate(Object entity)
	{
		if (entity instanceof Computer)
		{
			Computer comp = (Computer)entity;
			comp.setLastUpdate(new Date(System.currentTimeMillis()));
		}
	}
}
