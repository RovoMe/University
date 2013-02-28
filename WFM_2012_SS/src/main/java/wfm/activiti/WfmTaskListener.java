package wfm.activiti;

import java.util.Set;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import wfm.web.ActivitiEngine;

public class WfmTaskListener implements ExecutionListener
{
	public WfmTaskListener()
	{
		
	}

	@Override
	public void notify(DelegateExecution execution) throws Exception 
	{
		if (execution.getEventName().equals(EVENTNAME_END))
		{
			Set<String> variables = execution.getVariableNames();
			for (String var : variables)
			{
				if (var.endsWith("ValidationError") || var.equals("paymentError"))
				{
					String error = execution.getVariable(var).toString();
					if (!error.trim().equals(""))
					{						
						ActivitiEngine.getInstance().setTaskError(execution.getProcessInstanceId(), execution.getVariable(var).toString());
					}
				}
			}
		}
	}
}
