package ejb.pooling;

import java.io.IOException;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PoolingEJBServ1")
public class ServletTest1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PoolingTestSL sl;
		try {
			sl = InitialContext.doLookup("java:global/TestWeb/PoolingTestSL");
			response.setContentType("text/html");
			response.getWriter().println("<h2>Hello World from Servlet1!</h2>");
			response.getWriter().println(sl.test());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
