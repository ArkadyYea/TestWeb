package jaxrs21.reactive;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//-JAX-RS 2.1 defines a new type of invoker called RxInvoker, as well a default implemetation of this type CompletionStageRxInvoker
// that is based on the Java 8 type CompletionStage (CompletableFuture). There is a new rx() which is used in a similar manner to async() (Client side)
@Path("reactive")
public class ReactiveTest {
	
	//Used on client side
	@GET
	@Path("react1")
	@Produces(MediaType.TEXT_PLAIN)
	public Response react1() throws InterruptedException {
		Thread.sleep(2000);
		String res = "Reactive-1, thread: "+Thread.currentThread().getName();
		return Response.ok(res).build();
	}
	
	@GET
	@Path("react2")
	@Produces(MediaType.TEXT_PLAIN)
	public Response react2() throws InterruptedException {
		Thread.sleep(2000);
		String res = "Reactive-2, thread: "+Thread.currentThread().getName();
		return Response.ok(res).build();
	}
	
}
