package jaxrs.validation;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD, PARAMETER })
@Constraint(validatedBy = ValidateUserValidator.class)
public @interface ValidateUser {
	String message() default "Error Message from @ValidateUser";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
