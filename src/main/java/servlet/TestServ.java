package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jaxrs.stuff.SessionCounterListener;

@WebServlet(urlPatterns="/TestServ")
public class TestServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().println("<h2>Test Servlet</h2>");
		response.getWriter().println("<br/>");
		
		HttpSession session  = request.getSession(true);
		
		ServletContext sc = request.getServletContext();
		//sc.addListener("security.rest.SessionCounterListener");
		//sc.createListener(SessionCounterListener.class);
		System.out.println(sc.getDefaultSessionTrackingModes());
		System.out.println(sc.getEffectiveSessionTrackingModes());
		System.out.println(sc.getSessionCookieConfig());
		
	}
	
}
