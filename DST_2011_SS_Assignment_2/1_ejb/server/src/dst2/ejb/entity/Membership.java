package dst2.ejb.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
@IdClass(MembershipPK.class)
public class Membership
{
	@Temporal(TemporalType.DATE)
	private Date registrationDate;
	private double discount;
	
	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "grid_id", referencedColumnName = "grid_id")
	private Grid grid;
	
	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	public Membership()
	{
		this.setRegistrationDate(new Date(System.currentTimeMillis()));
	}
	
	public Membership(Grid grid, User user)
	{
		this.setRegistrationDate(new Date(System.currentTimeMillis()));
		this.setGrid(grid);
		this.setUser(user);
		this.setDiscount(0);
	}
	
	public Membership(Grid grid, User user, double discount)
	{
		this.setRegistrationDate(new Date(System.currentTimeMillis()));
		this.setGrid(grid);
		this.setUser(user);
		this.setDiscount(discount);
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
	
	public Grid getGrid()
	{
		return this.grid;
	}
	
	public void setGrid(Grid grid)
	{
		this.grid = grid;
	}
	
	public User getUser()
	{
		return this.user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
}
