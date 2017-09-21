package jaxrs.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

//Use the @Provider annotation
//Or make sure this JAX-RS Provider is declared in the JAX-RS Application subclass if you dont want to rely on automatic discovery.
//@Provider
@SuppressWarnings("all")
public class ContainerResponseFilterTest implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		
		System.out.println("----- ContainerResponseFilter called -----");
		
		System.out.println("getLength(): "+responseContext.getLength());
		System.out.println("getStatus(): "+responseContext.getStatus());
		System.out.println("hasEntity(): "+responseContext.hasEntity());
		System.out.println("getAllowedMethods(): "+responseContext.getAllowedMethods());
		System.out.println("getCookies(): "+responseContext.getCookies());
		System.out.println("getDate(): "+responseContext.getDate());
		System.out.println("getHeaders(): "+responseContext.getHeaders());
		System.out.println("getLanguage(): "+responseContext.getLanguage());
		System.out.println("getMediaType(): "+responseContext.getMediaType());
		System.out.println("getStatusInfo(): "+responseContext.getStatusInfo());
		
		System.out.println("-------------------------------");
	}

}
