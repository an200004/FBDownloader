package fb.object;

public class AlbumFB {

	private Photos photos;
	private Photo[] data;
	private PagingFB paging;
	
	public AlbumFB.Photo[] getPhotos() {
		return photos != null && photos.data != null && photos.data.length > 0 ? photos.data : this.data;
	}
	
	public String getNext() {
		if (photos != null && photos.paging.getNext() != null && photos.paging.getNext().trim().length() > 0) {
			return photos.paging.getNext().trim();
		} else if (paging != null && paging.getNext() != null && paging.getNext().trim().length() > 0){
			return paging.getNext().trim();
		}
		
		return null;
	}
	
	public String getPrevious() {
		if (photos != null && photos.paging.getPrevious() != null && photos.paging.getPrevious().trim().length() > 0) {
			return photos.paging.getPrevious().trim();
		} else if (paging != null && paging.getPrevious() != null && paging.getPrevious().trim().length() > 0) {
			return paging.getPrevious();
		}
		
		return null;
	}
	
	
	private class Photos {
		private Photo[] data;
		private PagingFB paging;
	}
	
	public class Photo {
		private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}
}
