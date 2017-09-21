package jaxrs.custom.entity.format;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import abc.User;

//-Contract for a provider that supports the conversion of a stream to a Java type.
// A MessageBodyReader implementation may be annotated with Consumes to restrict the media types for which it will be considered suitable.
//-Providers implementing MessageBodyReader contract must be either programmatically registered in a JAX-RS runtime
// or must be annotated with @Provider annotation to be automatically discovered by the JAX-RS runtime during a provider scanning phase.
//-When you send: write, when receive: read
@Provider 
@Consumes("custom/format")
public class CustomUserReader implements MessageBodyReader<User> { 

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return User.class.isAssignableFrom(type);
    }

    @Override
    public User readFrom(Class<User> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        
        String str = convertStreamToString(entityStream); 
        System.out.println("--- CustomUserReader.readFrom(): "+str);
        StringTokenizer s = new StringTokenizer(str, "/"); 
        
        User user = new User(); 
        user.setName(s.nextToken()); 
        user.setSurname(s.nextToken()); 
        return user; 
    }

    private String convertStreamToString(java.io.InputStream is) {
        try(Scanner s = new Scanner(is)) {
        	return s.hasNext() ? s.next() : "";
        }
    }
    

    
    
    
}