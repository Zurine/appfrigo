package es.smt.appfrigo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberPlateConstraintValidator  implements ConstraintValidator<NumberPlate, String> {
 
    @Override
    public void initialize(NumberPlate numberPlate) { }
 
    @Override
    public boolean isValid(String numberPlateField, ConstraintValidatorContext cxt) {
    	
    	if(numberPlateField == null || numberPlateField.equals("")) 
            return true;
    	else return numberPlateField.length() > 2 && numberPlateField.length() < 13;
    }
 
}