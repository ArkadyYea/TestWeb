package event.conditional;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;


@RequestScoped
public class ObserverConditional implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public void getStringFail(@Observes(notifyObserver=Reception.ALWAYS) String s) throws IOException {
		System.out.println("Got String (notifyObserver=Reception.ALWAYS): "+s);
	}
	
	public void getString(@Observes(notifyObserver=Reception.IF_EXISTS) String s) throws IOException {
		System.out.println("Got String (notifyObserver=Reception.IF_EXISTS): "+s);
	}
	
	
}
