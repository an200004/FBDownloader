package newfb.operation;

import newfb.object.album.AlbumInterface;
import newfb.object.album.AlbumObject;
import newfb.object.like.LikeObject;
import newfb.operation.common.ApplicationInformation;

public class AlbumOperation {

	public void getPageAlbum(String pageId) {
		boolean isNext = true;
		
		AlbumObject albumObject = ApplicationInformation.getResult(pageId + "?fields=albums", true, true, AlbumObject.class);
		
		while(isNext && albumObject != null && albumObject.getData() != null) {
			
			for (AlbumInterface album : albumObject.getData()) {
				System.out.println(album.getName());
			}
			
			if (albumObject.getNextPage() == null || albumObject.getNextPage().trim().length() <= 0) {
				isNext = false;
			} else {
				albumObject = ApplicationInformation.getResult(albumObject.getNextPage().trim(), false, false, AlbumObject.class);
			}
		}
	}
	
	public static void main(String args[]) {
		AlbumOperation albumOperation = new AlbumOperation();
		albumOperation.getPageAlbum("249264721791540");
	}
	
}
