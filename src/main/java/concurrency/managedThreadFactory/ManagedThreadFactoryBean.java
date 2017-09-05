package concurrency.managedThreadFactory;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;


@Dependent
public class ManagedThreadFactoryBean {
    
	public ManagedThreadFactoryBean() {}
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	public void name() {
		System.out.println("ThreadFactoryBean without Transaction, thread: "+Thread.currentThread().getName()+", transaction: "+tsr.getTransactionKey());
	}
	
	@Transactional(value=Transactional.TxType.REQUIRES_NEW)
	public void name2() {
		System.out.println("ThreadFactoryBean with Transaction, thread: "+Thread.currentThread().getName()+", transaction: "+tsr.getTransactionKey());
	}
	
	
}
