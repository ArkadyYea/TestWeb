package jaxrs.custom.entity.format;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import abc.User;

//-Contract for a provider that supports the conversion of a Java type to a stream.
// A MessageBodyWriter implementation may be annotated with Produces to restrict the media types for which it will be considered suitable.
//-Providers implementing MessageBodyWriter contract must be either programmatically registered in a JAX-RS runtime
// or must be annotated with @Provider annotation to be automatically discovered by the JAX-RS runtime during a provider scanning phase.
//-When you send: write, when receive: read
@Provider 
@Produces("custom/format")
public class CustomUserWriter implements MessageBodyWriter<User> { 

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return User.class.isAssignableFrom(type); 
    }

    @Override
    public long getSize(User u, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    	int size = u.getName().length() + 1 + u.getSurname().length();
    	System.out.println("Size: "+size);
        return size; 
    }

    @Override
    public void writeTo(User u, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
    		MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        
    	PrintWriter pw = new PrintWriter(entityStream);
        pw.print(u.getName()+"/"+u.getSurname());
        pw.flush();
        System.out.println("--- CustomUserWriter.writeTo()");
        
//        entityStream.write(u.getName().getBytes()); 
//        entityStream.write('/'); 
//        entityStream.write(u.getSurname().getBytes()); 

    }
    
}