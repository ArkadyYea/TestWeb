package json.p10;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonValue;

//-Needed to add to work: <dependency><groupId>org.glassfish</groupId><artifactId>javax.json</artifactId><version>1.0.4</version></dependency>
//-A JsonValue is one of the following: an object (JsonObject), an array (JsonArray),
// a number (JsonNumber), a string (JsonString), true (JsonValue.TRUE), false (JsonValue.FALSE), or null (JsonValue.NULL).
public class JsonArrayTest {
	
	public JsonArrayTest() {
		
		JsonArray arr = Json.createArrayBuilder().add("abc").add("def").build();
		
		System.out.println(arr);
		
		System.out.println("arr instanceof JsonValue -> "+(arr instanceof JsonValue));
		System.out.println("arr instanceof JsonArray -> "+(arr instanceof JsonArray));
		System.out.println("arr instanceof JsonObject -> "+(arr instanceof JsonObject));
		System.out.println("arr instanceof JsonNumber -> "+(arr instanceof JsonNumber));
		
	}
	
	
	public static void main(String[] args) {
		new JsonArrayTest();
	}
}
