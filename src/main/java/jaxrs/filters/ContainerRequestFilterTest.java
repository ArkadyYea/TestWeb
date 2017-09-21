package jaxrs.filters;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

//Use the @Provider annotation
//Or make sure this JAX-RS Provider is declared in the JAX-RS Application subclass if you dont want to rely on automatic discovery.
//@Provider
@SuppressWarnings("all")

//-Filters can be: pre-matching filters and post-matching filters.
//-A post-matching filter is applied only after a suitable resource method has been selected to process the request.
// Such filters can not influence the resource method matching process.
//-A pre-matching filter (annotated with @PreMatching) is executed before the request matching process is started.
// This filter can influence which resource method will be matched e.g. by changing the HTTP method or by changing the URI etc.
//-The resource matching is a process of finding a resource method that should be executed based on the request path and other request parameters.
@PreMatching
@Priority(1)
public class ContainerRequestFilterTest implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		System.out.println("----- ContainerRequestFilter called, priority 1 -----");
		
        System.out.println("getMethod(): "+requestContext.getMethod());
        System.out.println("hasEntity(): "+requestContext.hasEntity());
        System.out.println("getAcceptableLanguages(): "+requestContext.getAcceptableLanguages());
        System.out.println("getAcceptableMediaTypes(): "+requestContext.getAcceptableMediaTypes());
        System.out.println("getCookies(): "+requestContext.getCookies());
        System.out.println("getDate(): "+requestContext.getDate());
        System.out.println("getHeaders(): "+requestContext.getHeaders());
        System.out.println("getLength(): "+requestContext.getLength());
        System.out.println("getPropertyNames(): "+requestContext.getPropertyNames());
        System.out.println("getUriInfo().getPath(): "+requestContext.getUriInfo().getPath());
        System.out.println("getEntityStream(): If used in a filter is not available in response");
        
        System.out.println("-------------------------------");
        
        //boolean redirect = ThreadLocalRandom.current().nextBoolean();
        boolean redirect = false;

        if (redirect) {
        	//If no @PreMatching annotation, setRequestUri() throws:
        	// java.lang.IllegalStateException: Method could be called only in pre-matching request filter.
        	requestContext.setRequestUri(URI.create("/TestWeb/res/test"));

        	
        }
	}

}
