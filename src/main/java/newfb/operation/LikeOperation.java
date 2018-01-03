package newfb.operation;

import javax.json.JsonObject;

import com.google.gson.Gson;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import newfb.object.like.LikeInterface;
import newfb.object.like.LikeObject;
import newfb.operation.common.ApplicationInformation;

public class LikeOperation {

	public void getAllLikedPages() {
		boolean isNext = true;
		LikeObject likeObject = ApplicationInformation.getResult("me/likes", true, true, LikeObject.class);
		
		while(isNext && likeObject != null && likeObject.getData() != null) {
			
			LikeInterface[] likes = likeObject.getData();
			for (LikeInterface like : likes) {
				System.out.println(like.getName());
			}
			
			if (likeObject.getNextPage() == null || likeObject.getNextPage().trim().length() <= 0) {
				isNext = false;
			} else {
				likeObject = ApplicationInformation.getResult(likeObject.getNextPage().trim(), false, false, LikeObject.class);
			}
		}
	}
	
	public static void main(String args[]) {
		LikeOperation likeOperation = new LikeOperation();
		likeOperation.getAllLikedPages();
	}
	
}
