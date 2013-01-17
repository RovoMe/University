package dst2.ejb.entity;

import java.io.Serializable;

public enum TaskStatus implements Serializable
{
	ASSIGNED,
	READY_FOR_PROCESSING,
	PROCESSING_NOT_POSSIBLE,
	PROCESSED
}
