package jaxrs.interceptors;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

//-Interface for message body writer interceptors that wrap around calls to MessageBodyWriter.writeTo().
//-Providers implementing WriterInterceptor contract must be either programmatically registered in a JAX-RS runtime
// or must be annotated with @Provider annotation to be automatically discovered by the JAX-RS runtime during a provider scanning phase.
// Message body interceptor instances may also be discovered and bound dynamically to particular resource methods.
public class MyWriterInterceptor implements WriterInterceptor {

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        System.out.println("----- Servers MyWriterInterceptor.aroundWriteTo() called -----");
        System.out.println("getAnnotations(): "+context.getAnnotations());
        System.out.println("getEntity(): "+context.getEntity());
        System.out.println("getGenericType(): "+context.getGenericType());
        System.out.println("getHeaders(): "+context.getHeaders());
        System.out.println("getMediaType(): "+context.getMediaType());
        System.out.println("getOutputStream(): "+context.getOutputStream());
        System.out.println("getPropertyNames(): "+context.getPropertyNames());
        System.out.println("getType(): "+context.getType());
        System.out.println("-------------------------------");
        context.proceed(); 
    }
    
}
