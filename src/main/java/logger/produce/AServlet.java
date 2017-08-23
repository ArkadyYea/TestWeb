package logger.produce;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoggerTest")
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Inject
    LoggerEJBean lejb;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<h3>Loggers Test!</h3>");
		pw.println(lejb.testLogger()+"<br/>");
		pw.println(lejb.testLoggerConsumer()+"<br/>");
		pw.println(lejb.testLoggerInterface()+"<br/>");
		pw.close();
	}

}
