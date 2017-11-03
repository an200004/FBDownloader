package fb.operation;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("fb")
public class Testing {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		ApplicationInformation ai = new ApplicationInformation();
		JsonObject object = ai.getResult("10207328917500660", true, true);
		
		System.out.println(object.getString("name"));
		System.out.println(object.getString("id"));
		return "Success";
	}
	
}
