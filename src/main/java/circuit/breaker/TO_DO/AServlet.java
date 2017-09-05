package circuit.breaker.TO_DO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BreakerkServ")
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	Test tb;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		pw.println("<h2>Hello World from Servlet!</h2>");
		
		pw.println("<br/>Transaction required: "+tb.doItNow());
		
		pw.close();
		
	}

}
