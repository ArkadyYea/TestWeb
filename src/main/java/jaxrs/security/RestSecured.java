package jaxrs.security;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Providers;

//@Context - Application, UriInfo, Request, HttpHeaders, SecurityContext, Providers
@SuppressWarnings("unused")
@RolesAllowed({"Admins", "user"})
@Path("secured")
public class RestSecured {
	
	@Context
	HttpServletRequest req;

	@Context
	HttpServletResponse res;
	
	@Context
	HttpHeaders head;
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getSecured() {
		System.out.println("-----------------------------------");
		System.out.println("Call From Secured path");
		String str = "<h3>From Secured</h3>";
		
		if(req.getSession(false) == null) {
			System.out.println("Session NULL");
			return Response.status(Status.UNAUTHORIZED).build();
		}
		return Response.ok(str).cookie(new NewCookie("myCokieFromSecured", "myCookieVal")).build();
	}
	
	
	@GET
	@Path("secured2")
	@Produces(MediaType.TEXT_HTML)
	public Response getSecured2() {
		System.out.println("Called From Secured2 ");
		System.out.println("Headers: "+head.getRequestHeaders());
		System.out.println("Cookies: "+head.getCookies());
		String str = "<h3>From Secured 2</h3>";
		return Response.ok(str).build();
	}
	

	
	@Path("KillSession")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response killSession() {
		String str = "<h3>Kill Session</h3>";
		//ses.invalidate();

		javax.servlet.http.Cookie[] cookies = req.getCookies();
		for(javax.servlet.http.Cookie c : cookies) {
			if(c.getName().equals("JSESSIONID")) {
				System.out.println("JSESSIONID found");
				c.setMaxAge(0);
				res.addCookie(c);
			}
		}
		
//		Cookie cookie = head.getCookies().get("JSESSIONID");
//		if(cookie != null) {
//			
//		}
		
		return Response.ok(str).build();
	}
	
	

	
}
