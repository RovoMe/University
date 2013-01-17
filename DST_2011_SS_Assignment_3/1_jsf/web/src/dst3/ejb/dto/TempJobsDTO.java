package dst3.ejb.dto;

import java.io.Serializable;

public class TempJobsDTO implements Serializable
{
	private static final long serialVersionUID = -2059696205408033002L;
	private long gridId;
	private int numTempJobs;
	
	public TempJobsDTO()
	{
		
	}
	
	public TempJobsDTO(long gridId, int numTempJobs)
	{
		this.gridId = gridId;
		this.numTempJobs = numTempJobs;
	}
	
	public long getGridId()
	{
		return this.gridId;
	}
	
	public void setGridId(long gridId)
	{
		this.gridId = gridId;
	}
	
	public int getNumTempJobs()
	{
		return this.numTempJobs;
	}
	
	public void setNumTempJobs(int numTempJobs)
	{
		this.numTempJobs = numTempJobs;
	}
	
	public String toString()
	{
		return "TempJobsDTO: {gridId: "+this.gridId+" | numTempJobs: "+this.numTempJobs+"}";
	}
}
