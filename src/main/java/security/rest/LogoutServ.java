package security.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/LogOut")
public class LogoutServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().println("<h2>Log out</h2>");
		response.getWriter().println("<br/>");
		
		System.out.println("-----------------------------------");
		
		HttpSession session  = request.getSession(false);
		if(session != null) {
			System.out.println("Session NOT null "+session.getId());
			session.invalidate();
			if(request.getSession(false) == null) {
            	System.out.println("Session NULL");
            }
		} else {
			System.out.println("Session NULL");
		}
		request.logout();
		
		System.out.println("Logged out");
	}
}
