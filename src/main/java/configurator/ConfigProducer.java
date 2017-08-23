package configurator;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class ConfigProducer {
	
	String name = "Arek";
	
	@Produces
	public String whatever(InjectionPoint ip) {
		String fullyQualifiedClassName = ip.getMember().getDeclaringClass().getName();
		String fieldName = ip.getMember().getName();
		
		return "<br/>ip.getMember().getDeclaringClass().getName(): "+fullyQualifiedClassName 
				+ ",<br/>ip.getMember().getName(): " + fieldName +"<br/>";
	}
	
}
