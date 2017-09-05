package security;

import java.io.IOException;
import java.security.AccessController;
import java.security.Principal;

import javax.inject.Inject;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/SubjectSecurityServ")
public class GettingSubjectAndPrincipalServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @Inject
    GettingSubjectAndPrincipal gsap;
    
    @Inject
    GettingSubjectAndPrincipalWithoutDeclareRoles gsap2;
    
    @Inject
	Principal pr;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().println("<h2>Getting Subject And Principal</h2>");
		response.getWriter().println("<br/>");
		
		request.login("arek", "arek");
		request.authenticate(response);
		
		System.out.println("----------- SERVLET ------------");
		System.out.println("Servlets principal -> "+pr);
		System.out.println("request.isUserInRole('Admins') -> "+request.isUserInRole("Admins"));
		System.out.println("request.isUserInRole('user') -> "+request.isUserInRole("user"));
		System.out.println("request.isUserInRole('guest') -> "+request.isUserInRole("guest"));
		
		Subject subject = Subject.getSubject(AccessController.getContext());	//null
		if(subject != null) {
			System.out.println("Subject.getSubject(AccessController.getContext()) is NOT null");
		} else {
			System.out.println("Subject.getSubject(AccessController.getContext()) is NULL");
		}
		
		Subject subject2 = null;
		try {
			subject2 = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
		} catch (PolicyContextException e) {
			e.printStackTrace();
		}
		System.out.println("Servlet: "+subject2);
		
		gsap.checkSubject();
		gsap2.checkSubject();
	}
	
}
