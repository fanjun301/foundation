package cn.frank.foundation.generic;

public class GenericImpl extends Generic<Employee> {

	public GenericImpl() {
		super();
		System.out.println(String.format("class name = %s ", c.getName()));
	}

	public static void main(String[] args) {
		new GenericImpl();
	}
}
