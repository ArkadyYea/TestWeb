package instance.type.produce;

import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;

@Singleton
@Startup
public class ProduceRandom {

	@Produces
	@Randomize
	public String randomize() {
		return "Randomized - "+(ThreadLocalRandom.current().nextInt(100)+1);
	}
}
