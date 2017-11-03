package fb.operation;

import java.io.StringReader;
import java.text.MessageFormat;

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

public class ApplicationInformation {

	public static String APP_ID = "1531281533583122";
	public static String APP_SECRET = "3703878aca02b5341adf0bb9b0f8caef";
	public static String APP_ACCESS_TOKEN = "EAACEdEose0cBAKD14xd1wAYLJf2vLE0xDmGc7soQtxr0TctFN6rs9CSKEPhWVAcU9N7PZARn6cS4Y2FPRZAMMgCMHZCwHZAJ3XBlStFvFbP3AKY5QxPUwTUM9TaOr2vSEQqqTZAsrWa4aoLaItbcg4hVih07ZCr9mkIslCZB9M1hCeZCd4CZCVQLNc0GMndu0vA4ZD";
	
	public static String HOST_GRAPH_API = "https://graph.facebook.com";
	public static String VERSION = "v2.10";
	
    public static final String QUEUE_CONNECTION_STRING = "Endpoint=sb://fboperation.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=XpF28NYQGfejWxXCTukq2SGWGdgjitCTmDSzlfXWBD0=";
    public static final String PAGE_ALBUM_QUEUE_NAME = "fb_pages_albums";
    public static final String PAGE_ALBUM_PHOTO_QUEUE_NAME = "fb_pages_albums_photo";
    public static final String PAGE_ALBUM_PHOTO_DETAIL_QUEUE_NAME = "fb_pages_albums_photo_detail";
	
	public static Client APPLICATION = ClientBuilder.newClient();
	
//	static {
//		JsonObject object = 
//				getResult(MessageFormat.format("oauth/access_token?client_id={0}&client_secret={1}&grant_type=client_credentials", new String[]{APP_ID, APP_SECRET}), false); 
//		
//		APP_ACCESS_TOKEN = object.getString("access_token").replace((APP_ID + "|"), "");
//	}
	
	public static JsonObject getResult(String target, boolean appendHost, boolean appendAccessToken) {
		
		String accessToken = (target.contains("?") ? "&" : "?") + "access_token=";
		
		System.out.println((appendHost ? HOST_GRAPH_API + "/" + VERSION + "/" : "") 
				 + target + 
				(appendAccessToken ? accessToken + APP_ACCESS_TOKEN : ""));
		
		WebTarget resource = APPLICATION.target((appendHost ? HOST_GRAPH_API + "/" + VERSION + "/" : "")
				+ target + 
				(appendAccessToken ? accessToken + APP_ACCESS_TOKEN : ""));
		
		Builder request = resource.request();
		request.accept(MediaType.APPLICATION_JSON);
		
		Response response = request.get();
		
		return getJSONObject(response.readEntity(String.class));
		
//		return null;
	}
	
	private static JsonObject getJSONObject(String jsonInString) {
		JsonReader reader = Json.createReader(new StringReader(jsonInString));
		
		return reader.readObject();
	}
	
	public static void main(String args[]) {
		JsonObject object = 
				getResult("10201750078433170", true, true); 
		
		System.out.println(object.getString("name"));
	}
	
	
}
