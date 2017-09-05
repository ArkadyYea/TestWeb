package concurrency.contextService;

import java.security.AccessController;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.concurrent.ContextService;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedTask;
import javax.inject.Inject;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import javax.transaction.TransactionSynchronizationRegistry;

//-Return execution properties of given instance:
// • Map<String, String> getExecutionProperties(Object contextualProxy)
@Stateless
public class DynamicContextualTest {
	
	@Resource
	ContextService cs;
	
	@Resource
	ManagedExecutorService mes;
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	@Resource
	SessionContext ctx;
	
	@Inject
	Principal pr;
	
	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Object submitJob() {
		System.out.println("------------ EJB --------------");
		System.out.println("method submitJob(), thread: "+Thread.currentThread().getName()+", transaction: "+tsr.getTransactionKey());
		
		Map<String, String> map = new HashMap<>();
		map.put("testKey", "testValue");
		map.put(ManagedTask.TRANSACTION, "USE_TRANSACTION_OF_EXECUTION_THREAD");
		
		Object proxy = cs.createContextualProxy(new RunnableTask(), map, Runnable.class, MyIntf.class);
		
		System.out.println("Proxy class: "+proxy.getClass());
		System.out.println("Proxy class name: "+proxy.getClass().getName());
		
		Map<String, String> props = cs.getExecutionProperties(proxy);
		System.out.println("Props returned from proxy: "+props);
		
		
		System.out.println("------------ Proxy ------------");
		System.out.println("MyIntf from proxy: "+((MyIntf)proxy).doSomething());	//Transaction of a caller
		mes.execute((Runnable)proxy);												//No transaction in new thread
		
		System.out.println("Principal injected: "+pr.getName());
		//Subject subject = Subject.getSubject(AccessController.getContext());
		Subject subject = null;
		try {
			subject = (Subject)PolicyContext.getContext("javax.security.auth.Subject.container");
		} catch (PolicyContextException e) {
			e.printStackTrace();
		}
		System.out.println("EJB: "+subject);
		System.out.println(pr.implies(subject));
		
		System.out.println("Principal: "+ctx.getCallerPrincipal());					//Sometimes works, sometimes not
		System.out.println("Is Admin?: "+ctx.isCallerInRole("admin"));
		
		
		return proxy;
	}
	
	
	private class RunnableTask implements Runnable, MyIntf {
		@Override
		public void run() {
			System.out.println("Runnable from proxy, thread: "+Thread.currentThread().getName()+", transaction: "+tsr.getTransactionKey());
			
			Subject subject = null;
			try {
				subject = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
			} catch (PolicyContextException e) {
				e.printStackTrace();
			}
			System.out.println("RunnableTask: "+subject);			
		}

		@Override
		public String doSomething() {
			return "String returned From Impl of MyIntf"+", thread: "+Thread.currentThread().getName()+", transaction: "+tsr.getTransactionKey();
		}

		
	}
	
	//-Must be public! Otherwise: java.lang.IllegalAccessException: Class org.glassfish.enterprise.concurrent.internal.ContextProxyInvocationHandler
	// can not access a member of class concurrency.ContextService2.DynamicContextualTest$MyIntf with modifiers "public abstract"
	public interface MyIntf{
		public String doSomething();
	}
}
