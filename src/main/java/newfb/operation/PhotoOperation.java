package newfb.operation;

import newfb.object.album.AlbumObject;
import newfb.object.photo.PhotoDetailInterface;
import newfb.object.photo.PhotoDetailObject;
import newfb.object.photo.PhotoInterface;
import newfb.object.photo.PhotoObject;
import newfb.operation.common.ApplicationInformation;
import newfb.operation.common.FBOperation;

public class PhotoOperation extends FBOperation {

	public void getAlbumPhotos(String albumId) {
		sleepWithError();
		
		boolean isNext = true;
		PhotoObject photoObject = ApplicationInformation.getResult(albumId + "?fields=photos", true, true, PhotoObject.class);
		
		while (isNext && photoObject != null) {
			for (PhotoInterface photo : photoObject.getData()) {
				System.out.println(photo.getId());
			}
			
			if (photoObject.getNextPage() == null || photoObject.getNextPage().trim().length() < 0) {
				isNext = false;
			} else {
				photoObject = ApplicationInformation.getResult(photoObject.getNextPage().trim(), false, false, PhotoObject.class);
			}
		}
		
	}
	
	public void getPhotoDetail(String photoId) {
		sleepWithError();
		
		PhotoDetailObject photoDetailObject = ApplicationInformation.getResult(photoId + "?fields=images", true, true, PhotoDetailObject.class);
		
		if (photoDetailObject != null) {
			System.out.println(photoDetailObject.getId());
			
			for (PhotoDetailInterface photoDetail : photoDetailObject.getData()) {
				System.out.println(photoDetail.getSource());
			}
			System.out.println(photoDetailObject.getMaxSizePhotoSource());
			
		}
	}
	
	public static void main(String args[]) {
		PhotoOperation photoOperation = new PhotoOperation();
//		photoOperation.getAlbumPhotos("256499531068059");
		photoOperation.getPhotoDetail("654746797909995");
	}
	
}
