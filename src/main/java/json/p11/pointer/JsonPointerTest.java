package json.p11.pointer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonPointer;

//A JSON Pointer is a sequence of zero or more reference tokens, each prefixed by a '/' (%x2F) character.
//JsonPointer - add(), containsValue(), getValue(), remove(), replace()
//JsonArray, JsonObjects are immutable - operations add, replace, remove are creating new objects.
@SuppressWarnings("unused")
public class JsonPointerTest {
	
	public JsonPointerTest() {
		
		// {"langs":["Java","Net","C++","Scala","Python"]}
		JsonArray langs = Json.createArrayBuilder().add("Java").add("JavaScript").add("C++").add("Scala").add("Python").build();
		JsonObject jo = Json.createObjectBuilder().add("langs", langs).build();
		
		System.out.println(jo);
		
		JsonPointer pointer1 = Json.createPointer("/langs/0");
		JsonPointer pointer2 = Json.createPointer("/langs/1");
		JsonPointer pointer3 = Json.createPointer("/langs/5");
		
		System.out.println(pointer1.getValue(jo));
		System.out.println(pointer2.getValue(jo));
		//System.out.println(pointer3.getValue(jo));	//javax.json.JsonException: An array item index is out of range. Index: 5, Size: 5
		
		
		JsonObject newJo = pointer1.add(jo, Json.createValue("Grovy"));		//Adds BEFORE the pointer
		System.out.println(newJo);
		
		JsonObject newJo2 = pointer1.replace(jo, Json.createValue("Java 9"));
		System.out.println(newJo2);
		
		JsonObject newJo3 = pointer1.remove(jo);
		System.out.println(newJo3);
	}
	
	
	public static void main(String[] args) {
		new JsonPointerTest();
	}
}
