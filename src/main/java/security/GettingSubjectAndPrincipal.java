package security;

import java.security.Principal;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;

@Stateless
@DeclareRoles({"Admins","user","guest"})		//MUST be declared for method to work
public class GettingSubjectAndPrincipal {
	
	@Resource
	SessionContext ctx;
	
	@Inject
	Principal pr;
	
	public void checkSubject() {
		System.out.println("------------ EJB --------------");
		
		System.out.println("Principal injected: "+pr.getName());
		
		//Subject subject = Subject.getSubject(AccessController.getContext());		//null - does not work
		
		Subject subject = null;
		try {
			subject = (Subject)PolicyContext.getContext("javax.security.auth.Subject.container");
		} catch (PolicyContextException e) {
			e.printStackTrace();
		}
		System.out.println("EJB: "+subject);
		System.out.println("pr.implies(subject) -> "+pr.implies(subject));
		
		System.out.println("Principal, ctx.getCallerPrincipal() -> "+ctx.getCallerPrincipal());
		System.out.println("Is Admins?: "+ctx.isCallerInRole("Admins"));
		System.out.println("Is User?: "+ctx.isCallerInRole("user"));
		System.out.println("Is Administrator?: "+ctx.isCallerInRole("Administrator"));
		

	}
	
}
