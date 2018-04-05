package newfb.test.ws;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fb.db.DBOperation;
import newfb.object.db.ErrorDBObject;

@Path("test")
public class WSTest {

	@GET
	@Path("dberrorsave")
	@Produces(MediaType.TEXT_PLAIN)
	public String testFBErrorDBSave() {
		
		ErrorDBObject dbObject = new ErrorDBObject();
		dbObject.setCode("code");
		dbObject.setErrorDate(new Date());
		dbObject.setErrorSubcode("errorSubcode");
		dbObject.setFbTraceId("fbTraceId");
		dbObject.setMessage("message");
		dbObject.setType("type");
		
		DBOperation.save(dbObject);
		
		return "test complete";
	}
	
}
