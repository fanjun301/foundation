package cn.frank.foundation.jsTest;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JSMain1 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine jsEngine = sem.getEngineByName("JavaScript");
		try {
			//eval JS file
			jsEngine.eval(new InputStreamReader(JSMain1.class.getResourceAsStream("demo.js")));
			
			//Invocable call method directly
			Map<String, String> tmpp = new HashMap<String, String>();
			Object invokefn = ((Invocable)jsEngine).invokeFunction("maptest", tmpp);
			System.out.println(((Map<String, String>)invokefn).get("key"));
			
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} 
		
	}
}
