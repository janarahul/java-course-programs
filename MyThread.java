import java.util.*;

class FirstThread extends Thread {
	public void run() {
		for(int i=0;i<10;i++) {
			//Random x = new Random();
			System.out.println(Thread.currentThread().getName()+":"+ i);//x.nextInt(100));
			try {
				sleep(1000);
			}catch(InterruptedException e) {
				System.out.println("Interrupted...");

			}
		}

	}


}

class SecondThread extends Thread {
	public void run() {
		for(int i=0;i<10;i++) {
			//Random x = new Random();
			System.out.println(this.getName()+":"+ Math.pow(i,2));//x.nextInt(100));
			System.out.println(this);
			try {
				sleep(1000);
			}catch(InterruptedException e) {

				System.out.println("Interrupted...");

			}
		}

	}


}

class MyThread {
	
	public static void main(String args[]) {
		System.out.println(Thread.currentThread().getName());
		

		FirstThread f = new FirstThread();
		SecondThread s = new SecondThread();
		f.start();
		s.start();
		
		try {
			f.join();
			s.join();
		}catch(InterruptedException e) {

			System.out.println("Interrupted...");

		}
		System.out.println("Main thread ending");
	}


}
