package configurator;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConfigServ")
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	ShowBean sb;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().println("<h2>Hello World from Servlet!</h2>");
		response.getWriter().println(sb.doStg());
		response.getWriter().println("<br/>Show bean accessing ConfigProducer obj directly: "+sb.cp);
		response.getWriter().println("<br/>Show bean accessing ConfigProducer obj via method: "+sb.getName());
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public AServlet() {
        super();
    }
}
