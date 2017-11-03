package fb.object;

public class PhotoFB {
	
	private Image[] images;
	
	public Image[] getImages() {
		return images;
	}

	public String getMaxSizePhotoSource() {
		int maxSize = 0;
		String source = "";
		if (images.length > 0) {
			for (Image image : images) {
				int currSize = image.getHeight() * image.getWidth();
				if (currSize > maxSize) {
					maxSize = currSize;
					source = image.getSource();
				}
			}
			
		}
		
		return source;
	}
	
	public class Image {
		private int height;
		private int width;
		private String source;
		
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
