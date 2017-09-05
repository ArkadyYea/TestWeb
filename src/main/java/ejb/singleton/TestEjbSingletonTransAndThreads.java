package ejb.singleton;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.transaction.TransactionSynchronizationRegistry;

@Singleton
public class TestEjbSingletonTransAndThreads {
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	public void getSomeSleepAndShowThreadName() {
		try {
			System.out.println("\nTransaction key: "+tsr.getTransactionKey());
			System.out.println("Thread name: "+Thread.currentThread().getName());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("");
		}
	}
	
	@Asynchronous
	public void getSomeSleepAndShowThreadNameAsync() {
		try {
			System.out.println("\nTransaction key: "+tsr.getTransactionKey());
			System.out.println("Thread name: "+Thread.currentThread().getName());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("");
		}
	}

}
