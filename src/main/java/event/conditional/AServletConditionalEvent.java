package event.conditional;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/CondServ")
public class AServletConditionalEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Event<String> event;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try {
			PrintWriter pw = response.getWriter();
			pw.println("First Test Fired<br/>");
			pw.flush();
				
			event.fire("First Test Fired");
				
			Thread.sleep(300);
				
			event.fire("Second Test Fired");
								
			pw.println("Second Test Fired<br/>");
			pw.flush();
				
		} catch (Exception e) {
			System.out.println("Exception has been thrown "+e.getMessage());
		}
	}

	
	public AServletConditionalEvent() {
        super();
    }

}
