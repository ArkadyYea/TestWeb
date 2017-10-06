package ejb.schedule;

import javax.ejb.Schedule;
import javax.ejb.Singleton;


//@Singleton
@SuppressWarnings("unused")
public class ScheduleAndGlassfishProblem {
	
	//https://stackoverflow.com/questions/13092567/automatic-ejb-timer-on-glassfish-server-not-triggering/13102822#13102822
	//@Schedule(second="*/2", minute="*", hour="*")		//DOES NOT WORK!?!?!
	@Schedule(second="*/2", minute="*", hour="*", persistent=false)
	public void publish() {
		System.out.println(" * Time: " + System.currentTimeMillis());
	}
	
}
