package json.b10;

public class TestObjectWithPrivateField {
	
	public String pub = "public";
	private String prv = "private";
	
	public TestObjectWithPrivateField() {}
	
	public TestObjectWithPrivateField(String pub, String prv) {
		super();
		this.pub = pub;
		this.prv = prv;
	}
	
	@Override
	public String toString() {
		return "TestObject [pub=" + pub + ", prv=" + prv + "]";
	}
}
