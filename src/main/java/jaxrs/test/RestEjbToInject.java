package jaxrs.test;

import javax.ejb.Stateless;

@Stateless
public class RestEjbToInject {
	
	public String inject() {
		return "Injected to test";
	}
	
}