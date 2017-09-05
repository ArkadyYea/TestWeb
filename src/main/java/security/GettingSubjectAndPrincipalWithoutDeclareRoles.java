package security;

import java.security.Principal;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GettingSubjectAndPrincipalWithoutDeclareRoles {
	
	@Resource
	SessionContext ctx;
	
	@Inject
	Principal pr;
	
	public void checkSubject() {
		System.out.println("--- EJB2 without @DeclareRoles ---");
		
		System.out.println("Principal injected: "+pr.getName());
		
		System.out.println("Is Admins?: "+ctx.isCallerInRole("Admins"));
		System.out.println("Is User?: "+ctx.isCallerInRole("user"));
		System.out.println("Is guest?: "+ctx.isCallerInRole("guest"));
		

	}
	
}
