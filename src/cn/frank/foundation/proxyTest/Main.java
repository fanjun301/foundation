package cn.frank.foundation.proxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Limitation must proxy base on interface
 * @author jfan
 *
 */
public class Main {

	public static void main(String[] args) {

		test4Proxy();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void test4Proxy() {
		List<String> source = new ArrayList<String>();
        InvocationHandler invocationHandler = new MyInvocationHandler(source);	
        Class[] cs = {List.class};
		Object proxy = Proxy.newProxyInstance(null, cs, invocationHandler);
		List<String> rs = (List<String>)proxy;
		rs.add("Test");
		rs.get(0);
		rs.size();
		
	}

}
