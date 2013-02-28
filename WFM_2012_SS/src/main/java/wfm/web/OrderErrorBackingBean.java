package wfm.web;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class OrderErrorBackingBean implements Serializable
{
	private static final long serialVersionUID = 2625200924200261435L;
	private String instanceId = "";
	private static Logger logger = Logger.getLogger(OrderErrorBackingBean.class);
	
	public String getInstanceId() { return this.instanceId; }
	public void setInstanceId(String instanceId) { this.instanceId = instanceId; }
	
	public String getErrorMessage() 
	{
		String error = ActivitiEngine.getInstance().getTaskError(this.instanceId);
		logger.info("Error-Message: "+error);
		return error;
	}
}
