package jaxrs;

import injection.point.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("test")
@Stateless
public class JaxRsTestBean {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String basicTest() {
		return "This is info from res/test REST";
	}

	@GET
	@Path("json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response userJSON() {
		return Response.ok(getUsers().get(0)).build();
	}

	@GET
	@Path("jsons")		//Server error if AL set as entity: MessageBodyWriter not found for media type=application/json, type=class java.util.ArrayList
	@Produces(MediaType.APPLICATION_JSON)
	public Response userJSONArray() {
		List<User> users = getUsers();
		//User[] usersArr = users.toArray(new User[users.size()]);				//ok
		//User[] usersArr = users.stream().toArray(User[]::new);
		GenericEntity<List<User>> ge = new GenericEntity<List<User>>(users) {};	//ok
		return Response.ok(ge).build();
	}
	
	@GET
	@Path("jsonss")		//Works
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> userJSONArray2() {
		return getUsers();
	}
	
	@GET
	@Path("jsonStream")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonArray streamJSON() {
		try(JsonReader reader = Json.createReader(getClass().getResourceAsStream("array.json"))) {
			return reader.readArray();
		} catch (Exception e) {
			return Json.createArrayBuilder().add(e.getMessage()).build();
		}
	}
	
	@Resource
	ManagedExecutorService mes;
	
	@GET
	@Path("jsonAsync")
	@Produces(MediaType.APPLICATION_JSON)
	public void asyncJSON(@Suspended AsyncResponse response) {
		response.setTimeout(2, TimeUnit.SECONDS);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JsonArray  ja = Json.createArrayBuilder().add("This").add("is").add("a").add("json").add("array").build();
		CompletableFuture.supplyAsync( () -> ja, mes ).thenAccept( response::resume);
	}
	
	@GET
	@Path("xml")
	@Produces(MediaType.APPLICATION_XML)
	public Response userXML() {
		return Response.ok(getUsers().get(0)).build();
	}
	
	private List<User> getUsers() {
		List<User> list = new ArrayList<>();
		list.add(new User("John", "Doe"));
		list.add(new User("Aaron", "Smith"));
		list.add(new User("Jessica", "Marconi"));
		return list;
	}
}
