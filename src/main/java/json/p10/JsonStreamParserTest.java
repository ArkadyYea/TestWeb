package json.p10;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

//http://docs.oracle.com/javaee/7/api/javax/json/stream/JsonParser.html
//-Provides forward, read-only access to JSON data in a streaming way. This is the most efficient way for reading JSON data.
//-The class Json contains methods to create parsers from input sources (InputStream and Reader).
public class JsonStreamParserTest {
	
	public JsonStreamParserTest() throws FileNotFoundException {
		
		JsonParser parser = Json.createParser(new FileReader("JsonGenerator.json"));
		
		Event next = parser.next();
		System.out.println(next.name()+"\n");
		
		Event next1 = parser.next();
		System.out.println(next1.name());
		System.out.println(parser.getString()+"\n");
		
		Event next2 = parser.next();
		System.out.println(next2.name());
		System.out.println(parser.getString()+"\n");
		
		
//		For example, for the following JSON:
//
//		 {
//		   "firstName": "John", "lastName": "Smith", "age": 25,
//		   "phoneNumber": [
//		       { "type": "home", "number": "212 555-1234" },
//		       { "type": "fax", "number": "646 555-4567" }
//		    ]
//		 }
//		 
//		calls to the method next() result in parse events at the specified locations below (marked in bold):
//
//		 {START_OBJECT
//		   "firstName"KEY_NAME: "John"VALUE_STRING, "lastName"KEY_NAME: "Smith"VALUE_STRING, "age"KEY_NAME: 25VALUE_NUMBER,
//		   "phoneNumber"KEY_NAME : [START_ARRAY
//		       {START_OBJECT "type"KEY_NAME: "home"VALUE_STRING, "number"KEY_NAME: "212 555-1234"VALUE_STRING }END_OBJECT,
//		       {START_OBJECT "type"KEY_NAME: "fax"VALUE_STRING, "number"KEY_NAME: "646 555-4567"VALUE_STRING }END_OBJECT
//		    ]END_ARRAY
//		 }END_OBJECT
//		 
//		The methods next() and hasNext() enable iteration over parser events to process JSON data. JsonParser provides get methods to obtain the value at the current state of the parser. For example, the following code shows how to obtain the value "John" from the JSON above:
//
//		 
//		 Event event = parser.next(); // START_OBJECT
//		 event = parser.next();       // KEY_NAME
//		 event = parser.next();       // VALUE_STRING
//		 parser.getString();          // "John"
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new JsonStreamParserTest();
	}
}
