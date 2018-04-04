package newfb.operation.common;

import java.io.StringReader;
import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.glassfish.jersey.client.internal.HttpUrlConnector;

import com.google.gson.Gson;

import fb.db.DBOperation;
import javassist.expr.NewArray;
import newfb.object.common.FBObject;
import newfb.object.db.ErrorDBObject;
import newfb.object.like.LikeObject;

public class ApplicationInformation {

	public static String APP_ID = "1531281533583122";
	public static String APP_SECRET = "3703878aca02b5341adf0bb9b0f8caef";
	public static String APP_ACCESS_TOKEN = "EAACEdEose0cBAMFMNOzFfTtGeMk6J9jK3PRF718WVtVykzRVZCZBtTQcP4PhKA4Dxt71pGMdIL3fHnHZBbe7KC9sK1lEZBArrZA3Jy46111QsqcZAzljfh3R9W5ldoPYSUoRrFxUQUPS41pOZCXFZAMcUT6wAi9yWTiGaEjg4LF6tMFLO2rfgp3oXvLSZC4ysk5UvEcoQAicbHQZDZD";
	
	public static String HOST_GRAPH_API = "https://graph.facebook.com";
	public static String VERSION = "v2.10";
	
    public static final String QUEUE_CONNECTION_STRING = "Endpoint=sb://fboperation.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=XpF28NYQGfejWxXCTukq2SGWGdgjitCTmDSzlfXWBD0=";
    public static final String PAGE_ALBUM_QUEUE_NAME = "fb_pages_albums";
    public static final String PAGE_ALBUM_PHOTO_QUEUE_NAME = "fb_pages_albums_photo";
    public static final String PAGE_ALBUM_PHOTO_DETAIL_QUEUE_NAME = "fb_pages_albums_photo_detail";
	
    public static AtomicBoolean ERROR = new AtomicBoolean(true);
    
	public static Client APPLICATION = ClientBuilder.newClient();
	
	public static synchronized <T> T getResult(String target, boolean appendHost, boolean appendAccessToken, Class<?> fbObject) {
		
		T t = null;
		
		String accessToken = (target.contains("?") ? "&" : "?") + "access_token=";
		
		while (true) {
			
			if (ApplicationInformation.ERROR.get() == true) {
				try {
					System.out.println("Get result sleeping...");
					Thread.sleep(60000);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			} else {
			
				System.out.println((appendHost ? HOST_GRAPH_API + "/" + VERSION + "/" : "") 
						 + target + 
						(appendAccessToken ? accessToken + APP_ACCESS_TOKEN : ""));
				
				WebTarget resource = APPLICATION.target((appendHost ? HOST_GRAPH_API + "/" + VERSION + "/" : "")
						+ target + 
						(appendAccessToken ? accessToken + APP_ACCESS_TOKEN : ""));
				
				Builder request = resource.request();
				request.accept(MediaType.APPLICATION_JSON);
				
				Response response = request.get();
				
				t = (T) new Gson().fromJson(response.readEntity(String.class), fbObject);
				
				if (t instanceof FBObject) {
					if (((FBObject)t).isError()) {
						ERROR = new AtomicBoolean(true);
						saveErrorMessage((FBObject)t);
						
					} else {
						break;
					}
				}
			}
		}
		
		return t;
//		return (T) new Gson().fromJson(response.readEntity(String.class), fbObject);
		
//		return getJSONObject(response.readEntity(String.class));
		
//		return null;
	}
	
	private static JsonObject getJSONObject(String jsonInString) {
		JsonReader reader = Json.createReader(new StringReader(jsonInString));
		
		return reader.readObject();
	}
	
	private static void saveErrorMessage(FBObject fbObject) {
		ErrorDBObject error = new ErrorDBObject();
		error.setCode(fbObject.getErrorDetail("CODE"));
		error.setErrorSubcode(fbObject.getErrorDetail("ERROR_SUBCODE"));
		error.setFbTraceId(fbObject.getErrorDetail("FB_TRACE_ID"));
		error.setMessage(fbObject.getErrorDetail("MESSAGE"));
		error.setType(fbObject.getErrorDetail("TYPE"));
		error.setErrorDate(new Date());
		
		DBOperation.save(error);
	}
	
	public static void main(String args[]) {
//		JsonObject object = 
//				getResult("10201750078433170", true, true, String.class); 
//		
//		System.out.println(object.getString("name"));
	}
	
	
}
