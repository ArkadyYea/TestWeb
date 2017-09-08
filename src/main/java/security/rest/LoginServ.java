package security.rest;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/LogIn")
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().println("<h2>Log In</h2>");
		response.getWriter().println("<br/>");
		
		HttpSession session  = request.getSession(true);
		session.setAttribute("aaa", "aaa");
		
		System.out.println("-----------------------------------");
		System.out.println("Session Id before log in: "+session.getId());
		
		request.login("arek", "arek");
		boolean authenticate = request.authenticate(response);
		
		session  = request.getSession();
		if(session != null) {
			session.setAttribute("bbb", "bbb");
			System.out.println("Session NOT null");
		} else {
			System.out.println("Session NULL");
		}
		
		System.out.println("Logged In");
		System.out.println("Admins: "+request.isUserInRole("Admins"));
		System.out.println("Atribute aaa: "+session.getAttribute("aaa"));
		System.out.println("Atribute bbb: "+session.getAttribute("bbb"));
		System.out.println("Session Id after log in: "+session.getId());
		
		System.out.println("authenticate(response): " + authenticate);
        if (authenticate) {
            //request.getRequestDispatcher("/Welcome").forward(request, response);
        	//request.getRequestDispatcher("/res/secured").forward(request, response);
        	//response.sendRedirect(request.getContextPath()+"/Welcome");		//with url change
        } else {
            System.err.println(">>> ELSE: response has already been committed - no RequestDispatcher available. Why would I need it?!");
        }
	}
	
}
