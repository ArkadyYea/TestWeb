package ejb.pooling;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//PoolingEJBServ?abc=def
@WebServlet("/PoolingEJBServ")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	PoolingTestSL sl;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().println("<h2>Hello World from Servlet!</h2>");
		response.getWriter().println(sl.test());
		
		Map<String, String[]> params = request.getParameterMap();
		System.out.println(params);
		
		System.out.println("-----------------------");
		request.login("arek", "arek");
		System.out.println(request.getUserPrincipal());
		System.out.println(request.isUserInRole("admin"));
		System.out.println("-----------------------");
		System.out.println(request.isRequestedSessionIdValid());
		System.out.println(request.getServletContext());
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURI());
		System.out.println(request.getQueryString());
		System.out.println(request.getCookies());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
