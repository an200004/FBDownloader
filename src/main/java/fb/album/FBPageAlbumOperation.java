package fb.album;

import com.google.gson.Gson;

import fb.object.Album;
import fb.operation.ApplicationInformation;
import fb.queue.QueueManagement;

public class FBPageAlbumOperation {

	public void getFBPageAlbum() {
		
		AlbumOperation albumOperation = new AlbumOperation();
		
		while (true) {
			String queueMsg = QueueManagement.receiveMessage(ApplicationInformation.QUEUE_CONNECTION_STRING, ApplicationInformation.PAGE_ALBUM_QUEUE_NAME);
			
			if (queueMsg != null) {
				Album album = new Gson().fromJson(queueMsg, Album.class);
				System.out.println("MSG: " + album.getId() + "; " + album.getName());
				albumOperation.getAlbumPhotos(album);
				
			} else {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException ie) { ie.printStackTrace();}
			}
		}
		
	}
	
	public static void main(String args[]) {
		FBPageAlbumOperation ao = new FBPageAlbumOperation();
		ao.getFBPageAlbum();
	}
	
}
