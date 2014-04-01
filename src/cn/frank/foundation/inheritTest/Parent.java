package cn.frank.foundation.inheritTest;

import java.io.IOException;

public class Parent {
	
	public static final String P_VAR = "Parent";

	public static void ps(){
		System.out.println("p ps");
	}
	
	public final void pf(){
		System.out.println("p pf");
	}
	
	public void pne() throws IOException{
		System.out.println("p pne");
	}
}
