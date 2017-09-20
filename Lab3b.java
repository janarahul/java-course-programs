import java.util.*;
import java.io.*;

class GenRandThread extends Thread {
	public static int x;

	GenRandThread(String name) {
		super(name);
		System.out.println("Random number generater thread started");
		start();
	}
	public void run() {
		Random rand = new Random();
		for(int i=0;i<10;i++) {
			x = rand.nextInt(50);
			System.out.println(Thread.currentThread().getName()+" generated number: "+ x);
			try {
				sleep(1000);

			}catch(InterruptedException e) {
				System.out.println("GenRandThread Interrupted");
			}
		}

	}

}

class SqThread implements Runnable {
	Thread t;
	SqThread() {
		t = new Thread(this,"SqThread");
		t.setPriority(6);
		t.start();
	}
	public void run() {
		for(int i=0;i<10;i++){

			System.out.println("Square of generated rand number = "+ Math.pow(GenRandThread.x,2));
		
			try {
					t.sleep(1000);

			}catch(InterruptedException e) {
	
				System.out.println("SqThread Interrupted");
			}
		}
	
	}
}
class CuThread implements Runnable {
	Thread t;
	CuThread() {
		t = new Thread(this,"CuThread");
		t.setPriority(5);
		t.start();
	}
	public void run() {
		for(int i=0;i<10;i++){

			System.out.println("Cube of generated rand number = "+ Math.pow(GenRandThread.x,3));
			System.out.println("-------------------------------------");
		
			try {
					t.sleep(1000);

			}catch(InterruptedException e) {

				System.out.println("CuThread Interrupted");
			}
		}
	
	}
}

class Lab3b {
	public static void main(String[] args) {
		GenRandThread t1 = new GenRandThread("RandNoGenerator");
		
		SqThread t2 = new SqThread();
		CuThread t3 = new CuThread();
	}

}
