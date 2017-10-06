package jaxrs.async;

import java.util.concurrent.CompletableFuture;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("threads")
public class JaxRsSyncAsyncThreadTest {
	
	@GET
	@Path("sync")
    @Produces(MediaType.TEXT_HTML)
	public Response syncProd() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = "Servers Sync Response: "+Thread.currentThread().getName();
		return Response.ok(res).build();
	}
	
	
	@GET
	@Path("async")
    @Produces(MediaType.TEXT_HTML)
	public void asyncProd(@Suspended AsyncResponse ar) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		CompletableFuture.supplyAsync( this::createResponse ).thenAccept( ar::resume );
	}
	
	private Response createResponse() {
		String resTxt = "Servers Async Response: "+Thread.currentThread().getName();
		return Response.ok(resTxt).build();
	}
}
