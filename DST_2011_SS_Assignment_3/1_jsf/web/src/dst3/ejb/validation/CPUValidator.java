package dst3.ejb.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
 
		if (val < min)
			return false;
		if (val > max)
			return false;

		return true;
	}

}
