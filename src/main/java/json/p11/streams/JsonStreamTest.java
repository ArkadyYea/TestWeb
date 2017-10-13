package json.p11.streams;

import java.util.Comparator;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonPointer;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.stream.JsonCollectors;

@SuppressWarnings("unused")
public class JsonStreamTest {
	
	public JsonStreamTest() {
		
		// {"langs":["Java","Net","C++","Scala","Python"]}
		JsonArray langs = Json.createArrayBuilder().add("Java").add("JavaScript").add("C++").add("Scala").add("Python").build();
		JsonObject jo = Json.createObjectBuilder().add("langs", langs).build();
		
		System.out.println(jo);
		
		JsonArray collected1 = jo.getJsonArray("langs").stream()
			//.filter( jv -> ((JsonString) jv).getString().startsWith("Java") )
			.filter( jv -> jv.toString().startsWith("Java") )
			.collect(JsonCollectors.toJsonArray());
		System.out.println(collected1);
		
		JsonValue str = Json.createValue(1);
		System.out.println(str);
		System.out.println(str.asJsonArray());
		
//		jo.getJsonArray("langs").stream()
//				.filter( jv -> ((JsonString) jv).getString().startsWith("Java") )
//				.collect(JsonCollectors.toJsonObject());
//		System.out.println(collected1);
	}
	
	
	public static void main(String[] args) {
		new JsonStreamTest();
	}
}
