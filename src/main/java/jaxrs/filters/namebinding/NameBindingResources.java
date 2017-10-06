package jaxrs.filters.namebinding;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//-Name binding via annotations is only supported as part of the Server API.
//-In name binding, a name-binding annotation is first defined using the @NameBinding meta-annotation.
//-The defined name-binding annotation is then used to decorate a filter or interceptor class (more than one may be decorated with the same annotation).
//-At last, the name-binding annotation is applied to the resource method(s) to which the JAX-RS provider(s) should be bound to.
//-Using the defined name-binding annotation on a JAX-RS Application subclass means that
// a provider bound by the annotation will be applied to all resource and sub-resource methods in the JAX-RS application:
//-Filter or Interceptor still has to be annotated with @Provider or registered!
@Path("nameBinding")
public class NameBindingResources {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@MyNameBinding
	public Response nameBinding() {
		String str = "Declare a new Annotation adding @NameBinding. Decorate a filter or interceptor AND a resource with the new annotation.";
		return Response.ok(str).build();
	}
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("without")
	public Response withoutNameBinding() {
		String str = "This resource is not annotated with @MyNameBinding so filter wont work.";
		return Response.ok(str).build();
	}

}
