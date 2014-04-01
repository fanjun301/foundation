package cn.frank.foundation.reflectTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ClassMain {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

		try {
			Class c = Class.forName("cn.frank.foundation.reflectMain.Employee");
			/*
			 public static final int ABSTRACT 1024 
			 public static final int FINAL 16 
			 public static final int INTERFACE 512 
			 public static final int NATIVE 256 
			 public static final int PRIVATE 2 
			 public static final int PROTECTED 4 
			 public static final int PUBLIC 1 
			 public static final int STATIC 8 
			 public static final int STRICT 2048 
			 public static final int SYNCHRONIZED 32 
			 public static final int TRANSIENT 128 
			 public static final int VOLATILE 64 			 
			 */
			System.out.println(String.format("class modifier: %s", c.getModifiers()));
			System.out.println("----------field---------------");
			Field[] declaredFields = c.getDeclaredFields();
			for (Field field : declaredFields) {
				System.out.println(String.format("declare field : %s", field.getName()));
			}
			System.out.println("----------Constructor---------------");
			Constructor[] constructors = c.getDeclaredConstructors();
			for (Constructor constructor : constructors) {
				System.out.println(String.format("declare constructors: %s", constructor.getName()));
			}
			System.out.println("----------Method---------------");
			Method[] methods = c.getDeclaredMethods();
			for (Method method : methods) {
				System.out.println(String.format("declare method: %s", method.getName()));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}
