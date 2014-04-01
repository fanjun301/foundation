package cn.frank.foundation.jsTest;

import java.io.InputStreamReader;
import java.util.List;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JSMain {

	public static void main(String[] args) {
		
		ScriptEngineManager sem = new ScriptEngineManager();
		
		List<ScriptEngineFactory> engineFactories = sem.getEngineFactories();
		for (ScriptEngineFactory scriptEngineFactory : engineFactories) {
			System.out.println(scriptEngineFactory.getNames().toString());
		}
		
		ScriptEngine jsEngine = sem.getEngineByName("JavaScript");
//		sem.getEngineByExtension("js");
//		sem.getEngineByMimeType("text/javascript");
		  
		try {
			//simple eval JS
			jsEngine.eval("print('first test script parsed by java')");
			System.out.println();
			
			//eval JS file
			jsEngine.eval(new InputStreamReader(JSMain.class.getResourceAsStream(".\\demo.js")));
			jsEngine.eval("test('frank')");
			System.out.println();
			//Invocable call method directly
			Object invokefn = ((Invocable)jsEngine).invokeFunction("test", "Frank"); 
			System.out.println(invokefn);
			
			//same as getBindings(ScriptContext.ENGINE_SCOPE).put.
			jsEngine.put("v", "engine variable");
			((Invocable)jsEngine).invokeFunction("test", "Frank");

			System.out.println();
			Test test = ((Invocable)jsEngine).getInterface(Test.class);
			String rs = test.test("test java");
			System.out.println(rs);
			
//			Bindings binding = jsEngine.createBindings();//global
			Bindings binding = jsEngine.getBindings(ScriptContext.GLOBAL_SCOPE);
			binding.put("v", "global variable");
			jsEngine.eval(new InputStreamReader(JSMain.class.getResourceAsStream("demo.js")),binding);
			jsEngine.eval("test('frank')",binding);
			
			//compile
			CompiledScript script = ((javax.script.Compilable)jsEngine).compile(new InputStreamReader(JSMain.class.getResourceAsStream("demo.js")));
			script.eval();
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} 
		
	}
}
