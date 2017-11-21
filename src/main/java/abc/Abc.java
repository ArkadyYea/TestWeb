package abc;

import java.util.Map;


public class Abc {

	public Abc() {
		Map<String, String> envs = System.getenv();
		System.out.println(System.getenv("abc"));
		System.out.println(envs.keySet());
		
	}

	public static void main(String[] args) {
		new Abc();

	}

}
