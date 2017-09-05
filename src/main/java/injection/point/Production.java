package injection.point;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class Production {

//	@Produces
//	@MyQualifier
//	public User abc(InjectionPoint ip) {
//		String s = ip.getMember().getName();
//		User u = new User("ip name -> ", s);
//		System.out.println(u);
//		return u;
//	}
	
	@Produces
	@MyQualifier
	public String abc(InjectionPoint ip) {
		ip.getType();
		ip.getQualifiers();
		ip.getAnnotated();
		ip.getBean();
		ip.getMember().getName();
		ip.getMember().getClass();
		ip.getMember().getDeclaringClass();
		ip.getMember().getModifiers();
		ip.getMember().isSynthetic();
		ip.isTransient();
		ip.isDelegate();
		String s = "ip.getMember().getName() -> "+ip.getMember().getName()+", ip.isTransient() -> "+ip.isTransient()+", ip.isDelegate() -> "+ip.isDelegate()
				+", ip.getType() -> "+ip.getType()+", ip.getQualifiers() -> "+ip.getQualifiers()+", ip.getAnnotated() -> "+ip.getAnnotated()
				+", ip.getBean() -> "+ip.getBean()+", ip.getMember().getClass() -> "+ip.getMember().getClass()+", ip.getMember().getDeclaringClass() -> "
				+ip.getMember().getDeclaringClass()+", ip.getMember().getModifiers() -> "+ip.getMember().getModifiers()
				+", ip.getMember().isSynthetic() -> "+ip.getMember().isSynthetic();
		System.out.println(s);
		return s;
	}
	
}
