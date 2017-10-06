package jaxrs.filters.lifecycle;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

//Registered in DynamicFeature

//-By default, just like all the other providers, a SINGLE instance of each filter or entity interceptor is instantiated for each JAX-RS application.
// First the constructor is called, then any requested dependencies are injected, then the appropriate methods are called (simultaneously) as needed.
//-Implementations MAY offer alternative lifecycle options beyond the default one. See Section 4.1 for additional information.
//-@PostConstruct, @PreDestroy does not work here
@SuppressWarnings("unused")
public class AFilterLifecycleWithException implements ContainerRequestFilter {
	
	private int counter = 0;
	
	public AFilterLifecycleWithException() {
		System.out.println("FilterLifecycle's no-arg constructor");
	}
	
	public AFilterLifecycleWithException(@Context UriInfo info) {
		System.out.println("FilterLifecycle's constructor");
	}
	
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		counter++;
		System.out.println("FilterLifecycle's filter() called, invocation counter: "+counter);
		
		boolean test = ThreadLocalRandom.current().nextBoolean();
		if(test) {
			int abc = 2/0;
		}
	}
}
