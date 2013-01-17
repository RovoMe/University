package dst3.ejb.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Parameter
{
	private int idx;
	@Column(columnDefinition="TEXT")
	private String className;
	@Column(columnDefinition="TEXT")
	private String value;
	
	public int getIndex()
	{
		return this.idx;
	}
	
	public void setIndex(int idx)
	{
		this.idx = idx;
	}
	
	public String getClassName()
	{
		return this.className;
	}
	
	public void setClassName(String className)
	{
		this.className = className;
	}
	
	public String getValue()
	{
		return this.value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
}
