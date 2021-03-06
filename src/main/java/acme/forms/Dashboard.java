package acme.forms;

import java.io.Serializable;

import acme.datatypes.Workload;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable{
	
	protected static final long	serialVersionUID	= 1L;
	

	Double averageExecutionPeriods;
	Double deviationExecutionPeriods;
	Double minimumExecutionPeriods;
	Double maximumExecutionPeriods;

	Integer numberOfPublicDuty;
	Integer numberOfPrivateDuty;
	
	Integer numberOfFinishDuty;
	Integer numberOfNotFinishDuty;
	
	Workload minimumWorkload;
	Workload maximumWorkload;
	
	Float averageWorkload;
	Double deviationWorkload;
	
}
