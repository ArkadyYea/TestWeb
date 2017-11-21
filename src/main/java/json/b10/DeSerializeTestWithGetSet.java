package json.b10;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

//When object has getters and setters, private fileds are de/serialized too. 
public class DeSerializeTestWithGetSet {
	
	public DeSerializeTestWithGetSet() {
		
		TestObjectWithGetSet to = new TestObjectWithGetSet();
		
		String json = JsonbBuilder.create().toJson(to);
		System.out.println(json);
		
		TestObjectWithGetSet fromJson = JsonbBuilder.create().fromJson(json, TestObjectWithGetSet.class);
		System.out.println(fromJson);
		
	}
	
	
	public static void main(String[] args) {
		new DeSerializeTestWithGetSet();
	}
}
