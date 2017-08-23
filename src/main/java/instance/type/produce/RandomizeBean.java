package instance.type.produce;

import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Stateless
public class RandomizeBean {
	
	@Inject
	@Randomize
	Instance<String> rand; 

	public String testRandomizer() {
		return rand.get();
	}
}
