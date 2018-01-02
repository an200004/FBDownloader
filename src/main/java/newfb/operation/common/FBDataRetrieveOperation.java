package newfb.operation.common;

import java.io.FileReader;
import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class FBDataRetrieveOperation {

	public static void main(String args[]) throws Exception {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval(new InputStreamReader(FBDataRetrieveOperation.class.getClassLoader().getResourceAsStream("META-INF/fb.js")));
		System.out.println(FBDataRetrieveOperation.class.getClassLoader().getResourceAsStream("META-INF/fb.js"));
		new InputStreamReader(FBDataRetrieveOperation.class.getClassLoader().getResourceAsStream("META-INF/fb.js"));
		
//		new InputStreamReader(FBDataRetrieveOperation.class.getClassLoader().getResourceAsStream("/META-INF/fb.js"));
	}
	
}
