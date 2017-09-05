package ejb.pooling;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class PoolingTestCDI {
	
	private String secret;
	
	public String test() {
		return "Something in the "+secret;
	}
	
	@PostConstruct
	private void justTest() {
		secret = "PostConstruct";
		//Logger.getLogger(getClass().getName()).info("TestCDI's PostConstruct called");
	}

	public void secretSet() {
		this.secret = "Arek";
		Logger.getLogger(getClass().getName()).info("TestCDI's secret set: "+secret);
	}
	
	
	
}
