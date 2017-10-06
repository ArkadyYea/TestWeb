package jaxrs.async.response;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//-JAX-RS implementation in products that support EJB must also support the use of stateless and singleton session beans as root resource classes.
//-When an EJB method is annotated with @Asynchronous, the EJB container automatically allocates the necessary resources for its execution. 
//-In this scenario, the use of an Executor is unnecessary to generate an asynchronous response.
//-There is no explicit thread management needed in this case since that is under the control of the EJB container
@Path("threads")
@Stateless
public class EjbAsynchronousJaxRs {
	
	@Asynchronous
	@GET
	@Path("ejb")
    @Produces(MediaType.TEXT_HTML)
	public void asyncProd(@Suspended AsyncResponse ar) {
		
		String resTxt = "Servers Async Response: "+Thread.currentThread().getName();
		ar.resume(Response.ok(resTxt).build());
		
		//return Response.ok(resTxt).build();
		//We cant return Response with @Asynchronous -> Async method exposed through no-interface view must have return type void or java.lang.concurrent.Future<V>
	}
	
}
