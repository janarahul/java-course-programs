//Thread by implementing  Runnable

class NewThread implements Runnable {
	String name;
	Thread t;
	NewThread(String nam) {
		this.name = nam;
		t = new Thread(this,nam);
		t.setPriority(Thread.MAX_PRIORITY);
		System.out.println("thread:"+t);
		//t.start();
		
	}
	public void run() {
		try {
			for(int i=0;i<5;i++) {
				System.out.println("thread running: "+ i );
				Thread.sleep(500);
			}			
		}catch(InterruptedException e) {
			System.out.println("child interrupted");
		}
		System.out.println("EXiting chhild");
	}
}

class TDemo {
	public static void main(String args[]) {
	new NewThread("Rahul").t.start();
		try {
			for(int i=0;i<5;i++) {
				System.out.println("main running: "+ i );
				Thread.sleep(1000);
			}
		}catch(InterruptedException e) {
			System.out.println("Main interrupted");
		}
		System.out.println("EXiting main");
	}
}
