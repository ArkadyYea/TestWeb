package json.p10;

import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.json.JsonWriter;

public class JsonReaderWriterTest {
	
	public JsonReaderWriterTest() {
		
		JsonArray ja = Json.createArrayBuilder().build();
		
		try(JsonReader reader = Json.createReader(getClass().getResourceAsStream("array.json"))) {
			ja = reader.readArray();
		} catch (Exception e) {
			ja = Json.createArrayBuilder().add("There was an error").build();
		}
		
		System.out.println(ja);
		
		try(JsonWriter writer = Json.createWriter(new PrintWriter("arrayWriten.json"))) {				//D:/aaaa/TestWeb/
		///D:/aaaa/TestWeb/target/classes/json/
		//try(JsonWriter writer = Json.createWriter(new PrintWriter(getClass().getResource(".").getPath()+ "arrayWriten.json"))) {
			writer.writeArray(ja);
		} catch (Exception e) {
			e.printStackTrace();
			//writer.writeArray(Json.createArrayBuilder().add("There was an error").build());
		}
		
	}
	
	
	public static void main(String[] args) {
		new JsonReaderWriterTest();
	}
}
