import java.util.*;

class NoSufficientBalanceException extends Exception {
	double amount;

	public NoSufficientBalanceException(double amt) {
		this.amount = amt;
	}
	public double getAmount() {

		return this.amount;
	}
}


class Account {

	int id;
	double balance;
	
	Account(int i,double bal) {
		this.id=i;
		this.balance=bal;

	}
	public void deposit(double val) {
		this.balance += val;
	}
	public void debit(double val)  throws NoSuchMethodException {
		if(val <= this.balance) {
			this.balance -= val;
		}
		else {
			
			double diff = val-this.balance;
			throw new NoSuchMethodException();//NoSufficientBalanceException(diff) ;
		}
	}


}



class ExcDemo1 {

	public static void main(String[] args) throws NoSuchMethodException {

		Scanner sc = new Scanner(System.in);
		int op;
		Account obj = new Account(1001,1500.0);

		while(true) {
			System.out.println("Enter 1: deposit 2: withdraw 3: exit");
			op = sc.nextInt();
			switch(op) {
				case 1: System.out.println("Enetr amount to deposit");
					obj.deposit(sc.nextDouble());
					System.out.println("Balance= "+ obj.balance);
					break;

				case 2:	System.out.println("Enter amount to withdraw");	
					
					try {
	
						obj.debit(sc.nextDouble());
						System.out.println("Balance= "+ obj.balance);
					} catch (NoSuchMethodException e) {

						System.out.println("You need more "+ e.toString() +" rupees in your account");
						e.printStackTrace();
						throw e;
					}
					break;
				case 3:	System.exit(0);

			}
		}
	}

}
