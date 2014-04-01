package cn.frank.foundation.immutableTest;

public class Main {

	public static void main(String[] args) {

		Employee e = new Employee(1L, "Fan Jun","SD");
		//no set method
		System.out.println(e.toString());
	}

}
