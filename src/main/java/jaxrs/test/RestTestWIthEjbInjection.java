package jaxrs.test;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("test/injection")
public class RestTestWIthEjbInjection {
	
	@Inject
	RestEjbToInject ti;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getTest() {
		String str = "This is info from res/test/injection GET, "+ti.inject();
		return Response.ok(str).build();
	}
	
	
	
}

