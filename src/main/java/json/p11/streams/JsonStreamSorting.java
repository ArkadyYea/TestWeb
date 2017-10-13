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
public class JsonStreamSorting {
	
	public JsonStreamSorting() {
		
		// {"langs":["Java","Net","C++","Scala","Python"]}
		JsonArray langs = Json.createArrayBuilder().add("Java").add("JavaScript").add("C++").add("Scala").add("Python").build();
		JsonObject jo = Json.createObjectBuilder().add("langs", langs).build();
		
		System.out.println(jo);
		
		Comparator<JsonValue> comparator = new Comparator<JsonValue>() {

			@Override
			public int compare(JsonValue o1, JsonValue o2) {
				return o1.toString().compareTo(o2.toString());
			}
		};
		
		//java.lang.ClassCastException: org.glassfish.json.JsonStringImpl cannot be cast to java.lang.Comparable
		//JsonArray collect = langs.stream().sorted().collect(JsonCollectors.toJsonArray());
		
		JsonArray collect = langs.stream().sorted(comparator).collect(JsonCollectors.toJsonArray());
		System.out.println(collect);
		
		JsonArray collect2 = langs.stream().sorted( (o1, o2) -> { return o1.toString().compareTo(o2.toString()); }).collect(JsonCollectors.toJsonArray());
		System.out.println(collect2);
		
	}
	
	
	public static void main(String[] args) {
		new JsonStreamSorting();
	}
}
