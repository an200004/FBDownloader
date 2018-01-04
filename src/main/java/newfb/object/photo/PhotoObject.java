package newfb.object.photo;

import newfb.object.common.DataInterface;
import newfb.object.common.FBObject;

public class PhotoObject extends FBObject {

	private PhotoObjectInternal2 photos;
	private PhotoObjectInternal[] data;
	
	@Override
	public PhotoObjectInternal[] getData() {
		if (photos != null && photos.getData() != null && photos.getData().length > 0) {
			return photos.getData();
		} else if (data != null && data.length > 0){
			return data;
		}
		return null;
	}
	
	@Override
	protected Paging getPaging() {
		if (photos != null && photos.getPaging() != null) {
			return photos.getPaging();
		} else {
			return super.paging;
		}
		
	}

	
	private class PhotoObjectInternal2 extends FBObject {

		private PhotoObjectInternal[] data;
		
		@Override
		public PhotoObjectInternal[] getData() {
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
	
	private class PhotoObjectInternal implements PhotoInterface {
		
		private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
		
		
	}


}
