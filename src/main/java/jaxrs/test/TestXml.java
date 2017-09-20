package jaxrs.test;

import injection.point.User;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("test")
public class TestXml {
	
	@GET
	@Path("xml")
	@Produces(MediaType.APPLICATION_XML)
	public Response getXML() {
		return Response.ok(getUsers().get(0)).build();
	}
	
	
	@POST
	@Path("xml")
	@Consumes(MediaType.APPLICATION_XML)
	public Response postXML(User u) {
		return Response.ok("User created: "+u).build();
	}
	
	
	private List<User> getUsers() {
		List<User> list = new ArrayList<>();
		list.add(new User("John", "Doe"));
		list.add(new User("Aaron", "Smith"));
		list.add(new User("Jessica", "Marconi"));
		return list;
	}
	
}
