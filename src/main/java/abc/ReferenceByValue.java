package abc;

public class ReferenceByValue {

	public ReferenceByValue() {
		User u1 = new User("John", "Smith");
		
		System.out.println(u1);
		testRef(u1);
		System.out.println(u1);

		
	}
	//Java always passes arguments by value NOT by reference.
	//Passes a copy of reference, so when we assing a new user it has no impact on the original user.
	public void testRef(User u) {
        u = new User("Anna","Karenina");	//It is a local user, it gets destroyed after function returns
    }

	public static void main(String[] args) {
		new ReferenceByValue();
	}

}
