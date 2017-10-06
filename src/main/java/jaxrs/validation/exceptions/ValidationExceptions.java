package jaxrs.validation.exceptions;

import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

//@Provider 
public class ValidationExceptions implements ExceptionMapper<ValidationException> { 
    
    @Override
	public Response toResponse(ValidationException ex) {
    	return Response.status(400).entity("<h2>ValidationException: "+ex.getMessage()+"</h2>").type(MediaType.TEXT_HTML).build();
	}
    
}