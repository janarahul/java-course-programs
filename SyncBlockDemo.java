//sychronizing threads using synchronized block

class PrintData {

	static String data[] = {"Java","is","Object","Oreinted","Language"};

	public static void printData() {
		System.out.print("[");
		for(int i=0;i<data.length;i++) {
			System.out.print(" "+data[i]+" ");
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				System.out.println("Interrupted...");
			}	
		}
		System.out.println("]");
	}
}

class NewThread extends Thread {

	PrintData data;

	NewThread(PrintData pd) {
		this.data = pd;
		start();
	}

	public void run() {
		synchronized(data) {
			data.printData();
		}
	}

}

class SyncBlockDemo {

	public static void main(String args[]){

		PrintData obj = new PrintData();
		NewThread t1 = new NewThread(obj);
		NewThread t2 = new NewThread(obj);
		NewThread t3 = new NewThread(obj);
		NewThread t4 = new NewThread(obj);
	}
}
