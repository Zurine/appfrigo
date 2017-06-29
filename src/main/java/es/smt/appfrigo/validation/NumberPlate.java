package es.smt.appfrigo.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = NumberPlateConstraintValidator.class)
@Target( { ElementType.METHOD , ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberPlate {
	
	String message() default "Number Plate must be between 7 and 10";
    
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
}
