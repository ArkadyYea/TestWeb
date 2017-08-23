package circuit.breaker.notworking;

import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;


public class TestInterceptor {
	
	int counter = 0;
	boolean successful = false;
	
	@AroundInvoke
	public Object interceptor(InvocationContext ic) throws Exception {
		System.out.println("Target class: "+ic.getTarget());
		System.out.println("Method: "+ic.getMethod());
		System.out.println("TestAnnotation present: "+ic.getMethod().getAnnotation(TestAnnotation.class));
		
		Method methodToInv = ic.getMethod();
		TestAnnotation ann = ic.getMethod().getAnnotation(TestAnnotation.class);
		Object target = ic.getTarget();
		
		if(ann != null) {
			counter = ann.counter();
			System.out.println("counter "+counter);
			
			for (int i = 0; i < counter; i++) {
				if(successful) break;
				try {
					methodToInv.invoke(target, methodToInv.getParameters());
					System.out.println("invoking");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				successful = true;
			}
				
			
			
				
			
			

		}
		
		return ic.proceed();
	}

}
