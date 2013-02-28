package wfm.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.apache.log4j.Logger;

public class ActivitiEngine 
{
	private ProcessEngine processEngine = null;
	private static ActivitiEngine instance;
    private static Logger logger = Logger.getLogger(ActivitiEngine.class);
    private Map<String, String> taskErrorMap = new HashMap<String, String>();

    private ActivitiEngine()
    {

    }
	
	public static ActivitiEngine getInstance()
	{
		if (instance == null)
		{
			logger.info("Starting Activiti Engine");
			instance = new ActivitiEngine();
		}
		return instance;
	}
	
	
	public Deployment init(String description)
	{
		if (processEngine == null)
		{
			// use an H2 in-memory database for Activiti
			ProcessEngineConfiguration config = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
			
			/**
			 *  use the Activiti Explorer's H2 database - the process will be shown in the Activiti Explorer 
			 *  
			 *  only use this method if you have installed and started H2 on your own
			 *  @see http://forums.activiti.org/en/viewtopic.php?f=9&t=5762
			 *  @see http://www.h2database.com/html/quickstart.html
			 *  
			 *  *) use a generic H2 Server in the web-interface after invoking h2.bat or h2.sh and set the
			 *     JDBC URL to your needs, f.e. to jdbc:h2:tcp://localhost/activiti
			 *  *) change the jdbc.url inside WEB-INF/classes/db.properties of the activiti-explorer to match
			 *     your H2 jdbc.url f.e. jdbc.url=jdbc:h2:tcp://localhost/activiti
			 **/
			// ProcessEngineConfiguration config = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
			
			config.setProcessEngineName("Activiti 5.11 (Standalone)");
			processEngine = config.buildProcessEngine();
		}
		if (processEngine == null)
		{
			logger.error("Could not start the Activiti Engine");
			return null;
		}
		else
		{
			logger.info("Activiti Engine started");
		
			logger.info("deploying process description!");
			RepositoryService repositoryService = processEngine.getRepositoryService();
			List<Deployment> deployments = repositoryService.createDeploymentQuery().deploymentId(description).list();
			if (deployments == null || deployments.size() == 0)
			{
				DeploymentBuilder builder = repositoryService.createDeployment();
				builder.addClasspathResource("diagrams/"+description);
				builder.name(description);
				Deployment deployment = builder.deploy();
				logger.info("process deployed!");
				return deployment;
			}
			return deployments.get(0);
		}
	}
	
	public ProcessEngine getProcessEngine() { return this.processEngine; }
	public void setProcessEngine(ProcessEngine processEngine) { this.processEngine = processEngine; }
	
	public String getTaskError(String executionId) 
	{ 
		return this.getTaskError(executionId, true);
	}
	
	public String getTaskError(String executionId, boolean removeError) 
	{ 
		String taskError = this.taskErrorMap.get(executionId);
		if (removeError)
			this.taskErrorMap.put(executionId, ""); 
		return taskError; 
	}

	public void setTaskError(String executionId, String taskError) 
	{ 
		// Check if the error contains HTML tags
		if (taskError.startsWith("<"))
		{
			// remove opening tag - <h3>
			taskError = taskError.substring(taskError.indexOf(">")+1);
			// remove closing tag - </h3>
			taskError = taskError.substring(0, taskError.lastIndexOf("</"));
		}
		this.taskErrorMap.put(executionId, taskError);
	}
	
	public void destroy()
	{
		ProcessEngines.destroy();
		logger.info("Activiti Engine stopped");
	}
}
