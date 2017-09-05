package event.transactional;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/TransServ", asyncSupported=true)
public class AsyncServletTransactionalEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	//private Event<String> event;
	private Event<Transporter> event;
	
	@Inject
	EJBForEventWithAsyncMethod ejbw;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		AsyncContext asyncCtx = request.startAsync(request,response);
		
		ejbw.async( ()->{
			try {
				PrintWriter pw = asyncCtx.getResponse().getWriter();
				pw.println("Test Fired<br/>");
				pw.flush();
				
				//event.fire("Test Fire");
				event.fire(new Transporter("Test Fired", asyncCtx));
			
				Thread.sleep(200);
				
				if(ThreadLocalRandom.current().nextBoolean()) {
					ejbw.getCtx().setRollbackOnly();
					pw.println("Exception has been thrown.<br/>");
					pw.flush();
					throw new Exception("Random Exception");
				}
				
				//event.fire("Test Fire No Exception");
				event.fire(new Transporter("Test Fire No Exception", asyncCtx));
				
				pw.println("Test Fired No Exception<br/>");
				pw.flush();
				
			} catch (Exception e) {
				System.out.println("Exception has been thrown "+e.getMessage());
			} finally {
				try {
					Thread.sleep(300);
				} catch (Exception e) {
					e.printStackTrace();
				}
				asyncCtx.complete();
			}
		});
	}

	
	public AsyncServletTransactionalEvent() {
        super();
    }

}
