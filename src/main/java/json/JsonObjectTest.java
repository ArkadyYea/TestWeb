package json;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonValue;

//-Needed to add to work: <dependency><groupId>org.glassfish</groupId><artifactId>javax.json</artifactId><version>1.0.4</version></dependency>
//-A JsonValue is one of the following: an object (JsonObject), an array (JsonArray),
// a number (JsonNumber), a string (JsonString), true (JsonValue.TRUE), false (JsonValue.FALSE), or null (JsonValue.NULL).
public class JsonObjectTest {
	
	public JsonObjectTest() {
		
		JsonObject jo = Json.createObjectBuilder().add("key1", "value1").add("key2", "value2").build();
		
		System.out.println(jo);
		
		
		System.out.println("jo instanceof JsonValue -> "+(jo instanceof JsonValue));
		System.out.println("jo instanceof JsonArray -> "+(jo instanceof JsonArray));
		System.out.println("jo instanceof JsonObject -> "+(jo instanceof JsonObject));
		System.out.println("jo instanceof JsonNumber -> "+(jo instanceof JsonNumber));
		
	}
	
	
	public static void main(String[] args) {
		new JsonObjectTest();
	}
}
