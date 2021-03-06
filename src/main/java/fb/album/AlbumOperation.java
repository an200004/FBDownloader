package fb.album;

import java.text.MessageFormat;

import javax.json.JsonArray;
import javax.json.JsonObject;

import com.google.gson.Gson;

import fb.object.Album;
import fb.object.PageAlbumsFB.AlbumFB;
import fb.object.Photo;
import fb.operation.ApplicationInformation;
import fb.queue.QueueManagement;

public class AlbumOperation {

	int counter = 1;
	public void getAlbumPhotos(Album album) {
		
		boolean hasNext = true;
		counter = 1;
		JsonObject photosObject = ApplicationInformation.getResult(MessageFormat.format("{0}?fields=photos", album.getId().trim()), true, true);
		
		fb.object.AlbumFB albumFb = new Gson().fromJson(photosObject.toString(), fb.object.AlbumFB.class);
		
		while (albumFb != null && albumFb.getPhotos() != null && albumFb.getPhotos().length > 0) {
			for (fb.object.AlbumFB.Photo photoFb : albumFb.getPhotos()) {
				Photo photo = new Photo();
				photo.setAlbum(album);
				photo.setId(photoFb.getId());
				QueueManagement.sendMessage(new Gson().toJson(photo), ApplicationInformation.QUEUE_CONNECTION_STRING, ApplicationInformation.PAGE_ALBUM_PHOTO_QUEUE_NAME);
				System.out.println(photo.getId());
			}
			
			if (albumFb.getNext() != null && albumFb.getNext().trim().length() > 0) {
				photosObject = ApplicationInformation.getResult(albumFb.getNext(), false, false);
			} else {
				albumFb = null;
			}
		}
		
		/*while (hasNext) {
			if (photosObject.containsKey("photos") || photosObject.containsKey("data")) {
				JsonObject photoDataObject = photosObject.getJsonObject("photos");
				
				if (photoDataObject == null) {
					photoDataObject = photosObject;
				}
				
				if (photoDataObject.containsKey("data")) {
					JsonArray photosDetail = photoDataObject.getJsonArray("data");
					
					photosDetail.forEach(photoDetail -> {
						System.out.println(counter + "--> " + album.getName() + ": " + photoDetail.asJsonObject().getString("id"));
						Photo photo = new Photo();
						photo.setAlbum(album);
						photo.setId(photoDetail.asJsonObject().getString("id"));
						QueueManagement.sendMessage(new Gson().toJson(photo), ApplicationInformation.QUEUE_CONNECTION_STRING, ApplicationInformation.PAGE_ALBUM_PHOTO_QUEUE_NAME);
						counter = counter + 1;
					});
				} else {
					hasNext = false;
				}
				
				if (photoDataObject.containsKey("paging") && 
						photoDataObject.getJsonObject("paging").containsKey("next")) {
					photosObject = ApplicationInformation.getResult(photoDataObject.getJsonObject("paging").getString("next").trim(), false, false);
				} else {
					hasNext = false;
				}
				
			} else {
				hasNext = false;
			}
		}*/
		
		
//		507443009420458?fields=photos
		
	}
	
}
