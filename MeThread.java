//main Thread wiats for child thread

class First extends Thread{
	First() {
		super("First");
		start();
	}
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println(this+":"+i);
		}
	}

}

class Second extends Thread{
	Second() {
		super("Second");
		
		start();
		this.setPriority(Thread.MAX_PRIORITY);
	}
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println(this+":"+i);
		}
	}

}

class MeThread {
	public static void main(String args[]){
		First t1 = new First();
		Second t2 = new Second();
		try {
			t1.join();
			t2.join();
		}catch(InterruptedException e) {
		}
		
		System.out.println("Main ending...");
	}
} 
