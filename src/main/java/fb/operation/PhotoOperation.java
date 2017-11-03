package fb.operation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import javax.imageio.ImageIO;
import javax.json.JsonObject;

import com.google.gson.Gson;

import fb.object.Photo;
import fb.object.PhotoFB;
import fb.queue.QueueManagement;

public class PhotoOperation {

	public Photo getPhotoFromQueue() {
		String photoQueueMsg = QueueManagement.receiveMessage(ApplicationInformation.QUEUE_CONNECTION_STRING, ApplicationInformation.PAGE_ALBUM_PHOTO_QUEUE_NAME);
		
		Photo photo = new Gson().fromJson(photoQueueMsg, Photo.class);
		
		System.out.println("Photo Detail: " + photo.getAlbum().getName() + ": " + photo.getId() );
		
		return photo;
		
	}
	
	public void retrievePhotoDetail () {
		Photo photo = getPhotoFromQueue();
		JsonObject photoDetailObject = ApplicationInformation.getResult(MessageFormat.format("{0}?fields=images", photo.getId()), true, true);
		PhotoFB photoFB = new Gson().fromJson(photoDetailObject.toString(), PhotoFB.class);
		photo.setPhotoSource(photoFB.getMaxSizePhotoSource());
		QueueManagement.sendMessage(new Gson().toJson(photo), ApplicationInformation.QUEUE_CONNECTION_STRING, ApplicationInformation.PAGE_ALBUM_PHOTO_DETAIL_QUEUE_NAME);
		System.out.println(photoFB.getImages().length);
		System.out.println(photoFB.getMaxSizePhotoSource());
		System.out.println(photoDetailObject.toString());
		
	}
	
	public void downloadPhoto() {
		String photoDetailJson = QueueManagement.receiveMessage(ApplicationInformation.QUEUE_CONNECTION_STRING, ApplicationInformation.PAGE_ALBUM_PHOTO_DETAIL_QUEUE_NAME);
		Photo photo = new Gson().fromJson(photoDetailJson, Photo.class);
		
		try {
			System.out.println(photo.getPhotoSource());
			URL imageURL = new URL(photo.getPhotoSource());
			BufferedImage bi = ImageIO.read(imageURL);
			
			
			ImageIO.write(bi, getFileType(photo.getPhotoSource()), new File("c:\\temp\\" + getFilename(photo.getPhotoSource())));
//			FileWriter writer = new FileWriter("c:\\temp\\testing.jsp");
//			writer.
			
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		
		
	}
	
	private String getFilename(String imgSource) {
		return imgSource.substring(0, imgSource.indexOf("?")).substring((imgSource.lastIndexOf("/") + 1));
	}
	
	private String getFileType(String imgSource) {
		String filename = getFilename(imgSource);
		return filename.substring((filename.indexOf(".") + 1));
		
	}
 	
	public static void main(String args[]) {
		PhotoOperation po = new PhotoOperation();
		
		while (true) {
			po.downloadPhoto();;
		}
	}
}
