package newfb.operation;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;

import com.google.gson.Gson;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import fb.db.DBOperation;
import newfb.object.db.Page;
import newfb.object.like.LikeInterface;
import newfb.object.like.LikeObject;
import newfb.operation.common.ApplicationInformation;
import newfb.operation.common.FBOperation;
import newfb.operation.common.OperationStatus;

public class LikeOperation extends FBOperation {

	public void getAllLikedPages() {
		
		OperationStatus.GETALLPAGES_EXECUTING.set(true);
		
		//100128890151874
		List<Page> pages = new ArrayList<Page>();
		
		boolean isNext = true;
		LikeObject likeObject = ApplicationInformation.getResult("me/likes", true, true, LikeObject.class);
		
		while(isNext && likeObject != null && likeObject.getData() != null) {
			
			LikeInterface[] likes = likeObject.getData();
			for (LikeInterface like : likes) {
				System.out.println(like.getName());
				
				Page existPage = DBOperation.getPage(like.getId());
				if (existPage == null) {
					Page page = new Page();
					page.setId(like.getId());
					page.setName(like.getName());
					page.setExclude(true);
					page.setCreateDate(new Date());
					pages.add(page);
				}
			}
			
			if (likeObject.getNextPage() == null || likeObject.getNextPage().trim().length() <= 0) {
				isNext = false;
			} else {
				likeObject = ApplicationInformation.getResult(likeObject.getNextPage().trim(), false, false, LikeObject.class);
			}
		}
		
		if (pages != null && pages.size() > 0) {
			DBOperation.save(pages);
		}
		
		OperationStatus.GETALLPAGES_EXECUTING.set(false);
	}
	
	public static void main(String args[]) {
		LikeOperation likeOperation = new LikeOperation();
		likeOperation.getAllLikedPages();
	}
	
}
