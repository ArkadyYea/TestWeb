package jaxrs.context;

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
@Path("context")
public class ContextAnnotation {
	
	//Warning: StandardWrapperValve[jaxrs.JAXRSConfig]: Servlet.service() for servlet jaxrs.JAXRSConfig threw exception	java.lang.NullPointerException
	//?If no session?
//	@Context
//	HttpSession ses;
	
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
	Request rq;
	@Context
	HttpServletRequest req;
	@Context
	HttpServletResponse res;
	
	@Path("requestContext")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getRequest() {
		System.out.println("getMethod(): "+req.getMethod());
		System.out.println("getAuthType(): "+req.getAuthType());
		System.out.println("getContextPath(): "+req.getContextPath());
		System.out.println("getPathInfo(): "+req.getPathInfo());
		System.out.println("getRequestURI(): "+req.getRequestURI());
		//System.out.println(req.changeSessionId());
		
		System.out.println("rq.getMethod(): "+rq.getMethod());
		
		String str = "<h3>requestContext</h3>";
		return Response.ok(str).build();
	}
	
	@Context
	HttpHeaders head;
	
	@Path("headersContext")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getHeaders() {
		String str = "<h3>headersContext</h3>";
		
		String headerString = head.getHeaderString("Authorization");
		System.out.println("Authorization: "+headerString);
		System.out.println("getLength(): "+head.getLength());
		System.out.println("getAcceptableLanguages(): "+head.getAcceptableLanguages());
		System.out.println("getAcceptableMediaTypes(): "+head.getAcceptableMediaTypes());
		System.out.println("getCookies(): "+head.getCookies());
		System.out.println("getDate(): "+head.getDate());
		System.out.println("getLanguage(): "+head.getLanguage());
		System.out.println("getMediaType(): "+head.getMediaType());
		System.out.println("getRequestHeaders(): "+head.getRequestHeaders().keySet());
		
		return Response.ok(str).build();
	}
	
	
	@Context
	Application app;
	
	@Path("applicationContext")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getApp() {
		String str = "<h3>applicationContext</h3>";
		
		System.out.println("app.getClasses(): "+app.getClasses());
		System.out.println("app.getProperties(): "+app.getProperties());
		System.out.println("app.getSingletons(): "+app.getSingletons());
		
		return Response.ok(str).build();
	}
	
	
	@Context
	UriInfo info;
	
	@Path("uri Context")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getUri() {
		String str = "<h3>uriInfoContext</h3>";
		
		System.out.println("getAbsolutePath(): "+info.getAbsolutePath());
		System.out.println("getPath(): "+info.getPath());
		System.out.println("getPath(true): "+info.getPath(true));
		System.out.println("getPath(false): "+info.getPath(false));
		System.out.println("getBaseUri(): "+info.getBaseUri());
		System.out.println("getMatchedResources(): "+info.getMatchedResources());
		System.out.println("getMatchedURIs(): "+info.getMatchedURIs());
		System.out.println("getPathParameters(): "+info.getPathParameters().keySet());
		System.out.println("getPathSegments(): "+info.getPathSegments());
		System.out.println("getQueryParameters(): "+info.getQueryParameters().keySet());
		System.out.println("getRequestUri(): "+info.getRequestUri());
		
		UriBuilder rub = info.getRequestUriBuilder();
		UriBuilder apb = info.getAbsolutePathBuilder();
		System.out.println("getRequestUriBuilder(): "+rub);
		System.out.println("getAbsolutePathBuilder(): "+apb);
		
		
		return Response.ok(str).build();
	}
	
	@Context
	Providers prov;
	
	@Path("providersContext")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getProv() {
		String str = "<h3>providersContext</h3>";
		
		System.out.println(prov);
		
		return Response.ok(str).build();
	}
	
}
