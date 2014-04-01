package cn.frank.foundation.inheritTest;

/**
 * # 常量 是可以继承的
 * # final 方法是不可以继承的
 * # static 方法可以继承、重载
 * # 重载不可以抛出更大的异常，只可以抛出当前异常的本身或子类
 * @author jfan
 *
 */
public class Son extends Parent{

//	public static void ps(){
//		System.out.println("s ps");
//	}
	
/**
 * can't overide final method	
 */
//	public final void pf(){
//		System.out.println("s pf");
//	}
	
/**
 * can't throw big exception than Parent class	 
 */
//	public void pne() throws Exception{
//		System.out.println("s pne");
//	}
	
	/**
	 * 可以抛出更小的exception
	 */
	@Override
	public void pne() throws MyException {

	}
}
