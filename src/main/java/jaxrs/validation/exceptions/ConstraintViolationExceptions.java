package jaxrs.validation.exceptions;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

//@Provider 
public class ConstraintViolationExceptions implements ExceptionMapper<ConstraintViolationException> { 
    
    @Override
	public Response toResponse(ConstraintViolationException ex) {
    	return Response.status(400).entity("<h2>ConstraintViolationException:</h2><br/>"+ex.getMessage()).type(MediaType.TEXT_HTML).build();
	}
    
}