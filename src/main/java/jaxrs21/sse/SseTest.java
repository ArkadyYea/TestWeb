package jaxrs21.sse;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

@Singleton
@Path("sse2")
public class SseTest {
	
	private SseBroadcaster broadcaster;
	private Sse sse;
	
	@GET
	//@Produces(MediaType.SERVER_SENT_EVENTS)
	@Produces("text/event-stream")
	public void sseTest(@Context Sse sse, @Context SseEventSink eventSink) {
		System.out.println("---------sse: "+sse);
		System.out.println("---------es: "+eventSink);
		
		this.sse = sse;
		if(broadcaster == null) {
			this.broadcaster = sse.newBroadcaster();
		}
		this.broadcaster.register(eventSink);
		
		//for(int i = 0; i< 4 ; i++) {
			System.out.println("*");
			this.broadcaster.broadcast(this.sse.newEvent("Timer: " + System.currentTimeMillis()));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//}
		eventSink.close();
	}
	
	
	//https://stackoverflow.com/questions/13092567/automatic-ejb-timer-on-glassfish-server-not-triggering/13102822#13102822
	//@Schedule(second="*/2", minute="*", hour="*")		//DOES NOT WORK!?!?!
	//@Schedule(second="*/2", minute="*", hour="*", persistent=false)
	public void publish() {
		System.out.println("*");
		this.broadcaster.broadcast(this.sse.newEvent("Timer: " + System.currentTimeMillis()));
	}
}
