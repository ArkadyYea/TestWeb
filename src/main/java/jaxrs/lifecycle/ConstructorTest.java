package jaxrs.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("lifecycle")
public class ConstructorTest {
	
	public ConstructorTest() {
		System.out.println("Lifecycles constructor - no args");
	}
	
	//Seems that constructors below does not work on Glassfish
	//org.jboss.weld.exceptions.CreationException: WELD-001530: Cannot produce an instance of class jaxrs.lifecycle.ConstructorTest.
	public ConstructorTest(@Context HttpServletRequest req) {
		System.out.println("Lifecycles constructor - req");
	}
	
	public ConstructorTest(@Context Application app) {
		System.out.println("Lifecycles constructor - app");
	}
	
	@PostConstruct
	public void cons() {
		System.out.println("lifecycle's @PostConstruct");
	}
	
	@PreDestroy
	public void dest() {
		System.out.println("lifecycle's @PreDestroy");
	}
	
	@GET
	@Path("constructor")
	@Produces(MediaType.TEXT_HTML)
	public Response getTest() {
		String str = "Constructor Test";
		return Response.ok(str).build();
	}
	
	@Inject
	EjbToGetTransaction tr;
	
	
	
}
