package security.rest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("secured")
public class RestSecured {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getSecured() {
		System.out.println("From Secured called");
		String str = "<h3>From Secured</h3>";
		return Response.ok(str).cookie(new NewCookie("myCokieFromSecured", "myCookieVal")).build();
	}
	
	
	@GET
	@Path("secured2")
	@Produces(MediaType.TEXT_HTML)
	public Response getSecured2() {
		System.out.println("From Secured called");
		String str = "<h3>From Secured 2</h3>";
		return Response.ok(str).build();
	}
	
	
	@Context
	SecurityContext sc;
	
	@Path("securityContext")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getSecurityContext() {
		System.out.println("getAuthenticationScheme(): "+sc.getAuthenticationScheme());
		System.out.println("isSecure(): "+sc.isSecure());
		System.out.println("isUserInRole('Admins'): "+sc.isUserInRole("Admins"));
		System.out.println("getUserPrincipal(): "+sc.getUserPrincipal());
		
		String str = "<h3>SecurityContext</h3>";
		return Response.ok(str).build();
	}
	
	
	@Context
	ServletContext ctx;
	
	@Path("servletContext")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getServletContext() {
		System.out.println(ctx.getContextPath());
		String str = "<h3>ServletContext</h3>";
		return Response.ok(str).build();
	}
	
	
	@Context
	//Request req;
	HttpServletRequest req;
	
	@Path("requestContext")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getRequest() {
		System.out.println(req.getMethod());
		System.out.println(req.getAuthType());
		System.out.println(req.getContextPath());
		System.out.println(req.getPathInfo());
		System.out.println(req.getRequestURI());
		req.getSession().invalidate();
		try {
			req.logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		//System.out.println(req.);
		//System.out.println(req.);
		String str = "<h3>requestContext</h3>";
		return Response.ok(str).build();
	}
}
