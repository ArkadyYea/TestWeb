package injection.point;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;


@SessionScoped
@Named
public class TestCDI implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@MyQualifier
	String thisIsName;
	
	@Inject
	@MyQualifier
	String thisIsJustAnotherName;
	
	public String test() {
		System.out.println(thisIsName);
		return thisIsName;
	}
	

}
