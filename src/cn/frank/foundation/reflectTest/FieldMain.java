package cn.frank.foundation.reflectTest;

import java.lang.reflect.Field;

public class FieldMain {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

		try {
			Class c = Class.forName("cn.frank.foundation.reflectMain.Employee");
			Object obj = c.newInstance();
			Field dfid = c.getDeclaredField("id");
			dfid.setAccessible(true);
			dfid.set(obj, 1L);
			System.out.println(obj.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
	}

}
