package jaxrs21.sse;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

@Singleton
@Path("sse/test")
public class AServerSentEventSimple {
	
	@Context
	private Sse sse;
	private SseBroadcaster broadcaster;
	
	@PostConstruct
	public void init() {
		broadcaster = sse.newBroadcaster();
	}
	
	
	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void register(@Context SseEventSink eventSink) throws InterruptedException {
		//eventSink.send(sse.newEvent("Connected"));
		broadcaster.register(eventSink);
		//eventSink.close();
	}
	
	private int id = 1;
	
	@POST
	//@Produces(MediaType.TEXT_PLAIN)
	public void sentMessage(String message) {
		OutboundSseEvent messageEvent = sse.newEventBuilder()
				.name("ServerEventName")
				.id(String.valueOf(id)).comment("My Comment").data(message).build();
		id++;
		//broadcaster.broadcast(sse.newEvent(message));
		broadcaster.broadcast(messageEvent);
	}
	
}
