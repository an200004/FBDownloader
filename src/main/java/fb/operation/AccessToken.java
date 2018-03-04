package fb.operation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("accesstoken")
public class AccessToken {

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public void test(String accessToken) {
		ApplicationInformation.APP_ACCESS_TOKEN = accessToken;
		
		System.out.println(ApplicationInformation.APP_ACCESS_TOKEN);
	}
}
