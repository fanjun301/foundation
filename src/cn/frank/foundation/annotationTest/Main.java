package cn.frank.foundation.annotationTest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		try {
			Class<?> klass = Class.forName("cn.frank.foundation.annotationTest.Main");
			Method[] methods = klass.getDeclaredMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(Test.class)) {
					System.out.println(String.format("method call Test annotation, method name: %s",  method.getName()));
					Test annotation = method.getAnnotation(Test.class);
					System.out.println(String.format("annotation value : %s ", annotation.value()));
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test(value="frank fan test for annotation")
	public void callTest(){
		System.out.println("annotation should be called");
	}
	
	@Deprecated
	@SuppressWarnings("rawtypes")
	public void test(){
		@SuppressWarnings("unused")
		List list = new ArrayList();
	}
	
}
