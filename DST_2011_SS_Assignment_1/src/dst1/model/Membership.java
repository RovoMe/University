package dst1.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Membership implements Serializable
{
	@Transient
	private static final long serialVersionUID = 6770557157436494951L;
	private MembershipPK pk;
	private Date registrationDate;
	private double discount;
	private Grid grid;
	private User user;
	
	public Membership()
	{
		pk = new MembershipPK();
		this.setRegistrationDate(new Date(System.currentTimeMillis()));
	}
	
	public Membership(Grid grid, User user)
	{
		pk = new MembershipPK();
		this.setRegistrationDate(new Date(System.currentTimeMillis()));
		this.setGrid(grid);
		this.setUser(user);
		this.setDiscount(0);
	}
	
	public Membership(Grid grid, User user, double discount)
	{
		pk = new MembershipPK();
		this.setRegistrationDate(new Date(System.currentTimeMillis()));
		this.setGrid(grid);
		this.setUser(user);
		this.setDiscount(discount);
	}
	
	@EmbeddedId
	public MembershipPK getPk()
	{
		return pk;
	}
	
	public void setPk(MembershipPK pk)
	{
		this.pk = pk;
	}
	
	public Date getRegistrationDate()
	{
		return this.registrationDate;
	}
	
	public void setRegistrationDate(Date registrationDate)
	{
		this.registrationDate = registrationDate;
	}
	
	public double getDiscount()
	{
		return this.discount;
	}
	
	public void setDiscount(double discount)
	{
		this.discount = discount;
	}
	
	@ManyToOne(optional=false)
//	@PrimaryKeyJoinColumn(referencedColumnName="gridId")
	@JoinColumn(name="GRID_ID", insertable=false, updatable=false)
	public Grid getGrid()
	{
		return this.grid;
	}
	
	public void setGrid(Grid grid)
	{
		this.grid = grid;
		pk.setGridId(grid.getId());
	}
	
	@ManyToOne(optional=false)
//	@PrimaryKeyJoinColumn(referencedColumnName="userId")
	@JoinColumn(name="USER_ID", insertable=false, updatable=false)
	public User getUser()
	{
		return this.user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
		pk.setUserId(user.getId());
	}
}
