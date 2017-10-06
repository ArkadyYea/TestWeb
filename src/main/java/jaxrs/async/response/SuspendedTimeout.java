package jaxrs.async.response;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

//-If an AsyncResponse is not resumed or cancelled, it will eventually time out.
//-The default timeout is container-specific. A timeout results in a 503, “Service Unavailable,” response code sent back to the client.
@Path("threads")
public class SuspendedTimeout {
	
	@Resource
	ManagedExecutorService mes;
	
	@GET
	@Path("suspendedTimeout")
    @Produces(MediaType.TEXT_HTML)
	public void asyncProd(@Suspended AsyncResponse ar) {
		ar.setTimeout(3, TimeUnit.SECONDS);
	}
	
}
