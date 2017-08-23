package ejb;

import javax.ejb.Stateless;

@Stateless
public class TestForAspects {

	public void letsCrashIt() {
		throw new IllegalStateException("When we throw an exception, we can see a stack trace and what happens behind the scenes.");
	}

}
