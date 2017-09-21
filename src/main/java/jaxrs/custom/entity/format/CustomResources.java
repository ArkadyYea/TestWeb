package jaxrs.custom.entity.format;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import abc.User;

//DynamicFeatureTest uses this class for testing Interceptors
@Path("custom")
public class CustomResources {
	
	@GET
	@Produces("custom/format")
	public Response customGet() {
		User u = new User("John","Smith");
		return Response.ok(u).build();
	}
	
	@POST
	@Consumes("custom/format")
	public Response customPost(User u) {
		String str = "User created by custom reader: "+u;
		return Response.ok(str).build();
	}

}
