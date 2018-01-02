package fb.object;

import java.util.ArrayList;
import java.util.List;

public class PageAlbumsFB {

	private PageAlbum albums;
	private AlbumFB[] data;
	private PagingFB paging;
	
	public PageAlbumsFB.AlbumFB[] getAlbums() {
		return albums != null && albums.data != null && albums.data.length > 0 ? albums.data : this.data;
	}
	
	public String getNext() {
		if (albums != null && albums.paging.getNext() != null && albums.paging.getNext().trim().length() > 0) {
			return albums.paging.getNext().trim();
		} else if (paging != null && paging.getNext() != null && paging.getNext().trim().length() > 0){
			return paging.getNext().trim();
		}
		
		return null;
	}
	
	public String getPrevious() {
		if (albums != null && albums.paging.getPrevious() != null && albums.paging.getPrevious().trim().length() > 0) {
			return albums.paging.getPrevious().trim();
		} else if (paging != null && paging.getPrevious() != null && paging.getPrevious().trim().length() > 0) {
			return paging.getPrevious();
		}
		
		return null;
	}
	
	private class PageAlbum {
		private AlbumFB[] data;
		private PagingFB paging;
			
	}
	
	public class AlbumFB {
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
