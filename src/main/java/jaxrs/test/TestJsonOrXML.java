package jaxrs.test;

import injection.point.User;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("test")
@Stateless
public class TestJsonOrXML {
	
	@GET
	@Path("jsonOrXml")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response userJSON() {
		return Response.ok(new User("John","Smith")).build();
	}
		
}
