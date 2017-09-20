import java.io.*;
import java.util.*;

//Handling RuntimeException using try catch block

class Excp{
	
	public static void main(String args[]) {
		try {

			String name=null;
			//System.out.println(name.length());
			String str = "abc";
			System.out.println(Integer.parseInt(str));
			int a[] = new int[5];
			a[9]=50;
			
		}catch(NullPointerException e1) {
			e1.printStackTrace();
		}catch(NumberFormatException e2) {
			e2.printStackTrace();
		}catch(ArrayIndexOutOfBounds e3) {
			e3.printStackTrace();
		}finally {
			System.out.println("Exception handling with try and multiple catch block done");:
		}
			
	}

}
