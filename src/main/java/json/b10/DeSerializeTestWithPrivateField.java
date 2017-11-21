package json.b10;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

//
public class DeSerializeTestWithPrivateField {
	
	public DeSerializeTestWithPrivateField() {
		
		TestObjectWithPrivateField to = new TestObjectWithPrivateField("abc","def");
		
		String json = JsonbBuilder.create().toJson(to);
		System.out.println(json);
		
		TestObjectWithPrivateField fromJson = JsonbBuilder.create().fromJson(json, TestObjectWithPrivateField.class);
		System.out.println(fromJson);
		
		//Jsonb create = JsonbBuilder.create(new JsonbConfig().withPropertyVisibilityStrategy(propertyVisibilityStrategy));
	}
	
	
	public static void main(String[] args) {
		new DeSerializeTestWithPrivateField();
	}
}
