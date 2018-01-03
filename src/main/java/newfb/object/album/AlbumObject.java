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
	public String getNextPage() {
		if (super.getNextPage() != null) {
			return super.getNextPage();
		} else {
			return albums == null ? null : albums.getNextPage();
		}
	}
	
	@Override
	public String getPreviousPage() {
		if (super.getPreviousPage() != null) {
			return super.getPreviousPage();
		} else {
			return albums == null ? null : albums.getPreviousPage();
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
