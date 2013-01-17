package dst2.ejb.dto;

import java.io.Serializable;

public class ParameterDTO implements Serializable
{
	private static final long serialVersionUID = -6440532446485423288L;
	private int idx;
	private String className;
	private String value;
	
	public ParameterDTO()
	{
		
	}
	
	public ParameterDTO(int index, String className, String value)
	{
		this.idx = index;
		this.className = className;
		this.value = value;
	}
	
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
