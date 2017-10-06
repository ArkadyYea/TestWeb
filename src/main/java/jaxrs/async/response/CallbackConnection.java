package jaxrs.async.response;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.ConnectionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//-Asynchronous request processing lifecycle callback that receives connection related asynchronous response lifecycle events.
//-It allows you to be notified if the socket connection is disconnected while processing the response. Connection is lost or closed by the client.
//-Callbacks are generally used to receive notification of error conditions caused after invoking resume()
// You may have resources to clean up or even transactions to roll back or undo as a result of an asynchronous failure

//-Support for this type of callback by JAX-RS runtime is OPTIONAL!
@Path("threads")
public class CallbackConnection {
	
	@Resource
	ManagedExecutorService mes;
	
	@GET
	@Path("connectionCallback")
    @Produces(MediaType.TEXT_HTML)
	public void asyncProd(@Suspended AsyncResponse ar) {
		
		ar.register(new ConnectionCallback() {
			@Override
			public void onDisconnect(AsyncResponse disconnected) {
				System.out.println("ConnectionCallback called: "+ar);
			}
		});
		
		System.out.println("Before sending response");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ar.resume(Response.ok("Response Sent").build());
	}
	
	
}
