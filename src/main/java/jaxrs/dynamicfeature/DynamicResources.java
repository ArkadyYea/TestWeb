package jaxrs.dynamicfeature;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//DynamicFeatureTest uses this class for testing Container Filters and Interceptors
@Path("dynamicFeature")
public class DynamicResources {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response dynamicTest() {
		String str = "DynamicFeature is used for dynamic registration of post-matching providers on particular class or method.";
		return Response.ok(str).build();
	}
	
	@Path("test")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response dynamicTest2() {
		String str = "When we use class, all resources in this class are affected.";
		return Response.ok(str).build();
	}

}
