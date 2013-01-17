package dst2.ejb.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import javax.validation.*;


@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CPUValidator.class)
public @interface CPUs
{
	String message() default "Illegal amount of CPUs found.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	int min() default 1;
	int max() default 8;
}
