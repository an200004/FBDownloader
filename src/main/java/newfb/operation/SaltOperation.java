package newfb.operation;

import fb.db.DBOperation;
import newfb.operation.common.FBOperation;

public class SaltOperation extends FBOperation {
	
	public static String getFirstSalt(String type) {
		return DBOperation.getSalt(type);
	}

}
