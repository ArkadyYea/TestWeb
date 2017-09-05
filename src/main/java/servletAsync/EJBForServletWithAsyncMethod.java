package servletAsync;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class EJBForServletWithAsyncMethod {
	
	@Asynchronous
	public void async(Runnable r) {
		r.run();
	}

}
