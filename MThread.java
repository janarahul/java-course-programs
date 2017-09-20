import java.util.*;

class MThread extends Thread{
	public  void run() {
		Random obj = new Random();
		System.out.println(obj.nextInt(100));
	}
	public static void main(String[] args) {
		MThread obj = new MThread();
		obj.start();
		System.out.printf(Thread.currentThread().getName());


	}


}
