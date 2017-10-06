package jaxrs.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import abc.User;

public class ValidateUserValidator implements ConstraintValidator<ValidateUser, User> {
	
	@Override
	public void initialize(ValidateUser constraintAnnotation) {
	}

	@Override
	public boolean isValid(User value, ConstraintValidatorContext ctx) {
		if(value.getName() == null || value.getName().equals("")) {
			return false;			
		} else if(value.getSurname() == null || value.getSurname().equals("")) {
			return false;			
		} else {
			return true;
		}
	}
	
}
