package event.transactional;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
public class EJBWithAsyncMethod {
	
	@Resource
	EJBContext ctx;
	
	@Asynchronous
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void async(Runnable r) {
		r.run();
	}
	
	
	public EJBContext getCtx() {
		return ctx;
	}
}
