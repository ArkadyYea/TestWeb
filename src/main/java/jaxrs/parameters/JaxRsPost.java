package jaxrs.parameters;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import abc.User;

@Path("posts")
public class JaxRsPost {
	
//	@Path("form")
//	@POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	public Response formParams(@FormParam("name") String name, @FormParam("surname") String surname) {
//		String res = "@FormParams. Parameters passed -> name: "+name+", surname: "+surname;
//		System.out.println(res);
//		return Response.ok(res).build();
//	}
	

	@Path("jsonObject")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	public Response jsonObj(JsonObject jo) {
		JsonString name = jo.getJsonString("name");
		JsonString surname = jo.getJsonString("surname");
		String res = "JsonObject, name: "+name.getString()+", surname: "+surname.getString();
		return Response.ok(res).build();
	}
	
	
	@Path("jsonArray")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	public Response jsonArray(JsonArray ja) {
		JsonString name = (JsonString) ja.get(0);
		JsonString surname = (JsonString) ja.get(1);
		String res = "JsonArray, name: "+name.getString()+", surname: "+surname.getString();
		return Response.ok(res).build();
	}
	
	
	@Path("xml")
	@POST
    @Consumes(MediaType.APPLICATION_XML)
	public Response xmlCons(User u) {
		System.out.println(u);
		return Response.ok(u, MediaType.TEXT_HTML).build();
	}
	
	@Path("xml")
	@GET
    @Produces(MediaType.TEXT_XML)
	public Response xmlProd() {
		//String res = "JsonArray, name: "+name.getString()+", surname: "+surname.getString();
		return Response.ok(new User("John","Smith")).build();
		//return new User("John","Smith");
	}
	
	
	@Path("form")
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	//@Produces(MediaType.TEXT_HTML)
	public Response formParams(@FormParam("name") String name, @FormParam("surname") String surname) {
		String res = "@FormParams. Parameters passed -> name: "+name+", surname: "+surname;
		System.out.println(res);
		return Response.ok(res).build();
	}
	
	
	
	
}
