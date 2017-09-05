package concurrency.contextService;

import java.io.IOException;
import java.security.AccessControlContext;
import java.security.AccessController;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import concurrency.contextService.DynamicContextualTest.MyIntf;

//http://docs.huihoo.com/javaone/2014/CON2258-Java-EE-7-Recipes-for-Concurrency.pdf
//-If you are interested in propagating contextual information from the Java EE container runtime to other threads. 
//-Context of application is captured upon creation of proxy
//-Proxy object methods can be run in the captured context at a later time
//-Dynamic proxy can be customized via execution properties
//-Return execution properties of given instance:
// • Map<String, String> getExecutionProperties(Object contextualProxy)

//https://www.javacodegeeks.com/2014/07/java-ee-concurrency-api-tutorial.html
//The most interesting feature of dynamic contextual objects is that the JNDI naming ctx, classloader, and security ctx are propagated to the proxied objects.
//This can be useful in a context where you are bringing J2SE impls in your Enterprise applications and want to run them within the context of the container.

//http://docs.oracle.com/javaee/7/api/javax/enterprise/concurrent/ContextService.html
//-The proxy objects follow the same rules as defined for the java.lang.reflect.Proxy class with the following additions:
// • The proxy instance will retain the context of the creator's thread.
// • The proxy instance will implement all of the interfaces specified on the createContextualProxy().
// • The object to have a proxy instance created for should not be a component managed by the Java™ EE Product Provider, such as a web component or an EJB.
// • All interface method invocations on a proxy instance run in the creator's context with the exception of hashCode, equals, toString and all other methods declared in Object.
// • The proxy instance must implement Serializable.
// • The proxied object instance must implement Serializable if the proxy instance is serialized.
// • Execution properties can be stored with the proxy instance. Custom property keys must not begin with "javax.enterprise.concurrent.".
// • Execution properties are to be used for controlling how various contextual information is retrieved and applied to the thread. 
// Although application components can store arbitrary property keys and values, it is not recommended.
// Java™ EE product providers may impose limits to the size of the keys and values.
@WebServlet(urlPatterns="/Contextual", asyncSupported=true)
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @Inject
    DynamicContextualTest dct;
    
    @Resource
    UserTransaction ut;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().println("<h2>Dynamic Contextual test</h2>");
		response.getWriter().println("<br/>");
		
		request.login("arek", "arek");
		//Subject subject = Subject.getSubject(AccessController.getContext());	//null
		Subject subject = null;
		try {
			subject = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
		} catch (PolicyContextException e) {
			e.printStackTrace();
		}
		System.out.println("Servlet: "+subject);
		
		try {
			ut.begin();
			Object proxy = dct.submitJob();
			MyIntf mi = (MyIntf) proxy;
			Runnable  r = (Runnable) proxy;
			Thread.sleep(100);
			System.out.println("------------ Servlet -----------");
			System.out.println("User Tr Status (0 - Active): "+ut.getStatus());		//javax.transaction.Status
			System.out.println("Servlet: "+mi.doSomething());
			r.run();
			ut.commit();

		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
