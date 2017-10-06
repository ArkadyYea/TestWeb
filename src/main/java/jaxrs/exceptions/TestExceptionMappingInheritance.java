package jaxrs.exceptions;

import java.net.ConnectException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.jar.JarException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//-JAX-RS supports exception inheritance.
//-When an exception is thrown, JAX-RS will first try to find an ExceptionMapper for that exception’s type.
//-If it cannot find one, it will look for a mapper that can handle the exception’s superclass.
//-It will continue this process until there are no more superclasses to match against.
@Path("exception")
public class TestExceptionMappingInheritance {
	
	@GET
	@Path("inheritance")
	@Produces(MediaType.TEXT_HTML)
	public Response exceptionInheritence() throws JarException, ConnectException {
		boolean test = ThreadLocalRandom.current().nextBoolean();
		if(test) {
			throw new JarException("JarException");
		} else {
			throw new ConnectException("ConnectException");
		}
	}
}
