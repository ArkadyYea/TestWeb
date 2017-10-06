package jaxrs.filters.lifecycle;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("filterlifecycle")
public class FilterLifecycleResources {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response dynamicTest() {
		String str = "A single instance of each filter or entity interceptor is instantiated for each JAX-RS application."
				+ "\nThe filter randomly throws a mapped ArithmeticException";
		return Response.ok(str).build();
	}
	
}