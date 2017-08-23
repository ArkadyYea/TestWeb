package transactions;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.transaction.TransactionSynchronizationRegistry;

@Stateless
public class TransactionBean {
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	@Resource
	EJBContext ctx;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int req() {
		return tsr.getTransactionStatus();
	}
	
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public int never() {
		return tsr.getTransactionStatus();
	}
	
	public int noAnnotation() {
		return tsr.getTransactionStatus();
	}
	
	public int rolledBack() {
		ctx.setRollbackOnly();
		return tsr.getTransactionStatus();
	}
	
	
	public static String getTransactionStatus(int code) {
		
		String status = null;
		
		switch (code) {
			case 0: status = "STATUS_ACTIVE"; break;
			case 1: status = "STATUS_MARKED_ROLLBACK"; break;
			case 2: status = "STATUS_PREPARED"; break;
			case 3: status = "STATUS_COMMITTED"; break;
			case 4: status = "STATUS_ROLLEDBACK"; break;
			case 5: status = "STATUS_UNKNOWN"; break;
			case 6: status = "STATUS_NO_TRANSACTION"; break;
			case 7: status = "STATUS_PREPARING"; break;
			case 8: status = "STATUS_COMMITTING"; break;
			case 9: status = "STATUS_ROLLING_BACK"; break;
			default: status = "UNKNOWN"; break;
		}
		
		return status;
	}
	
	public static String getTransactionStatus2(int code) {
		
		Map<Integer, String> codes = new HashMap<>();
		
		codes.put(0, "STATUS_ACTIVE");
		codes.put(1, "STATUS_MARKED_ROLLBACK");
		codes.put(2, "STATUS_PREPARED");
		codes.put(3, "STATUS_COMMITTED");
		codes.put(4, "STATUS_ROLLEDBACK");
		codes.put(5, "STATUS_UNKNOWN");
		codes.put(6, "STATUS_NO_TRANSACTION");
		codes.put(7, "STATUS_PREPARING");
		codes.put(8, "STATUS_COMMITTING");
		codes.put(9, "STATUS_ROLLING_BACK");
		
		return codes.get(code);
	}
	
}
