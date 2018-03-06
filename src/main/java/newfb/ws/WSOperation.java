package newfb.ws;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import newfb.operation.common.ApplicationInformation;

@Path("wsoperation")
public class WSOperation {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("appRunningStatus")
	public String produceError() {
		return ApplicationInformation.ERROR.toString();
	}
	
	@GET
	@Path("accessToken")
	public void updateAccessToken(@QueryParam("accessToken") String accessToken) {
		ApplicationInformation.APP_ACCESS_TOKEN = accessToken;
		ApplicationInformation.ERROR = new AtomicBoolean(false);
	}
	
}
