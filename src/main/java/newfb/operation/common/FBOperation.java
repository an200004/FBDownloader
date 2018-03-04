package newfb.operation.common;

public class FBOperation {

	public boolean hasError() {
		return ApplicationInformation.ERROR.get();
	}
	
	protected void sleepWithError() {
		while (hasError()) {
			
			try {
				Thread.currentThread().sleep(60000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
