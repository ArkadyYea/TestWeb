package jaxrs.test;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@SuppressWarnings("unused")
@Path("test")
public class TestMethods {
	
	@Context
	HttpHeaders head;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getTest() {
		String str = "This is info from res/test GET";
		System.out.println("Headers: "+head.getRequestHeaders());
		System.out.println("Cookies: "+head.getCookies());
		return Response.ok(str).build();
	}
	
	@HEAD
	@Produces(MediaType.TEXT_HTML)
	public Response headTest() {
		String str = "This is info from res/test HEAD";
		return Response.ok(str).build();
	}

	@POST
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML)
	public Response postTest(String posted) {
		String str = "This is info from res/test POST\n"+posted;
		return Response.ok(str).build();
	}
	
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	public Response deleteTest() {
		String str = "This is info from res/test DELETE";
		return Response.ok(str).build();
	}
	
//	@OPTIONS
//	@Produces(MediaType.TEXT_HTML)
//	public Response optionsTest() {
//		String str = "This is info from res/test OPTIONS";
//		return Response.ok(str).build();
//	}
	
	@PUT
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML)
	public Response putTest(String put) {
		String str = "This is info from res/test PUT\n"+put;
		return Response.ok(str).build();
	}
	
	@PATCH	//This annotation is from javax.ws.rs-api-2.1.jar (dependency of jersey-media-multipart-2.26)
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML)
	public Response patchTest(String patch) {
		String str = "This is info from res/test PATCH\n"+patch;
		return Response.ok(str).build();
	}

}
