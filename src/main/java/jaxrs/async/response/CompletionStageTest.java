package jaxrs.async.response;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

//-An alternative approach to the injection of AsyncResponse is for a resource method
// to return an instance of CompletionStage<T> as an indication to the underlying JAX-RS implementation that asynchronous processing is enabled.
//-A CompletableFuture instance is created and returned in the resource method;
// the call to complete() on that instance is executed ONLY AFTER the long running operation terminates.
@Path("threads")
public class CompletionStageTest {
	
	@Resource
	ManagedExecutorService mes;
	
	@GET
	@Path("completionStage")
	public CompletionStage<String> completionStage() {
		//No AsyncResponse here
		CompletableFuture<String> cs = new CompletableFuture<>();

		mes.submit( () -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cs.complete("completionStage(), thread: "+Thread.currentThread().getName());
		});
		
		return cs;
	}
	
	
	@GET
	@Path("completableFuture")
	public CompletionStage<String> completableFuture() {
		//No AsyncResponse here
		CompletableFuture<String> cs = new CompletableFuture<>();

		mes.submit( () -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cs.complete("completableFuture(), thread: "+Thread.currentThread().getName());
		});
		
		return cs;
	}
}
