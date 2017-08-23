package managedThreadFactory;

import java.io.IOException;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource
	ManagedThreadFactory mtf;
	
	@Resource
	ManagedExecutorService mes;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().println("<h2>Hello World from Servlet!</h2>");
		
		mtf.newThread( () -> {
			try {
				response.getWriter().println("ManagedThreadFactory response </br>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		
		mes.submit( () -> {
			try {
				response.getWriter().println("ManagedExecutorService response </br>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}
	
}
