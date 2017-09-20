import java.util.*;


class Reservation{
	int availableSeats = 30;

	
	public void selectSeats(int seats) {

		if(seats <= availableSeats){
			availableSeats -= seats;
			System.out.println(seats+" are booked successfully");
			System.out.println("Available Seats: "+ availableSeats);
		}
		else {
			System.out.println("booking failed"+seats+":Only "+availableSeats+" are available.");
		}
	}

}

class Threads extends Thread {
	Reservation resv;
	int seats;
	Threads(Reservation res,int seat) {
		this.resv = res;
		this.seats = seat;
		start();
	}
	public void run() {
	{ 
			resv.selectSeats(seats);
		}
	}

}	

class Reserve {
	public static void main(String[] args) {
		Reservation res = new Reservation();
		Threads t1 = new Threads(res,10);
		Threads t2 = new Threads(res,20);
		Threads t3 = new Threads(res,5);
	
	}
}
