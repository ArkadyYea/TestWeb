package jaxrs.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//-When a request comes in, the targeted RESTful web service is resolved, and a new instance of the matching class is created.
//-Since the life cycle is per request, the service does not have to worry about CONCURRENCY and can use instance variables safely.
@Path("lifecycle")
public class TestMethods {
	
	private int counter = 0;
	
	@PostConstruct
	public void cons() {
		System.out.println("lifecycle's @PostConstruct");
	}
	
	@PreDestroy
	public void dest() {
		System.out.println("lifecycle's @PreDestroy");
	}
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getTest() {
		String str = "This is info from lifecycle GET, counter = "+(++counter)+", Transaction = "+tsr.getTransactionKey()+", Thread: "+Thread.currentThread().getName();
		return Response.ok(str).build();
	}
	
	@Inject
	EjbToGetTransaction tr;
	
	@GET
	@Path("/transactional")
	@Produces(MediaType.TEXT_HTML)
	public Response getTest2() {
		return tr.getResponse(counter);
	}
	
	
	@Resource
	UserTransaction ut;
	
	@GET
	@Path("/transactional2")
	@Produces(MediaType.TEXT_HTML)
	public Response getTest3() throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, NotSupportedException {
		ut.begin();
		String str = "This is info from lifecycle GET, counter = "+(++counter)+", Transaction = "+tsr.getTransactionKey()+", Thread: "+Thread.currentThread().getName();
		ut.commit();
		return Response.ok(str).build();
	}
	
	
}
