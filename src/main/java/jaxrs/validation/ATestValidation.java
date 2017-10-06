package jaxrs.validation;

import java.util.concurrent.ThreadLocalRandom;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import abc.User;

//-Constraint annotations are allowed in the same locations as the binding annotations (like @QueryParam, @Context),
// except in class constructors and property setters.
//-Specifically, they are allowed in resource method parameters, fields and property getters
// as well as resource classes, entity parameters and resource methods (return values)
//-Constraint annotations on properties are specified on property or in their corresponding getters.
//-Constraint annotations are also allowed on resource classes, it is defined for the entire class. 
//-Constraint annotations in Bean Validation are cumulative (can be strengthen) across a given type hierarchy
// while JAX-RS annotations are inherited or, overridden and ignored.
//-The default resource class instance lifecycle is per-request in JAX-RS. Implementations MAY support other lifecycles;
// the same caveats related to the use of other JAX-RS annotations in resource classes apply to constraint annotations.
// Fe, a constraint validation annotating a constructor parameter in a resource class whose lifecycle is singleton will only be executed once.
@Path("validation")
public class ATestValidation {
	
	//http://localhost:8080/TestWeb/res/validation?name=John
	//http://localhost:8080/TestWeb/res/validation		- HTTP Status 400 - Bad Request - request sent by the client was syntactically incorrect.
	@GET
	public Response validateQueryParam(@NotNull @QueryParam("name") String name) {
		String res = "User name is: "+name;
		return Response.ok(res).build();
	}

	
	@GET
	@Path("customAnnotation")
	public Response validateParam(@LengthCheck(minNumberOfLetters = 3) @QueryParam("name") String name) {
		String res = "User name is: "+name;
		return Response.ok(res).build();
	}

	//-There are 2 ways these entities can be validated.
	//-If a request entity is mapped to a Java bean whose class is decorated with Bean Validation annotations, then validation can be enabled using @Valid:
	// The presence of @Valid will trigger validation of all the constraint annotations decorating a Java bean class. 
	@POST
	@Path("user")
	public Response validateUser(@Valid UserToValidate u) {		//Resource used in RestClient
		String res = "User is: "+u;
		return Response.ok(res).build();
	}
	
	//Or, a new annotation can be defined and used directly on the resource method parameter.
	@POST
	@Path("user2")
	public Response validateUser2(@ValidateUser User u) {		//Resource used in RestClient
		String res = "User is: "+u;
		return Response.ok(res).build();
	}
	
	//-Response entity bodies returned from resource methods can be validated in a similar manner by annotating the resource method itself.
	@GET
	@Path("user")
	@Valid
	public UserToValidate validateUser() {
		UserToValidate u = null;
		boolean test = ThreadLocalRandom.current().nextBoolean();
		
		if(test) {
			u = new UserToValidate("John" , "Smith");
		} else {
			u = new UserToValidate("" , "Smith");
		}
		
		String res = "User is: "+u;
		return u;
		//return Response.ok(res).build();
	}

}
