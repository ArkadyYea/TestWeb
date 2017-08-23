package ejb;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AspectsEJBServ")
public class TestForAspectsServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	TestForAspects tfa;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		pw.println("<h2>EJB aspects</h2>");
		
		pw.println("EJB is just a POJO with some aspects called interceptors:");
		pw.println("<br/>Security, Transactions, State management, Threading, and Pooling. ");
		pw.println("<br/>When we throw an exception, we can see a stack trace and what happens behind the scenes.<br/><br/>");
		pw.flush();
		
		try {
			tfa.letsCrashIt();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			sw.append("<span style='font: 10px Arial;'><pre>");
			
			e.printStackTrace(new PrintWriter(sw, true));
			
			pw.println(sw.toString());
			pw.flush();
		}
		
		pw.close();
		
	}

}
