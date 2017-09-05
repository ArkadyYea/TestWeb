package ejb.singleton;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ThreadAndTransactionEJBSingletonServ")
public class TestEjbSingletonTransAndThreadsServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	TestEjbSingletonTransAndThreads tt;
	
	@Inject
	TestEjbSingletonTransAndThreads tt2;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		pw.println("<h2>EJB Singleton Transactions and Thrads</h2>");
		
		System.out.println("---------- Synchronous -----------");
		
		long start = System.currentTimeMillis();
		tt.getSomeSleepAndShowThreadName();
		tt2.getSomeSleepAndShowThreadName();
		System.out.println("Time: "+(System.currentTimeMillis() - start)+" ms");
		
		
		System.out.println("---------- Asynchronous ----------");
		
		start = System.currentTimeMillis();
		tt.getSomeSleepAndShowThreadNameAsync();
		tt2.getSomeSleepAndShowThreadNameAsync();
		System.out.println("Time: "+(System.currentTimeMillis() - start)+" ms");
		
		pw.flush();
		
		pw.close();
		
	}

}
