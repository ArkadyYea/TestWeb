package jaxrs.interceptors;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

//-Interface for message body reader interceptors that wrap around calls to MessageBodyReader.readFrom().
//-Providers implementing ReaderInterceptor contract must be either programmatically registered in a JAX-RS runtime
// or must be annotated with @Provider annotation to be automatically discovered by the JAX-RS runtime during a provider scanning phase.
// Message body interceptor instances may also be discovered and bound dynamically to particular resource methods.
public class MyReaderInterceptor implements ReaderInterceptor {

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        System.out.println("----- Servers MyReaderInterceptor.aroundReadFrom() called -----");
        System.out.println("getAnnotations(): "+context.getAnnotations());
        System.out.println("getGenericType(): "+context.getGenericType());
        System.out.println("getHeaders(): "+context.getHeaders());
        System.out.println("getInputStream(): "+context.getInputStream());
        System.out.println("getMediaType(): "+context.getMediaType());
        System.out.println("getPropertyNames(): "+context.getPropertyNames());
        System.out.println("getType(): "+context.getType());
        System.out.println("-------------------------------");
        return context.proceed(); 
    }

}
