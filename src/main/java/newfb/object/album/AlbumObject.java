package newfb.object.album;

import newfb.object.common.DataInterface;
import newfb.object.common.FBObject;

public class AlbumObject extends FBObject {

	private AlbumObjectInternal2 albums;
	private AlbumObjectInternal[] data;
	
	@Override
	public AlbumObjectInternal[] getData() {
		if (albums != null && albums.getData() != null && albums.getData().length > 0) {
			return albums.getData();
		} else if (data != null && data.length > 0) {
			return data;
		}
		return null;
	}
	
	@Override
	protected Paging getPaging() {
		if (albums != null && albums.getPaging() != null) {
			return albums.getPaging();
		} else {
			return super.paging;
		}
		
	}
	
	private class AlbumObjectInternal2 extends FBObject {
		private AlbumObjectInternal[] data;

		@Override
		public AlbumObjectInternal[] getData() {
			if (data != null && data.length > 0) {
				return data;
			}
			return null;
		}

		@Override
		protected Paging getPaging() {
			return super.paging;
		}

	}
	
	
	private class AlbumObjectInternal implements AlbumInterface {
		
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
