package ejb;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;

@Stateless
public class TestEjbTransactionsAndThreads {
	
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
