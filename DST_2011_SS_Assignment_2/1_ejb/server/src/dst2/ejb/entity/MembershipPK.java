package dst2.ejb.entity;

public class MembershipPK
{
	private long user;
	private long grid;

	public MembershipPK()
	{
		
	}
	
	public MembershipPK(long user, long grid)
	{
		this.user = user;
		this.grid = grid;
	}
	
	public long getUserId()
	{
		return this.user;
	}
	
	public void setUserId(long user)
	{
		this.user = user;
	}
	
	public long getGridId()
	{
		return this.grid;
	}
	
	public void setGridId(long grid)
	{
		this.grid = grid;
	}
	
	@Override
    public boolean equals(Object object) 
	{
        if (object instanceof MembershipPK) 
		{
         MembershipPK pk = (MembershipPK)object;
            return user == pk.user && grid == pk.grid;
        } 
		else 
		{
            return false;
        }
    }
 
    public int hashCode() 
	{
        return (int) (new Long(grid).hashCode() + new Long(user).hashCode());
    }
}
