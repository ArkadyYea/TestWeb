package jaxrs.exceptions;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//-Objects implementing javax.ws.rs.ext.ExceptionMapper know how to map a thrown application exception to a Response object.
//-ExceptionMapper implementation must be annotated with the @Provider annotation to to tells the JAX-RS runtime that it is a component. 
//-toResponse() receives the thrown exception and creates a Response object that will be used to build the HTTP response.
//-Here: ArithmeticExceptions implements ExceptionMapper<ArithmeticException>
@Path("exception")
public class TestExceptionMapping {
	
	@GET
	@Path("mapped")
	@Produces(MediaType.TEXT_HTML)
	public Response exceptionMapped() {
		String str = "Dividing 2 / 0: " + (2/0);
		return Response.ok(str).build();
	}

}
