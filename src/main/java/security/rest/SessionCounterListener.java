package security.rest;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionCounterListener implements HttpSessionListener {

  private static int activeSessions;

  public static int getTotalActiveSession(){
	return activeSessions;
  }

  @Override
  public void sessionCreated(HttpSessionEvent arg0) {
	activeSessions++;
	System.out.println(">>> HttpSessionListener, sessionCreated, total sessions: "+activeSessions);
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent arg0) {
	activeSessions--;
	System.out.println(">>> HttpSessionListener, sessionDestroyed, total sessions: "+activeSessions);
  }
}