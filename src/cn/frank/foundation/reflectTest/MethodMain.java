package cn.frank.foundation.reflectTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodMain {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		try {
			Class c = Class.forName("cn.frank.foundation.reflectMain.Employee");
			Object obj = c.newInstance();
			Method toStr = c.getMethod("echo", String.class);
			toStr.invoke(obj, "Frank Test");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
