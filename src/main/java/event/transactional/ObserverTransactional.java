package event.transactional;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

public class ObserverTransactional {
	
	public void getTransporter(@Observes(during=TransactionPhase.AFTER_SUCCESS) Transporter t) throws IOException {
		PrintWriter pw = t.getAsyncCtx().getResponse().getWriter();
		pw.println("TransactionPhase.AFTER_SUCCESS: "+t.getInfo());
		pw.flush();
		System.out.println("Got Transporter (Sucess): "+t.getInfo());
	}
	
	public void getTransporterFail(@Observes(during=TransactionPhase.AFTER_FAILURE) Transporter t) throws IOException {
		PrintWriter pw = t.getAsyncCtx().getResponse().getWriter();
		pw.println("TransactionPhase.AFTER_FAILURE: "+t.getInfo());
		pw.flush();
		System.out.println("Got Transporter (Failre): "+t.getInfo());
	}
	
//	public void getString(@Observes(during=TransactionPhase.AFTER_SUCCESS) String s) throws IOException {
//		System.out.println("Got String (Sucess): "+s);
//	}
//	
//	public void getStringFail(@Observes(during=TransactionPhase.AFTER_FAILURE) String s) throws IOException {
//		System.out.println("Got String (Failre): "+s);
//	}
	
}
