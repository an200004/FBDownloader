package newfb.ws;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import newfb.operation.SaltOperation;
import newfb.operation.common.ApplicationInformation;
import newfb.operation.common.OperationStatus;

@Path("wsoperation")
public class WSOperation {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("appRunningStatus")
	public String produceError() {
		return OperationStatus.ERROR.toString();
	}
	
	@GET
	@Path("accessToken")
	public void updateAccessToken(@QueryParam("accessToken") String accessToken) {
		ApplicationInformation.APP_ACCESS_TOKEN = accessToken;
		OperationStatus.ERROR.set(false);
	}
	
	@GET
	@Path("saltDetail")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSaltDetail(@QueryParam("type") String type) {
		return SaltOperation.getFirstSalt(type);
	}
	
}
