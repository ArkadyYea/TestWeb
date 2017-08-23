package json;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;

@Named
@RequestScoped
public class JsonToMapTestBean {
	
	public JsonToMapTestBean() {}
	
	public void test() {
		//Nie mogą być 2 takie same nazwy (jak 2 nazwy atrybutów w obiekcie?) 
		JsonObject jo = Json.createObjectBuilder().add("Arek", "Garek").add("Arek1", "Barek").build();
		
		Map<String, JsonValue> map = jo;
		
		System.out.println("Class: "+map.getClass().getName());
		System.out.println("Keys: "+map.keySet());
		System.out.println("Vals: "+map.values());
		
		CookieHandler.setDefault(new CookieManager(null /*=default in-memory store*/, CookiePolicy.ACCEPT_ALL));
	}
	
	
}
