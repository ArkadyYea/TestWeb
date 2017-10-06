package jaxrs.exceptions.mappings;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//We can also write an ExceptionMapper for WebApplicationException or its subclasses
//This gives you complete control on how errors are handled by your application.
@Provider 
public class NotAllowedExceptions implements ExceptionMapper<NotAllowedException> { 
    
    public Response toResponse(NotAllowedException ex) { 
        return Response.status(500).entity("Customized NotAllowedException mappper: "+ex.getMessage())
        		.type(MediaType.TEXT_PLAIN).build(); 
    }
    
} 