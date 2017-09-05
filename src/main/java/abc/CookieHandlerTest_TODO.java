package abc;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

public class CookieHandlerTest_TODO {
	
	public CookieHandlerTest_TODO() {}
	
	public void test() {
		
		CookieHandler.setDefault(new CookieManager(null /*= default in-memory store*/, CookiePolicy.ACCEPT_ALL));
	}
	
	
}
