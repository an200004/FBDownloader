package newfb.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	
}
