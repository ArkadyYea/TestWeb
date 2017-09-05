package ejb;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;


//@ConcurrencyManagement - If this annotation is not specified, the singleton bean is assumed to have container managed concurrency.
//http://www.byteslounge.com/tutorials/java-ee-ejb-concurrency-concurrencymanagement-lock-and-locktype
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class BeanConcurrencyManagement_TO_DO {

	public void letsCrashIt() {
		// to do!
	}

}
