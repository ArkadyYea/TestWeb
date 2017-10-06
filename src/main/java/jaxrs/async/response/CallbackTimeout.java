package jaxrs.async.response;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

//-A timeout value can be specified when suspending a connection to avoid waiting for a response indefinitely.
//-JAX-RS impls are REQUIRED to generate a ServiceUnavailableException, a subclass of WebApplicationException with its status set to 503,
// if the timeout value is reached and no timeout handler is registered. The exception MUST be processed as described in sec 3.3.4.
//-If a registered timeout handler resets the timeout value or resumes the connection and returns a response,
// JAX-RS implementations MUST NOT generate an exception.
@Path("threads")
public class CallbackTimeout {
	
	@Resource
	ManagedExecutorService mes;
	
	@GET
	@Path("timeout")
    @Produces(MediaType.TEXT_HTML)
	public void asyncProd(@Suspended AsyncResponse ar) {
		
		ar.setTimeout(2, TimeUnit.SECONDS);
		ar.setTimeoutHandler(new TimeoutHandler() {
	        public void handleTimeout(AsyncResponse ar) {
	            ar.resume(Response.status(Status.SERVICE_UNAVAILABLE).entity("Operation timed out -- please try again, thread: "
	            		+Thread.currentThread().getName()).build());
	        }
	    });

		CompletableFuture.supplyAsync( this::createResponse, mes ).thenAcceptAsync( ar::resume );
	}
	
	private Response createResponse() {
		try {
			int time = ThreadLocalRandom.current().nextInt(3);
			System.out.println("time: "+time);
			Thread.sleep((time+1)*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String resTxt = "It randomly times out. Servers Async Response: "+Thread.currentThread().getName();
		return Response.ok(resTxt).build();
	}
}
