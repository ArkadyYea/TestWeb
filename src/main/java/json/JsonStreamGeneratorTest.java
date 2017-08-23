package json;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

//-Writes JSON data to an output source in a streaming way.
//-The class Json contains methods to create generators for character or output streams (Writer and OutputStream).
public class JsonStreamGeneratorTest {
	
	public JsonStreamGeneratorTest() throws FileNotFoundException {
		
		JsonGenerator generator = Json.createGenerator(new PrintWriter("JsonGenerator.json"));
		generator
	     .writeStartObject()
	         .write("firstName", "John")
	         .write("lastName", "Smith")
	         .write("age", 25)
	         .writeStartObject("address")
	             .write("streetAddress", "21 2nd Street")
	             .write("city", "New York")
	             .write("state", "NY")
	             .write("postalCode", "10021")
	         .writeEnd()
	         .writeStartArray("phoneNumber")
	             .writeStartObject()
	                 .write("type", "home")
	                 .write("number", "212 555-1234")
	             .writeEnd()
	             .writeStartObject()
	                 .write("type", "fax")
	                 .write("number", "646 555-4567")
	             .writeEnd()
	         .writeEnd()
	     .writeEnd();
        generator.close();
	 
		System.out.println("Done!");
	}
	
	
	public static void main(String[] args) throws Exception {
		new JsonStreamGeneratorTest();
	}
}
