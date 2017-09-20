import java.io.*;

//nested try catch blocks

class TryDemo {

	public static void main(String args[]) {
		try {
			//String name = new String("something");
			String name = null;
			System.out.println(name.length());
			System.out.println("TESTING!!!");
			//System.out.println(Integer.parseInt("abc"));
			try {
				int a[] = new int[5];
				a[10]=50;
				System.out.println("inner Try1!!!");
			} catch (ArrayIndexOutOfBoundsException e2) {

				System.out.println("in the catch of inner try1");
			} finally {

				System.out.println("in the inside finally block1");

			}
			try {
				int a[] = new int[5];
				a[9]=50;
			} catch (ArrayIndexOutOfBoundsException e3) {

				System.out.println("in the catch of inner try2");
			} finally {

				System.out.println("in the inside finally block2");

			}
			System.out.println("TESTING333!!!");
		} catch (NullPointerException e1) {
			System.out.println("in the outside null pointer exception catch block:");
			try {
				int a[] = new int[5];
				a[9]=50;
			} catch (ArrayIndexOutOfBoundsException e4) {

				System.out.println("in the catch of inner catch1");
			} finally {

				System.out.println("in the inside finally block1 of catch");

			}

		} finally {
			System.out.println("in the outside finally block");
			try {
				int a[] = new int[5];
				a[9]=50;
			} catch (ArrayIndexOutOfBoundsException e5) {

				System.out.println("in the catch of finally1");
			} finally {

				System.out.println("in the inside finally block1 of outside finally");

			}
		}

	}


}
