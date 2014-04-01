package cn.frank.foundation;

public class ParamMain {

	public static void main(String[] args) {
		
		if (args != null && args.length > 0) {
			System.out.println("Recevice argument from main");
		}else {
			System.out.println("No argument received");
		}
		
	     ParamMain.add(1,2,3,4);
         ParamMain.add(1,2);   
	}
	
	public static void add(int ... num){
		int sum = 0 ;
		for (int i : num) {
			sum += i;
		}
		System.out.println(String.format("sum value is %d", sum));
	}

}
