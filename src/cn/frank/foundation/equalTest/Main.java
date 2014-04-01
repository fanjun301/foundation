package cn.frank.foundation.equalTest;

public class Main {

	public static void main(String[] args) {

		Employee e1 = new Employee(1L,"Fan Jun","SD");
		Employee e2 = new Employee(2L,"Frank Fan","SD");
		System.out.println(String.format("Employee e1 hash code: %s", e1.hashCode()));
		System.out.println(String.format("Employee e2 hash code: %s", e2.hashCode()));
		System.out.println(String.format("Equal Test e1 and e2: %s", e1.equals(e2)));
		System.out.println(String.format("Equal Test e1 and new e1: %s", e1.equals(new Employee(1L,"Fan Jun","SD"))));
	}

}
