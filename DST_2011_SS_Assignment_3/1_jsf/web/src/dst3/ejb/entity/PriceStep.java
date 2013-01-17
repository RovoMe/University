package dst3.ejb.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"numberOfHistoricalJobs"})})
public class PriceStep
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private int numberOfHistoricalJobs;
	private BigDecimal price;
	
	public PriceStep()
	{
		
	}
	
	public PriceStep(int numberOfHistoricalJobs, BigDecimal price)
	{
		this.setNumberOfHistoricalJobs(numberOfHistoricalJobs);
		this.setPrice(price);
	}
	
	public long getId()
	{
		return this.id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public int getNumberOfHistoricalJobs()
	{
		return this.numberOfHistoricalJobs;
	}
	
	public void setNumberOfHistoricalJobs(int numberOfHistoricalJobs)
	{
		this.numberOfHistoricalJobs = numberOfHistoricalJobs;
	}
	
	public BigDecimal getPrice()
	{
		return this.price;
	}
	
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
}
