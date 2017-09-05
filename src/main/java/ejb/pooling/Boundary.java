package ejb.pooling;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class Boundary {
	
	@Inject
	PoolingTestSL testSl;
	
	public PoolingTestSL getTestSL() {
		return testSl;
	}
	
	@PostConstruct
	private void justTest() {
		Logger.getLogger(getClass().getName()).info("Boundary's @PostConstruct called");
	}
	
}
