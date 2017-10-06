package jaxrs.stream;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

//https://stackoverflow.com/questions/40743623/stream-large-responses-with-jersey-asynchronously

//No need for AsyncContext?!? We can do the same with AsyncResponse, it is just a handle to execute it async
//asyncContext.start() - then it is executed async

@Path("stream")
public class AsyncStream {
	
	@Context
	HttpServletRequest request;
	
	@GET
	//@Path("async")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public void asyncStream(@Suspended AsyncResponse response) throws IOException {
		
		System.out.println("request not null? - "+request);
		
		AsyncContext asyncContext = request.getAsyncContext();		//Jersey already switched to async mode, HttpServletRequest.startAsync() -> IllegalStateExc
		System.out.println("Async mode? - "+request.isAsyncStarted()+", thread: "+Thread.currentThread().getName());
		
		asyncContext.start(()->{
			System.out.println("asyncContext.start(), thread: "+Thread.currentThread().getName());
		});
		
		ServletOutputStream os = asyncContext.getResponse().getOutputStream();
		os.setWriteListener(new WriteListener() {
			
			volatile boolean done = false;
			
			@Override
			public void onWritePossible() throws IOException {
				while (os.isReady()) {
	                if(done) {
	                    asyncContext.complete();
	                    break;
	                } else {
	                	System.out.println("OutputStream, thread: "+Thread.currentThread().getName());
	                	try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
	                    os.println("Abc, test");
	                    done = true;
	                }
	            }
			}
			
			@Override
			public void onError(Throwable t) {
				System.out.println("Error: "+t.getMessage());
				asyncContext.complete();
			}
		});
		
		//asyncContext.complete();	//The same as response.resume()
		//response.resume("Abc");
	}

}
