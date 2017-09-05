package ejb.pooling;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class PoolingTestSL {
	
	private String secret;
	
	public String test() {
		return "Something in the "+secret;
	}
	
	@PostConstruct
	private void justTest() {
		secret = "PostConstruct";
		Logger.getLogger(getClass().getName()).info("TestSL's PostConstruct called");
	}

	public void secretSet() {
		this.secret = "Arek";
		Logger.getLogger(getClass().getName()).info("TestSL's secret set: "+secret);
	}
	
	
	
}
