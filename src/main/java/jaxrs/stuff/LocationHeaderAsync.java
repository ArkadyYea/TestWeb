package jaxrs.stuff;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@SuppressWarnings("unused")
@Path("location")
public class LocationHeaderAsync {
	
	@Context
	UriInfo info;
	
	@Path("sync")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response sync() {
		return Response.created(info.getAbsolutePath()).entity("sync").build();
	}
	
	//curl -i http://localhost:8080/TestWeb/res/location/async
	@Path("async")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public void abc(@Suspended AsyncResponse ar) {
		System.out.println(info);
		Consumer<Object> con = ar::resume;
		//Supplier<Object> sup = this::doIt;			//We cant use it because Info is NOT accessible there!!!!!!
		//CompletableFuture.supplyAsync(sup).thenAccept(con);
		
		Response rs = Response.created(info.getAbsolutePath()).entity("async").build();
		CompletableFuture.supplyAsync( () -> rs ).thenAccept(con);
		
	}
	
	
	
	private Response doIt() {
		//Info is NOT accessible here!!!!!!
		
		//System.out.println(ingo.getPath());
		//System.out.println(info.getAbsolutePath());
		//System.out.println(info);
		//return Response.created(info.getAbsolutePath()).entity("async").build();
		return Response.created(info.getAbsolutePath()).entity("async").build();
	}
	
}
