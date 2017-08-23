package configurator;

import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Stateless
public class ShowBean {
	
	@Inject
	ConfigProducer cp;
	
	@Inject
	private Instance<String> myMessage;
		
	public String doStg() {
		return "Configuration: "+myMessage.get();
	}
	
	public String getName() {
		return cp.name;
	}

}
