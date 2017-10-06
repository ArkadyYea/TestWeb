package jaxrs.parameters;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("params")
public class JaxRsParams {
	
	//http://localhost:8080/TestWeb/res/params/John-Smith
	@Path("{name}-{surname}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response basicPathParams(@PathParam("name") String name, @PathParam("surname") String surname) {
		String res = "@PathParam '{name}-{surname}'. Parameters passed -> name: "+name+", surname: "+surname;
		return Response.ok(res).build();
	}
	
	//http://localhost:8080/TestWeb/res/params/John, 404 if numbers added
	@GET
	@Path("{name: [a-zA-Z]*}")
	public Response regexPathParams(@PathParam("name") String name) {
		String res = "@PathParam '{name: [a-zA-Z]*}'. Parameters passed -> name: "+name;
		return Response.ok(res).build();
	
	}
	
	//http://localhost:8080/TestWeb/res/params/132, 404 if letters added
	@GET
	@Path("{id : \\d+}")
	public Response numberPathParams(@PathParam("id") String id) {
		String res = "@PathParam '{id : \\d+}'. Parameters passed -> id: "+id;
		return Response.ok(res).build();
	
	}
	
	
	//http://localhost:8080/TestWeb/res/params/query?name=John&surname=Smith
	@GET
	@Path("query")
	@Produces(MediaType.TEXT_HTML)
	public Response queryParams(@QueryParam("name") String name, @QueryParam("surname") String surname) {
		String res = "@QueryParam '?name=val1&surname=val2'. Parameters passed -> name: "+name+", surname: "+surname;
		return Response.ok(res).build();
	}
	
	
	//http://localhost:8080/TestWeb/res/params/matrix;num1=123;num2=234;num3=345
	@GET
	@Path("matrix")		//Otherwise ambiguous with queryParams
	@Produces(MediaType.TEXT_HTML)
	public Response matrixParams(@MatrixParam("num1") String num1, @MatrixParam("num2") String num2, @MatrixParam("num3") String num3) {
		String res = "@MatrixParam ';par1=val1;par2=val2;par3=val3'. Parameters passed -> num1: "+num1+", num2: "+num2+", num3: "+num3;
		return Response.ok(res).build();
	}
	
	
	//http://localhost:8080/TestWeb/res/params/cookie
	@GET
	@Path("cookie")
	@Produces(MediaType.TEXT_HTML)
	public Response cookieParams(@CookieParam("cookieName") String cookie) {
		String res = "@CookieParam. Cookie -> cookieName: "+cookie;
		return Response.ok(res).cookie(new NewCookie("cookieName","cookieVal")).build();
	}
	
	
	//http://localhost:8080/TestWeb/res/params/header
	@GET
	@Path("header")
	@Produces(MediaType.TEXT_HTML)
	public Response headerParams(@HeaderParam("User-Agent") String agent) {
		String res = "@HeaderParam('User-Agent') -> "+agent;
		return Response.ok(res).build();
	}
	
	
	//http://localhost:8080/TestWeb/res/params/form
	@Path("form")
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	//@Produces(MediaType.TEXT_HTML)
	public Response formParams(@FormParam("name") String name, @FormParam("surname") String surname) {
		String res = "@FormParams. Parameters passed -> name: "+name+", surname: "+surname;
		return Response.ok(res).build();
	}
	
	
	//http://localhost:8080/TestWeb/res/params/default  //?name=John&surname=Smith
	@GET
	@Path("default")
	@Produces(MediaType.TEXT_HTML)
	public Response queryParamsWithDefaultValue(@DefaultValue("John") @QueryParam("name") String name, @DefaultValue("Doe") @QueryParam("surname") String surname) {
		String res = "@QueryParam with @DefaultValue. If no querry string '?name=val1&surname=val2', default values John Doe are used. Parameters passed -> name: "+name+", surname: "+surname;
		return Response.ok(res).build();
	}
	
	
	@GET
	@Path("defaultMulti")
	@Produces(MediaType.TEXT_HTML)
	public Response queryParamsWithDefaultValueMulti(@DefaultValue("John") @QueryParam("name") String name, @DefaultValue("Doe") @QueryParam("surname") List<String> surname) {
		String res = "@QueryParam with @DefaultValue. If no querry string '?name=val1&surname=val2&surname=val3', default values John Doe are used. Params -> name: "+name+", surname: "+surname;
		return Response.ok(res).build();
	}
}
