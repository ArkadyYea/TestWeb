package jaxrs21.sse;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.enterprise.concurrent.ManagedExecutorService;
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
@Path("sse")
public class ServerSentEventChat {
	
	private int id = 1;
	private SseBroadcaster broadcaster;
	@Context
	private Sse sse;
	
	@PostConstruct
	public void init() {
		broadcaster = sse.newBroadcaster();
	}
	
	
	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void register(@Context SseEventSink eventSink) throws InterruptedException {
		eventSink.send(sse.newEvent("Connected."));			//Sends only for this client, broadcaster sends for all clients.
		broadcaster.register(eventSink);
		//eventSink.close();
	}
	
		
	@POST
	@Path("form")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void sendMessageForm(@FormParam("forms_message") String message, @FormParam("forms_name") String name) {
		OutboundSseEvent messageEvent = sse.newEventBuilder()
			.name("ServerEventName")
			.id(String.valueOf(id)).comment("My Comment").data(name+": "+message+", "+id+", Thread: "+Thread.currentThread().getName()).build();
		id++;
		broadcaster.broadcast(messageEvent);
	}

	
	
	@Resource
	ManagedExecutorService mes;
	
	@POST
	@Path("formAsync")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void sendMessageFormAsync(@FormParam("forms_message") String message, @FormParam("forms_name") String name) {
		
		mes.execute( () -> {
			OutboundSseEvent messageEvent = sse.newEventBuilder()
					.name("ServerEventName")
					.id(String.valueOf(id)).comment("My Comment").data(name+": "+message+", "+id+", Thread: "+Thread.currentThread().getName()).build();
			id++;
			broadcaster.broadcast(messageEvent); 
		} );
	}
	
	
	
	
	@POST
	public void sendMessage(String message) {
		OutboundSseEvent messageEvent = sse.newEventBuilder()
				.name("ServerEventName")
				.id(String.valueOf(id)).comment("My Comment").data(message+", "+id).build();
		id++;
		broadcaster.broadcast(messageEvent);
	}
	
}
