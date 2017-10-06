package jaxrs.exceptions;

import javax.ws.rs.GET;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//https://dennis-xlc.gitbooks.io/restful-java-with-jax-rs-2-0-2rd-edition/en/part1/chapter7/exception_handling.html
//-javax.ws.rs.WebApplicationException  or subclasses of it can be thrown by application code
// and automatically processed by JAX-RS without having to write an explicit mapper (but you can write mapper to override default behavior). 
//-The class javax.ws.rs.WebApplicationException offers various constructors so you can pick a specific status code (Response.Status), Response, or an entity. 
//-If the app has initialized the WebApplicationException with a status code or Response object, that code or Response will be used
// to create the actual HTTP response. Otherwise, the WebApplicationException will return a status code of 500, “Internal Server Error”.

//-JAX-RS 2.0 has added a nice exception hierarchy for various HTTP error conditions. So, instead of creating
// an instance of WebApplicationException and initializing it with a specific status code, you can use one of these exceptions instead. 
//WebApplicationException's subclasses:
//Exception						Status code		Description
//BadRequestException			400				Malformed message
//NotAuthorizedException		401				Authentication failure
//ForbiddenException			403				Not permitted to access
//NotFoundException				404				Couldn’t find resource
//NotAllowedException			405				HTTP method not supported
//NotAcceptableException		406				Client media type requested not supported
//NotSupportedException			415				Client posted media type not supported
//InternalServerErrorException	500				General server error
//ServiceUnavailableException	503				Server is temporarily unavailable or busy@SuppressWarnings("unused")

@SuppressWarnings("unused")
@Path("exception")
public class TestExceptionWebApplicationEcx {
	
	@GET
	@Path("WebApplication")
	@Produces(MediaType.TEXT_HTML)
	public Response exceptionWebApplication() {
		if(true)
			throw new WebApplicationException("This is an error", Response.status(500, " There was an error!").build());
		return Response.ok("Abc").build();
	}
	
	@PUT
	@Path("notAllowedCustomized")
	@Produces(MediaType.TEXT_HTML)
	public Response exceptionWebApplication2() {
		if(true)
			throw new NotAllowedException(Response.status(405).entity("Is NotAllowedException customized or not?").build());
		return Response.ok("Abc").build();
	}
	
	@GET
	@Path("notFoundNotCustomized")
	@Produces(MediaType.TEXT_HTML)
	public Response exceptionWebApplication3() {
		if(true)
			//throw new NotFoundException(Response.status(404).entity("NotFoundException not customized").build());
			throw new NotFoundException("NotFoundException not customized", Response.status(404, " NotFoundException with customized Response!").build());
			//throw new NotFoundException("NotFoundException not customized");
		return Response.ok("Abc").build();
	}
}
