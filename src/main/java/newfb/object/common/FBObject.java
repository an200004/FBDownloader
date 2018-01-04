package newfb.object.common;

import fb.object.PagingFB;

public abstract class FBObject {

	protected Paging paging;
	private Error error;
	
	public abstract DataInterface[] getData();
	
	protected abstract Paging getPaging();
	
	public boolean isError() {
		return error == null ? false : true;
	}
	
	public String getErrorMsg() {
		if (isError()) {
			return error.message;
		}
		
		return null;
	}
	
	public String getNextPage() {
		if (getPaging() != null && getPaging().next != null && getPaging().next.trim().length() > 0) {
			return getPaging().next.trim();
		} 
		
		return null;
	}
	
	public String getPreviousPage() {
		if (getPaging() != null && getPaging().previous != null && getPaging().previous.trim().length() > 0) {
			return getPaging().previous.trim();
		}
		
		return null;
	}
	
	
	protected class Paging {
		private String next;
		private String previous;
		
		public String getNext() {
			return next;
		}
		public void setNext(String next) {
			this.next = next;
		}
		public String getPrevious() {
			return previous;
		}
		public void setPrevious(String previous) {
			this.previous = previous;
		}
	}
	
	private class Error {
		private String message;
	    private String type;
	    private String code;
	    private String error_subcode;
	    private String fbtrace_id;
	}
}
