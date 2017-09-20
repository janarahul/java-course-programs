//Throwing multiple times the same checked Exception

class MulThrow {

	static void funName() throws IllegalAccessException {
		try {
	
			throw new IllegalAccessException("Illegal Access");

		}catch(IllegalAccessException e2) {

			System.out.println("Exception handling in function funName");
			throw e2;
		}
	}
 
	public static void main(String args[]) {
		try{
			funName();
		}catch(IllegalAccessException e1) {

			System.out.println("Handling Exception in main");
		}
	}
}
