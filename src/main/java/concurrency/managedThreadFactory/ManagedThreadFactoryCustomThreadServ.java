package concurrency.managedThreadFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/ThreadFactoryManagedCustomThreadServ", asyncSupported = true)
public class ManagedThreadFactoryCustomThreadServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource
	ManagedThreadFactory mtf;
	
	Runnable r = ()->{
		System.out.println("Thread created using ManagedThreadFactory, thread: "+Thread.currentThread().getName());
	};

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<h2>ManagedThreadFactory Test</h2>");
		pw.flush();
		
		System.out.println("------------------------------------");
		System.out.println("Main thread: "+Thread.currentThread().getName());
		System.out.println("------------------------------------");
		
		Thread thread = mtf.newThread(r);
		thread.setName("My Thread");
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.setDaemon(true);
		
		thread.start();
		
		pw.close();
	}

	public ExecutorService getManagedThreadPool() {
		return new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), mtf);
	}
	
}
