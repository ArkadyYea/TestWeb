package jaxrs.exceptions.mappings;

import java.io.IOException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//-JAX-RS supports exception inheritance.
//-When an exception is thrown, JAX-RS will first try to find an ExceptionMapper for that exception’s type.
//-If it cannot find one, it will look for a mapper that can handle the exception’s superclass.
//-It will continue this process until there are no more superclasses to match against.
@Provider 
public class IOExceptions implements ExceptionMapper<IOException> { 
    
    public Response toResponse(IOException ex) { 
        return Response.status(500).entity("This is IOException mappper, class of the original exception: "+ex.getClass().getName())
        		.type(MediaType.TEXT_PLAIN).build(); 
    }
    
} 