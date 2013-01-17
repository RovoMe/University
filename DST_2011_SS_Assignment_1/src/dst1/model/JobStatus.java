package dst1.model;

import java.io.Serializable;

public enum JobStatus implements Serializable
{
	SCHEDULED,
	RUNNING,
	FAILED,
	FINISHED
}
