package logger.produce;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LoggerEJBean {
	
	@Inject  
	private Logger logger;
	
	@Inject  
	private Consumer<String> logger2;
	
	@Inject  
	private Log logger3;
	
	public String testLogger() {
		if(logger != null) {
			logger.info("Logger is working");
			return "Logger is working!";
		} else {
			System.out.println("Logger is null!");
			return "Logger is NOT working!";
		}
	}
    
	public String testLoggerConsumer() {
		if(logger2 != null) {
			logger2.accept("Consumer Logger is working");
			return "Consumer Logger is working!";
		} else {
			System.out.println("Consumer Logger is null!");
			return "Consumer Logger is NOT working!";
		}
	}
    
	public String testLoggerInterface() {
		if(logger3 != null) {
			logger3.log("Interface Logger is working");
			return "Interface Logger is working!";
		} else {
			System.out.println("Interface Logger is null!");
			return "Interface Logger is NOT working!";
		}
	}
    
}
