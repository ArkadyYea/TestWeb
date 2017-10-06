package jaxrs.stream;

import java.io.IOException;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class AsyncStream2 {
	
	@Context
	HttpServletRequest request;
	
	@Context
	HttpServletResponse res;
	
	@Resource
	ManagedExecutorService mes;
	
	@GET
	@Path("async")
	//@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public void asyncStream(@Suspended AsyncResponse response) throws IOException {
		
		System.out.println("request not null? - "+request);
		
		
		mes.execute(()->{
			try {
				Thread.sleep(2000);
				System.out.println("async, thread: "+Thread.currentThread().getName());
				res.getOutputStream().println("aaaaa");
				//res.getWriter().println("From ansync");
				response.resume("Abc");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		response.resume("Abc");
	}

}
