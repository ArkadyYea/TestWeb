package servletAsync;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.servlet.AsyncContext;

@Stateless
public class Observer {
	
	public void getMessSucess(@Observes final AsyncContext ac) throws IOException {
		String message = (String) ac.getRequest().getAttribute("message");
		System.out.println(message);
		
		ac.start(()->{
			try {
				PrintWriter pw = ac.getResponse().getWriter();
				pw.println("From Async servlet: "+ac.getRequest().getAttribute("message")+",<br/>Thread: "+Thread.currentThread().getName()+"<br/><br/>");
				pw.flush();
				if(message.equals("Final Message")) {
					ac.complete();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
	}
}
