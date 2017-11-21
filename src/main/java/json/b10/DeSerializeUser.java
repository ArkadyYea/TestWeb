package json.b10;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import abc.User;

//
public class DeSerializeUser {
	
	public DeSerializeUser() {
		
		User user = new User("John","Smith");
		
		String json = JsonbBuilder.create().toJson(user);
		System.out.println(json);
		
		User fromJson = JsonbBuilder.create().fromJson(json, User.class);
		System.out.println(fromJson);
		
		
		Jsonb jb = JsonbBuilder.create();
		
	}
	
	
	public static void main(String[] args) {
		new DeSerializeUser();
	}
}
