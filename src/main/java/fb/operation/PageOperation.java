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
import fb.object.Page;
import fb.queue.QueueManagement;

@Path("page")
public class PageOperation {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("allpages")
	public String allPages() {
		
		boolean hasNext = true;
		List<Page> pages = new ArrayList<Page>();
		
		JsonObject pagesObject = ApplicationInformation.getResult("me/likes", true, true);
	
		while (hasNext) {
			
			pagesObject.getJsonArray("data").forEach(k -> {
				
				if (DBOperation.getPage(((JsonObject)k).getString("id")) == null) {
					Page page = new Page();
					page.setId(((JsonObject)k).getString("id"));
					page.setName(((JsonObject)k).getString("name"));
					page.setExclude(true);
					
					pages.add(page);
				}
			});
			
			if (pagesObject.getJsonObject("paging").containsKey("next")) {
				pagesObject = ApplicationInformation.getResult(pagesObject.getJsonObject("paging").getString("next").trim(), false, false);
			} else {
				hasNext = false;
			}
			
		}
		
		if (pages != null && pages.size() > 0) {
			DBOperation.save(pages);
		}
		
		System.out.println(pages.size());
		return "";
	}
	
	public String getPageAlbums() {
		
		findRequiredPages().forEach(page -> {
			JsonObject albumObject = ApplicationInformation.getResult(MessageFormat.format("{0}?fields=albums", page.getId().trim()), true, true);
			
			boolean hasNext = true;
			
			while (hasNext) {
				if (albumObject.containsKey("albums") || albumObject.containsKey("data")) {
	//				Object obj = albumObject.getValue("data");
					JsonObject dataAlbumObject = albumObject.getJsonObject("albums");
					
					if (dataAlbumObject == null) {
						dataAlbumObject = albumObject;
					}
					
					if (dataAlbumObject.containsKey("data")) {
						JsonArray dataAlbum = dataAlbumObject.getJsonArray("data");
						
						dataAlbum.forEach(album -> {
							Album albumObj = new Album();
							albumObj.setId(album.asJsonObject().getString("id"));
							albumObj.setName(album.asJsonObject().getString("name"));
							albumObj.setPage_name(page.getName());
							
							QueueManagement.sendMessage(new Gson().toJson(albumObj), ApplicationInformation.QUEUE_CONNECTION_STRING, ApplicationInformation.PAGE_ALBUM_QUEUE_NAME);
							System.out.println(album.asJsonObject().getString("name"));
						});
						System.out.println("");
						
						if (dataAlbumObject.containsKey("paging")) {
							JsonObject pagingAlbum = dataAlbumObject.getJsonObject("paging");
							if (pagingAlbum.containsKey("next") && pagingAlbum.getString("next") != null && pagingAlbum.getString("next").trim().length() > 0) {
								albumObject = ApplicationInformation.getResult(pagingAlbum.getString("next").trim(), false, false);
							} else {
								hasNext = false;
							}
						} else {
							hasNext = false;
						}
					} else {
						hasNext = false;
					}
					
				} else {
					hasNext = false;
				}
				
			}
			
			
		});
			
		return "";
	}
	
	private List<Page> findRequiredPages() {
		List<Page> pages = DBOperation.getExcludePages();
		
//		pages.forEach(page -> {
//			System.out.println(page.getId());
//		});
		
		return pages;
	}
	
	public static void main(String args[]) {
		PageOperation po = new PageOperation();
//		po.allPages();
//		po.findRequiredPages();
		po.getPageAlbums();
	}
}
