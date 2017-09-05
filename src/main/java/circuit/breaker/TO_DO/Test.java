package circuit.breaker.TO_DO;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Interceptors(TestInterceptor.class)
@Stateless
public class Test {

	@TestAnnotation(counter=3)
	public String doItNow() {
		if(true) throw new IllegalStateException("Just to test");
		return "Method doItNow() called!";
	}

}
