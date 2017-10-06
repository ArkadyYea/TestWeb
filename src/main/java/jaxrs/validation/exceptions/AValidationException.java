package jaxrs.validation.exceptions;

import java.util.concurrent.ThreadLocalRandom;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import jaxrs.validation.LengthCheck;
import jaxrs.validation.UserToValidate;

//-The exception model in Bean Validation defines a base class javax.validation.ValidationException and a few subclasses to report errors
// that are specific to constraint definitions, constraint declarations, group definitions and constraint violations.
//-JAX-RS impls MUST provide a default exception mapper for javax.validation.ValidationException according to the following rules:
// 1. If the exception is of type javax.validation.ValidationException or any of its subclasses excluding ConstraintViolationException, then it is mapped to a response with status code 500 (Internal Server Error).
// 2. If the exception is an instance of javax.validation.ConstraintViolationException, then:
//  (a) If the exception was thrown while validating a method return type, then it is mapped to a response with status code 500 (Internal Server Error) 1.
//  (b) Otherwise, it is mapped to a response with status code 400 (Bad Request).
//-In all cases, JAX-RS impls SHOULD include a response entity describing the source of the error.
//-Applications can provide their own exception mappers and, consequently, customize the default mapper described above.

//To activate ConstraintViolationExceptions unrem @Provider or register it in JAXRSConfig class (?DynamicFeature does not work?)
@Path("validation")
public class AValidationException {

	//http://localhost:8080/TestWeb/res/validation/paramException?name=John
	@GET
	@Path("paramException")
	public Response paramException(@LengthCheck(minNumberOfLetters = 3) @QueryParam("name") String name) {
		String res = "User name is: "+name;
		return Response.ok(res).build();
	}


	@GET
	@Path("userException")
	@Valid
	public UserToValidate userException() {
		UserToValidate u = null;
		boolean test = ThreadLocalRandom.current().nextBoolean();
		
		if(test) {
			u = new UserToValidate("John" , "Smith");
		} else {
			u = new UserToValidate("" , "Smith");
		}
		
		return u;
	}
	
}
