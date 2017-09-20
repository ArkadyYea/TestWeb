package jaxrs.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("insecured")
public class RestInsecured {
	
	@Context
	SecurityContext sc;
	
	@Context
	HttpServletRequest request;
	
	@GET
	@Path("login")
	@Produces(MediaType.TEXT_HTML)
	public Response login() {
        
		HttpSession session  = request.getSession(true);
		System.out.println("-----------------------------------");
		System.out.println("Session Id before log in: "+session.getId());
		
        try {
			request.login("arek", "arek");
		} catch (ServletException e) {
			e.printStackTrace();
		}
        
        session = request.getSession();
        
        
        if(session != null) {
        	session.setAttribute("abc", "abc");
            System.out.println("Logged in, Session Id: "+session.getId());
        } else {
        	System.out.println("Logged in, session NULL");
        }
        
        return Response.ok("Logged in").build();
	}

	@GET
	@Path("logout")
	@Produces(MediaType.TEXT_HTML)
	public Response logout() {
        HttpSession session  = request.getSession(false);
		
        System.out.println("-----------------------------------");
        if(session != null) {
			System.out.println("Session NOT null");
			session.invalidate();
			System.out.println("Session destroyed "+session.getId());
			if(request.getSession(false) == null) {
            	System.out.println("Session NULL");
            }
		} else {
			System.out.println("Session NULL");
		}
		
		try {
			request.logout();
            System.out.println("Logged out");
		} catch (ServletException e) {
			e.printStackTrace();
		}
        return Response.ok("Logged out").build();
	}
	

	@GET
	@Path("info")
	@Produces(MediaType.TEXT_HTML)
	public Response getSecurityContext() {
		String str = "<h3>Insecured info</h3>";
		System.out.println("-----------------------------------");
		System.out.println("getAuthenticationScheme(): "+sc.getAuthenticationScheme());
		System.out.println("isSecure(): "+sc.isSecure());
		System.out.println("isUserInRole('Admins'): "+sc.isUserInRole("Admins"));
		System.out.println("getUserPrincipal(): "+sc.getUserPrincipal());
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			System.out.println("Session NOT null, attribute abc = "+session.getAttribute("abc"));
		} else {
			System.out.println("Session NULL");
		}
		
		return Response.ok(str).build();
	}
	
	
	@GET
	@Path("sessioninfo")
	@Produces(MediaType.TEXT_HTML)
	public Response getSessionInfo() {
		System.out.println("-----------------------------------");
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			System.out.println(session.getClass());
			System.out.println("Session NOT null");
			System.out.println(session.getId());
			System.out.println(session.getAttributeNames());
			
		} else {
			System.out.println("Session NULL");
		}
		
		return Response.ok("Session Info").build();
	}
}
