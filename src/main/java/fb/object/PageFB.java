package fb.object;

public class PageFB {

	private PageFBData[] data;
	private PagingFB paging;
	
	public PageFBData[] getData() {
		return data;
	}
	
	public String getNext() {
		if (paging != null && paging.getNext() != null && paging.getNext().trim().length() > 0) {
			return paging.getNext();
		}
		
		return null;
	}
	
	public String getPrevious() {
		if (paging != null && paging.getPrevious() != null && paging.getPrevious().trim().length() > 0) {
			return paging.getPrevious();
		}
		
		return null;
	}
	
	public class PageFBData {
	
		private String name;
	    private String id;
	    
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	    
	    
	}
}
