package jaxrs.async.response;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//-A resource method that wants to produce a response asynchronously must inject as a method parameter AsyncResponse using the special annotation @Suspended.
//-asyncProd() forks a (non-request) thread to execute a long running operation and returns immediately.
//-Once the execution of the long running operation is complete, the connection is resumed and 
// the response returned by calling resume() on the injected instance of AsyncResponse.
@Path("threads")
public class SuspendedTest {
	
	@Resource
	ManagedExecutorService mes;
	
	@GET
	@Path("suspended")
    @Produces(MediaType.TEXT_HTML)
	public void asyncProd(@Suspended AsyncResponse ar) {
		
		CompletableFuture.supplyAsync( this::createResponse, mes ).thenAccept( ar::resume );
	}
	
	private Response createResponse() {
		String resTxt = "Servers Async Response: "+Thread.currentThread().getName();
		return Response.ok(resTxt).build();
	}
}
