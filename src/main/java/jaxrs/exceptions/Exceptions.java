package jaxrs.exceptions;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Errors can be reported to a client either by creating and returning the appropriate Response object or by throwing an exception.
//Thrown exceptions are handled by the JAX-RS runtime if you have registered an exception mapper (or use WebApplicationException  or subclasses). 

//https://dennis-xlc.gitbooks.io/restful-java-with-jax-rs-2-0-2rd-edition/en/part1/chapter7/exception_handling.html
//-A resource can throw any checked (classes extending java.lang.Exception) or unchecked (classes extending java.lang.RuntimeException) exceptions
//-If the thrown exception is not handled by a mapper, it is propagated and handled by the container (i.e., servlet) JAX-RS is running within.
//-Checked exceptions cant be thrown directly and must be wrapped in a container-specific exception (ServletException, WebServiceException or WebApplicationException
//-javax.ws.rs.WebApplicationException  or subclasses of it (BadRequestException, ForbiddenException, NotAcceptableException, NotAllowedException, NotAuthorizedException, NotFoundException, NotSupportedException).
// can be thrown by application code and automatically processed by JAX-RS without having to write an explicit mapper. 
//-The class javax.ws.rs.WebApplicationException offers various constructors so you can pick a specific status code (Response.Status), Response, or an entity. 
@SuppressWarnings("unused")
@Path("exception")
public class Exceptions {
	
	@GET
	@Path("checked")
	@Produces(MediaType.TEXT_HTML)
	public Response exceptionChecked() throws NamingException {
		if(true)
			throw new NamingException("Checked Exception");
		return Response.ok("Abc").build();
	}
	
	@GET
	@Path("unchecked")
	@Produces(MediaType.TEXT_HTML)
	public Response exceptionUnchecked() throws RuntimeException {
		if(true)
			throw new RuntimeException("Runtime Exception");
		return Response.ok("Abc").build();
	}
	
	@GET
	@Path("servlet")
	@Produces(MediaType.TEXT_HTML)
	public Response exceptionServlet() throws ServletException {
		if(true)
			throw new ServletException("Servlet Exception");
		return Response.ok("Abc").build();
	}

}
