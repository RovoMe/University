package dst1.model;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class MembershipPK implements Serializable
{
	private static final long serialVersionUID = 4502661293135239559L;
	private long user_id;
	private long grid_id;
	@Transient
	private volatile int hashCode;
	
	public MembershipPK()
	{
		
	}
	
	public MembershipPK(long user_id, long grid_id)
	{
		this.user_id = user_id;
		this.grid_id = grid_id;
	}
	
	@Column(name="USER_ID")
	public long getUserId()
	{
		return this.user_id;
	}
	
	public void setUserId(long user_id)
	{
		this.user_id = user_id;
	}
	
	@Column(name="GRID_ID")
	public long getGridId()
	{
		return this.grid_id;
	}
	
	public void setGridId(long grid_id)
	{
		this.grid_id = grid_id;
	}
	
	@Override
	public int hashCode()
	{
		int result = hashCode;
		if(result == 0)
		{
			result = 17;
			result = 31 * result + (int)user_id;
			result = 31 * result + (int)grid_id;
			hashCode = result;
		}
		return result;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof MembershipPK))
			return false;
		
		MembershipPK pk = (MembershipPK)o;
		return pk.getGridId() == this.getGridId() && pk.getUserId() == this.getUserId();
	}
	
}
