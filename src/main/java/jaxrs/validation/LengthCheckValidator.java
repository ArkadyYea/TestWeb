package jaxrs.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LengthCheckValidator implements ConstraintValidator<LengthCheck, String> {
	
	private int minNumberOfLetters;

	@Override
	public void initialize(LengthCheck constraintAnnotation) {
		this.minNumberOfLetters = constraintAnnotation.minNumberOfLetters();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext ctx) {
		if(value.length() < minNumberOfLetters) {
			return false;			
		} else {
			return true;
		}
	}
	
}
