package jaxrs.exceptions;

import javax.ejb.ApplicationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@ApplicationException(rollback = true)
public class ApplicationExceptionTest extends WebApplicationException {
	private static final long serialVersionUID = 1L;

	public ApplicationExceptionTest(String message) {
		super(Response.status(400).header("X-Exception-Info", message).build());
	}

}
