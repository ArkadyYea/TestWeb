package jaxrs.exceptions.mappings;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider 
public class EntityNotFoundExceptions implements ExceptionMapper<EntityNotFoundException> { 
    
    public Response toResponse(javax.persistence.EntityNotFoundException ex) { 
        return Response.status(404).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN).build(); 
    }
    
} 