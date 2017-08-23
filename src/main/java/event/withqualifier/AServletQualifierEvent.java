package event.withqualifier;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/QualServ")
public class AServletQualifierEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@Test("abc")
	//private Event<String> event;
	private Event<Integer> event;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try {
			PrintWriter pw = response.getWriter();
			pw.println("First Test Fired<br/>");
			pw.flush();
			
			//event.fire("First Test Fired");
			//event.select(new AnnotationLiteral<Test>(){ public String name() { return "abc"; } }).fire(1);	//FAIL
			
			Annotation an = new TestAnnotationLiteral() {
				private static final long serialVersionUID = 1L;
				public String value() {	return "abc"; } 
			};
			event.select(an).fire(1);		//OK
			
			//event.fire(1);
			
		} catch (Exception e) {
			System.out.println("Exception has been thrown "+e.getMessage());
		}
	}

	
	public AServletQualifierEvent() {
        super();
    }

}
