package json.p11.pointer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonMergePatch;
import javax.json.JsonObject;
import javax.json.JsonPatch;
import javax.json.JsonValue;

//
public class ATest {
	
	public ATest() {
		
		JsonArray langs = Json.createArrayBuilder().add("Java").add("JavaScript").add("C++").add("Scala").add("Python").build();
		JsonArray langs2 = Json.createArrayBuilder().add("Java").add("JavaScript").build();
		
		JsonPatch createDiff = Json.createDiff(langs2,  langs);
		System.out.println(createDiff);
		
		JsonPatch createDiff2 = Json.createDiff(langs,  langs2);
		System.out.println(createDiff2);
		
		JsonPatch createPatch = Json.createPatch(langs);
		System.out.println(createPatch);
		
		JsonMergePatch createMergeDiff = Json.createMergeDiff(langs, langs2);
		System.out.println(createMergeDiff.toJsonValue());
		JsonValue apply = createMergeDiff.apply(langs);
		
		
		JsonMergePatch createMergePatch = Json.createMergePatch(createPatch.toJsonArray());
		System.out.println(createMergePatch.toJsonValue());
	}
	
	
	public static void main(String[] args) {
		new ATest();
	}
}
