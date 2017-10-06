package jaxrs.async.response;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

//-Each of the cancel() methods is really a precanned call to resume():
//-Internally, a Response object is built with a 503 status code.
//-For cancel() methods that accept input, the parameter is used to initialize a Retry-After HTTP response header.
@Path("threads")
public class SuspendedCancel {
	
	@Resource
	ManagedExecutorService mes;
	
	@GET
	@Path("suspendedCancel")
    @Produces(MediaType.TEXT_HTML)
	public void asyncProd(@Suspended AsyncResponse ar) {
		
		//ar.cancel();		//-> response.resume(Response.status(503).build());
		ar.cancel(100);		//-> response.resume(Response.status(503).header(HttpHeaders.RETRY_AFTER, 100).build());
		//ar.cancel(Date);	//-> response.resume(Response.status(503).header(HttpHeaders.RETRY_AFTER, date).build());
	}
	
}
