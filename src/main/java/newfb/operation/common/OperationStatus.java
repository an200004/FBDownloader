package newfb.operation.common;

import java.util.concurrent.atomic.AtomicBoolean;

public class OperationStatus {

	public static AtomicBoolean ERROR = new AtomicBoolean(true);
	public static AtomicBoolean GETALLPAGES_EXECUTING = new AtomicBoolean(false);
}
