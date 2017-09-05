package concurrency.managedThreadFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ContextService;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.UserTransaction;

//https://docs.oracle.com/javaee/7/api/javax/enterprise/concurrent/ManagedThreadFactory.html
//-A ManagedThreadFactory extends the Java™ SE ThreadFactory to provide a method for creating threads for execution in a Java™ EE environment
//-Threads returned from the newThread() should implement the ManageableThread interface. The Runnable task that is allocated to the new thread
// using the ThreadFactory.newThread(Runnable) will run with the app component context of the component instance that created (looked-up) this ManagedThreadFactory instance.
//-The task runs without an explicit transaction (they do not enlist in the application component's transaction). If a transaction is required,
// use a javax.transaction.UserTransaction instance. A UserTransaction instance is available in JNDI using the name: "java:comp/UserTransaction"
//-A ManagedThreadFactory can be used with Java SE ExecutorService implementations directly.
@WebServlet(value="/ThreadFactoryManagedServ", asyncSupported=true)
public class ManagedThreadFactoryServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource
	ManagedThreadFactory mtf;
	
	@Resource
	ManagedExecutorService mes;
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	@Resource
    ContextService cs;
	
	@Resource
	UserTransaction ut;
	
	@Inject
	ManagedThreadFactoryBean tfb;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<h2>ManagedThreadFactory Test</h2>");
		pw.close();
		
		System.out.println("------------------------------------");
		System.out.println("Main thread: "+Thread.currentThread().getName());
		System.out.println("------------------------------------");
		
		getManagedThreadPool().execute(() -> {
			
			try {
				System.out.println("ManagedThreadPool using ManagedThreadFactory, thread: "+Thread.currentThread().getName()+", transaction: "+tsr.getTransactionKey());
				
				ut.begin();
				System.out.println("ManagedThreadPool with UserTransaction, thread: "+Thread.currentThread().getName()+", transaction: "+tsr.getTransactionKey());
				ut.commit();
				
				System.out.println("------------------------------------");		
				
				tfb.name();
				
				//ut.begin();
				tfb.name2();
				//ut.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		});
	}
	
	public ExecutorService getManagedThreadPool() {
		return new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), mtf);
	}
	
}
