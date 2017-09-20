import java.util.*;

//throw RuntimeException in a constructor

class ThrowDemo {
	static int marks;

	ThrowDemo(int mark) {
		this.marks =  mark;
		if(marks < 0){
			throw new ArithmeticException("Marks cannot be less than 0 ");	
		}
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter marks: ");
		int mar = sc.nextInt();
		/*try {
			ThrowDemo ex = new ThrowDemo(mar);
		}catch(ArithmeticException e) {
			e.printStackTrace();
			String arg[] = {};
			ThrowDemo.main(arg);
		}*/
			ThrowDemo ex = new ThrowDemo(mar);
		
	}
}
