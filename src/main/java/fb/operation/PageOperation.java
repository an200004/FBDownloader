package fb.operation;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import fb.db.DBOperation;
import fb.object.Album;
import fb.object.Page_Obsolete;
import fb.object.PageAlbumsFB;
import fb.object.PageFB;
import fb.queue.QueueManagement;

@Path("page")
public class PageOperation {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("allpages")
	public String allPages() {
		
		List<Page_Obsolete> pages = new ArrayList<Page_Obsolete>();
		
		JsonObject pagesObject = ApplicationInformation.getResult("me/likes", true, true);
		PageFB pageFb = new Gson().fromJson(pagesObject.toString(), PageFB.class);
		
		while (pageFb != null && pageFb.getData().length > 0) {
			
			for (PageFB.PageFBData pageFbData : pageFb.getData()) {
				if (DBOperation.getPage(pageFbData.getId()) == null) {
					Page_Obsolete Page_Obsolete = new Page_Obsolete();
					Page_Obsolete.setId(pageFbData.getId());
					Page_Obsolete.setName(pageFbData.getName());
					Page_Obsolete.setExclude(true);
					pages.add(Page_Obsolete);
					System.out.println(pageFbData.getName());
				}
			}
			
			if (pageFb.getNext() != null && pageFb.getNext().trim().length() > 0) {
				pagesObject = ApplicationInformation.getResult(pageFb.getNext().trim(), false, false);
				pageFb = new Gson().fromJson(pagesObject.toString(), PageFB.class);
			} else {
				pageFb = null;
			}
			
		}
		
		if (pages != null && pages.size() > 0) {
			DBOperation.save(pages);
		}
		
		System.out.println(pages.size());
		return "";
	}
	
	public String getPageAlbums(Page_Obsolete Page_Obsolete) {
		
			JsonObject albumObject = ApplicationInformation.getResult(MessageFormat.format("{0}?fields=albums", Page_Obsolete.getId().trim()), true, true);
			
			PageAlbumsFB pageAlbumsFb =  new Gson().fromJson(albumObject.toString(), PageAlbumsFB.class);
			
			while (pageAlbumsFb != null && pageAlbumsFb.getAlbums().length > 0) {
				for (PageAlbumsFB.AlbumFB albumFB : pageAlbumsFb.getAlbums()) {
					Album albumObj = new Album();
					albumObj.setId(albumFB.getId());
					albumObj.setName(albumFB.getName());
					albumObj.setPage_name(Page_Obsolete.getName());
					QueueManagement.sendMessage(new Gson().toJson(albumObj), ApplicationInformation.QUEUE_CONNECTION_STRING, ApplicationInformation.PAGE_ALBUM_QUEUE_NAME);
					System.out.println(albumObj.getName());
					
				}
				
				if (pageAlbumsFb.getNext() != null && pageAlbumsFb.getNext().trim().length() > 0) {
					albumObject = ApplicationInformation.getResult(pageAlbumsFb.getNext().trim(), false, false);
					pageAlbumsFb = new Gson().fromJson(albumObject.toString(), PageAlbumsFB.class);
				} else {
					pageAlbumsFb = null;
				}
			}
		
					
		return "";
	}
	
	public List<Page_Obsolete> findRequiredPages() {
//		List<Page_Obsolete> pages = DBOperation.getExcludePages();
		
		return null; //pages;
	}
	
	public static void main(String args[]) {
		PageOperation po = new PageOperation();
//		po.allPages();
//		List<Page> pages = po.findRequiredPages();
//		pages.forEach(p -> {
//			System.out.println(p.getName());
//		});
//		po.getPageAlbums();
	}
}
