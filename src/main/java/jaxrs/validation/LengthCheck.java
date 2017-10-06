package jaxrs.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Constraint(validatedBy = LengthCheckValidator.class)
public @interface LengthCheck {
	//String message() default "{com.example.validation.constraints.email}";
	String message() default "Error Message from @LengthCheck";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	int minNumberOfLetters();
}
