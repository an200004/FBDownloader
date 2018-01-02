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
		JsonObject pagesObject = ApplicationInformation.getResult("me/likes", true, true);
		LikeObject likeObject = new Gson().fromJson(pagesObject.toString(), LikeObject.class);

		LikeInterface[] likes = likeObject.getData();
		for (LikeInterface like : likes) {
			System.out.println(like.getName());
		}
		
//		System.out.println(pagesObject.toString());
//		System.out.println(likeObject.isError());
//		System.out.println(likeObject.getErrorMsg());
//		System.out.println(likeObject.getData());
//		System.out.println(likeObject.getNextPage());
		
		
	}
	
	public static void main(String args[]) {
		LikeOperation likeOperation = new LikeOperation();
		likeOperation.getAllLikedPages();
	}
	
}
