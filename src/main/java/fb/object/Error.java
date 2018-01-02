package fb.object;

public class Error {

	private ErrorInternal error;
	private String completeError;
	
	public String getErrorMessage() {
		return error.message;
	}
	
	public String getErrorType() {
		return error.type;
	}
	
	public String getErrorCode() {
		return error.code;
	}
	
	public String getErrorSubCode() {
		return error.sub_code;
	}
	
	public String getCompleteError() {
		return completeError;
	}
	
	private class ErrorInternal {
		private String message;
	    private String type;
	    private String code;
	    private String sub_code;
	    private String fbtrace_id;
	}
}
