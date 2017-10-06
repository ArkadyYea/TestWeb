package jaxrs.lifecycle;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.ws.rs.core.Response;

@Stateless
public class EjbToGetTransaction {
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	public String getString(int counter) {
		return "This is info from lifecycle GET, counter = "+(++counter)+", Transaction = "+tsr.getTransactionKey()+", Thread: "+Thread.currentThread().getName();
	}

	public Response getResponse(int counter) {
		String res = "This is info from lifecycle GET, counter = "+(++counter)+", Transaction = "+tsr.getTransactionKey()+", Thread: "+Thread.currentThread().getName();
		return Response.ok(res).build();
	}

}