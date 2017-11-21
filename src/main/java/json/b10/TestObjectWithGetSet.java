package json.b10;

public class TestObjectWithGetSet {
	
	public String pub = "public";
	private String prv = "private";
	
	
	public TestObjectWithGetSet() {}
	
	public String getPub() {
		return pub;
	}


	public void setPub(String pub) {
		this.pub = pub;
	}


	public String getPrv() {
		return prv;
	}


	public void setPrv(String prv) {
		this.prv = prv;
	}
	
	@Override
	public String toString() {
		return "TestObject [pub=" + pub + ", prv=" + prv + "]";
	}
	
}
