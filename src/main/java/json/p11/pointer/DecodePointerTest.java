package json.p11.pointer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonPointer;

//A JSON Pointer is a sequence of zero or more reference tokens, each prefixed by a '/' (%x2F) character.

//https://tools.ietf.org/html/rfc6901
//-Because the characters '~' (%x7E) and '/' (%x2F) have special meanings in JSON Pointer,
// '~' needs to be encoded as '~0' and '/' needs to be encoded as '~1' when these characters appear in a reference token.
@SuppressWarnings("unused")
public class DecodePointerTest {
	
	public DecodePointerTest() {
		
		String encodedPointer = Json.encodePointer("aa/bb~cc");
		System.out.println(encodedPointer);
		
		String decodedPointer = Json.decodePointer(encodedPointer);
		System.out.println(decodedPointer);
		
		
	}
	
	
	public static void main(String[] args) {
		new DecodePointerTest();
	}
}
