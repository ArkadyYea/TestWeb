package ejb.pooling;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PoolingEJBServ2")
public class ServletTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	PoolingTestCDI sl;
    
    public ServletTest2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().println("<h2>Hello World from Servlet2!</h2>");
		response.getWriter().println(sl.test());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
