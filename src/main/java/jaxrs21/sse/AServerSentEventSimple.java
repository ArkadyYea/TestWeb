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

//	Sse - https://javaee.github.io/javaee-spec/javadocs/javax/ws/rs/sse/Sse.html
//-Server-side entry point for creating OutboundSseEvent and SseBroadcaster.
//-Instance of this interface can be injected into a field or as a parameter of a method or a constructor. 
//-The instance is thread safe, meaning that it can be shared and its method invoked from different threads without causing inconsistent internal state.
// SseBroadcaster newBroadcaster(), default OutboundSseEvent newEvent(String data) default OutboundSseEvent newEvent(String name, String data)
// OutboundSseEvent.Builder newEventBuilder()

//	SseEventSink (AutoCloseable) - https://javaee.github.io/javaee-spec/javadocs/javax/ws/rs/sse/SseEventSink.html	(Represents a client)
//-Outbound Server-Sent Events stream.
//-The instance of SseEventSink can be only acquired by injection of a resource method parameter:
//-The injected instance is then considered as a return type, so the resource method doesn't return anything, similarly as in server-side async processing.
//-The underlying client connection is kept open and the application code is able to send events.
// A server-side instance implementing the interface corresponds exactly to a single client HTTP connection.
//-The injected instance is thread safe.
//-When close() used - clinets auto reconnect! - You need to close client side to disable auto reconnect.
//-We can use send() to send a message to only 1 client
// CompletionStage<?> send(OutboundSseEvent event), void close(), boolean isClosed()

//	SseBroadcaster (AutoCloseable) - https://javaee.github.io/javaee-spec/javadocs/javax/ws/rs/sse/SseBroadcaster.html	(Sends messages to all registered clients)
//-Server-Sent events broadcasting facility.
//-Server broadcaster can be used to manage multiple server sinks. It enables sending events to all registered event outputs
// and provides facility to effectively handle exceptions and closures of individual registered event outputs.
//-Instance of this interface is thread safe.
// void register(SseEventSink sseEventSink), CompletionStage<?> broadcast(OutboundSseEvent event), void close()
// void onClose(Consumer<SseEventSink> onClose), void onError(BiConsumer<SseEventSink,Throwable> onError)



@Singleton
@Path("sse/test")
public class AServerSentEventSimple {
	
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
		eventSink.send(sse.newEvent("Connected"));
		broadcaster.register(eventSink);
		//eventSink.close();
	}
	
	
	
	@POST
	public void sentMessage(String message) {
		OutboundSseEvent messageEvent = sse.newEventBuilder()
				.name("ServerEventName")
				.id(String.valueOf(id)).comment("My Comment").data(message).build();
		id++;
		//broadcaster.broadcast(sse.newEvent(message));
		broadcaster.broadcast(messageEvent);
	}
	
}
