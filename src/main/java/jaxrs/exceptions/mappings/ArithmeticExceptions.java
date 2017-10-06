package jaxrs.exceptions.mappings;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider 
public class ArithmeticExceptions implements ExceptionMapper<ArithmeticException> { 
    
    @Override
	public Response toResponse(ArithmeticException ex) {
    	return Response.status(400).entity("<h2>ArithmeticException: "+ex.getMessage()+"</h2>").type(MediaType.TEXT_HTML).build();
	}
    
}