package jaxrs.async.response;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//-A completion callback is invoked when the whole request processing is over, i.e. once a response for the request has been processed
// and sent back to the client or in when an unmapped exception or error is being propagated to the container.
//-Callbacks are generally used to receive notification of error conditions caused after invoking resume()
// You may have resources to clean up or even transactions to roll back or undo as a result of an asynchronous failure
@Path("threads")
public class CallbackCompletion {
	
	@Resource
	ManagedExecutorService mes;
	
	@GET
	@Path("completionCallback")
    @Produces(MediaType.TEXT_HTML)
	public void asyncProd(@Suspended AsyncResponse ar) {
		
		ar.register(new CompletionCallback() {
			
			@Override
			public void onComplete(Throwable throwable) {
				System.out.println("CompletionCallback called");
			}
		});
		
		System.out.println("Before sending response");
		ar.resume(Response.ok("Response Sent").build());
	}
	
	
}
