package cn.frank.foundation.beanUtilsTest;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

/**
 * clone
 * copyProperty
 * populate
 * describe
 * @author jfan
 *
 */
public class BeanUtilsMain {

	public static void main(String[] args) {

        test4Clone();
        test4CopyProperties();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void test4CopyProperties() {
		Employee original = new Employee(1L, "Fan Jun", "SD");
		try {
			BeanUtils.copyProperty(original, "title", "SDN");
			System.out.println(original.toString());
			
			Employee ne = new Employee();
			BeanUtils.copyProperties(ne, original);
			System.out.println(ne.toString());
			
			Map map = new HashMap();
			map.put("id", 2L);
			map.put("name", "frank");
			map.put("titile", "Engineer");
			BeanUtils.populate(ne, map);
			System.out.println(ne.toString());
			
			map = BeanUtils.describe(ne);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
	}

	private static void test4Clone() {

		Employee original = new Employee(1L, "Fan Jun", "SD");
		try {
			Employee ce = (Employee)BeanUtils.cloneBean(original);
			System.out.println(ce.toString());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

}
