package jaxrs.test;

import java.util.Date;
import java.util.Locale;
import java.time.Instant;
import java.time.LocalDate;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@SuppressWarnings("unused")
@Path("test")
public class TestResponse {
	//https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Cache-Control
//  (cache-control header)
//	CacheControl() - Create a new instance of CacheControl. The new instance will have the following default settings:
//	private = false
//	noCache = false
//	noStore = false
//	noTransform = true
//	mustRevalidate = false
//	proxyRevalidate = false
//	An empty list of private fields
//	An empty list of no-cache fields
//	An empty map of cache extensions
	
	@GET
	@Path("response")
	@Produces(MediaType.TEXT_HTML)
	public Response getResp() {
		String str = "This is info from res/test/response GET, check headers";
		Response response = Response.ok(str)
			.cacheControl(CacheControl.valueOf("no-cache, no-store, must-revalidate"))
			.cookie(new NewCookie("myCokie", "myCookieVal"))
			.encoding("utf-8")
			.expires(Date.from(Instant.ofEpochMilli(System.currentTimeMillis() + 600000)))
			.header("X-my-custom-header", "value of my custom header")
			.lastModified(new Date())
			.link("Link URI", "Link rel")
			.language(Locale.ENGLISH)
			.location(info.getAbsolutePath())
			.tag("Tag string")
			.build();
		return response;
	}
	
	@Context
	UriInfo info;
}
