package cn.frank.foundation.proxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	private Object target;
	public MyInvocationHandler(Object target){
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println(String.format("Method will be called : %s ", method.getName()));
		System.out.println("before call method");
		Object result = method.invoke(target, args);
		System.out.println("after call method");
		return result;
	}

}
