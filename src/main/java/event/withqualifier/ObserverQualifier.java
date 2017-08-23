package event.withqualifier;

import javax.enterprise.event.Observes;

public class ObserverQualifier {
	
//	public void getString(@Observes @Test String s) throws IOException {
//		System.out.println("Got String: "+s);
//	}

	public void getInt1(@Observes @Test Integer s) {
		System.out.println("Got Integer1: "+s);
	}
	
	public void getInt2(@Observes @Test("abc") Integer s) {
		System.out.println("Got Integer2: "+s);
	}
	
	public void getInt3(@Observes @Test2 Integer s){
		System.out.println("Got Integer3: "+s);
	}
}
