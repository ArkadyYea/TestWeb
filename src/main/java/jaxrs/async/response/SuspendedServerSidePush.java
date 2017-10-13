package jaxrs.async.response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.Suspended;

//-If we post something on the server side all clients will read it
@Singleton
@Path("threads/push")
public class SuspendedServerSidePush {
	
	private List<AsyncResponse> responses = new ArrayList<>();
    private Executor executor = Executors.newSingleThreadExecutor();
	
    
	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct - SuspendedServerSidePush constructed");
	}
	
	
    @GET
	@Produces("text/plain")
	public void getQuote(@Suspended AsyncResponse response) {
		
		synchronized (responses) {
			responses.add(response);
		}
		
		System.out.println("SuspendedServerSidePush, size: "+responses.size());
	}
	
	@POST
	@Consumes("text/plain")
	public void postQuote(String val) {
		final List<AsyncResponse> doneResponses = new ArrayList<>();
		executor.execute( () -> {
			synchronized (responses) {
				for (AsyncResponse response : responses) {
					if(response.isSuspended()) response.resume(val);
					if(response.isDone()) doneResponses.add(response);		//Only works this way, if remove() called directly ConcurrencyException
				}
				responses.removeAll(doneResponses);
			}
		});
	}
	
	@GET
	@Path("test")
	@Produces("text/plain")
	public String test() {
		for(AsyncResponse ar : responses) {
			System.out.println("ar: "+ar+", done: "+ar.isDone());
		}
		
		return "Responses: "+responses.size();
	}
	
	
	@GET
	@Path("long")
	//@Produces("application/json")
	public void ytdReport(@Suspended AsyncResponse response) {
		executor.execute( new Runnable() {
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Report report = generateYTDReportFor(product);
	            response.resume("");
	        }
	    });
	}

	//protected Report generateYTDReportFor(String product) {    …    }
	
//	@Schedule(second="*/2", minute="*", hour="*", persistent=false)
//	public void abc() {
//		System.out.println("Scheduler, size = "+responses.size());
////		synchronized (responses) {
//			for (AsyncResponse response : responses) {
//				if (response.isDone()) {
//					responses.remove(response);
//				}
//			}
////		}
//
//	}
	
}
