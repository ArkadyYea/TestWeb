package jaxrs.filters.namebinding;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
@MyNameBinding
public class ContainerRequestFilterWithNameBinding implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("----- ContainerRequestFilterWithNameBinding called -----");
	}

}
