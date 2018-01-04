package newfb.object.photo;

import fb.object.PhotoFB.Image;
import newfb.object.common.DataInterface;
import newfb.object.common.FBObject;

public class PhotoDetailObject extends FBObject {

	private String id;
	private PhotoDetailInternal[] images;
	
	@Override
	public PhotoDetailInternal[] getData() {
		if (images != null && images.length > 0) {
			return images;
		}
		return null;
	}

	@Override
	protected Paging getPaging() {
		return super.paging;
	}
	
	public String getId() {
		return id;
	}
	
	public String getMaxSizePhotoSource() {
		int maxSize = 0;
		String source = null;
		if (images != null && images.length > 0) {
			for (PhotoDetailInterface photoDetail : images) {
				int currSize = photoDetail.getHeight() * photoDetail.getWidth();
				if (currSize > maxSize) {
					maxSize = currSize;
					source = photoDetail.getSource();
				}
			}
		}
		
		return source;
	}
	
	private class PhotoDetailInternal implements PhotoDetailInterface {
		
		public int height;
		public int width;
		public String source;
		
		public int getHeight() {
			return height;
		}
		
		public void setHeight(int height) {
			this.height = height;
		}
		
		public int getWidth() {
			return width;
		}
		
		public void setWidth(int width) {
			this.width = width;
		}
		
		public String getSource() {
			return source;
		}
		
		public void setSource(String source) {
			this.source = source;
		}	
	}

}
