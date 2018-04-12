package newfb.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import newfb.operation.LikeOperation;
import newfb.operation.common.OperationStatus;

@Path("likeoperation")
public class LikeOperationWS {

	
	@GET
	@Path("allLikedPages")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllLikedPages() {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				LikeOperation likeOperation = new LikeOperation();
				likeOperation.getAllLikedPages();
				
			}
		});
		
		thread.start();
		
//		System.out.println("Testing...");
		return "Get all pages executing";
		
		
	}
	
	@GET
	@Path("allPagesExecutionStatus")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllPagesExecutionStatus() {
		return OperationStatus.GETALLPAGES_EXECUTING.toString();
	}
}
