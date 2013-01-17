package dst1.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
//import java.util.List;
//import dst1.model.Cluster;
//import dst1.model.Computer;

public class CPUValidator implements ConstraintValidator<CPUs, Integer>
{
	private int min;
	private int max;
	
	@Override
	public void initialize(CPUs cups)
	{
		this.min = cups.min();
		this.max = cups.max();
	}

	@Override
	public boolean isValid(Integer val, ConstraintValidatorContext context)
	{
		if (min > max)
			return false;
 
//		for (Cluster cluster : clusters)
//		{
//			System.out.println(cluster.getName());
//			try
//			{
//
//			List<Computer>comps = cluster.getComputers();
//			for (Computer comp : comps)
//			{
//				int val = comp.getCPUs();
				if (val < min)
					return false;
				if (val > max)
					return false;
//			}
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
		return true;
	}

}
