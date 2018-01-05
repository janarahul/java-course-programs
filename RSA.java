import java.io.*;
import java.util.*;
import java.math.BigInteger;

class RSA {
	static BigInteger p,q,n,phin,e;
	

	public static BigInteger encrypt(int m,BigInteger e,BigInteger n){
		
		return BigInteger.valueOf(m).modPow(e,n);

	}
	public static void main(String args[]) {
		Random rd = new Random();
		p = BigInteger.probablePrime(32,rd);	
		//System.out.println(p.toString()+"  - "+p.subtract(BigInteger.valueOf(1)).toString());
		q = BigInteger.probablePrime(32,rd);	
		n = p.multiply(q);
		phin = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));	
		System.out.println("p: "+p.toString()+" q: "+q.toString());
		 
		//boolean check = p1.primeToCertainty(32,rd);
		System.out.println(phin.bitLength());

		e = new BigInteger(16,rd);
		while(!phin.gcd(e).equals(BigInteger.ONE)){

			e = new BigInteger(16,rd);

		}
		
		System.out.println("Public key: "+e+","+n);
		System.out.println("Enter message");
		Scanner sc = new Scanner(System.in);
		String msg  = sc.nextLine();
		byte[] msgBytes = msg.getBytes();
		//String recMsg = "";
		//ArrayList<Byte> recMsg = new ArrayList<Byte>();
		System.out.println("Message after decryption is: ");
		for(byte m: msgBytes) {
			
			BigInteger m1 = RSA.encrypt(m,e,n);
			BigInteger d = e.modInverse(phin);
			BigInteger m2 = m1.modPow(d,n);
			//System.out.print(m2.byteValueExact());
			System.out.print((char)m2.byteValueExact());
			//recMsg.concat((new String((char)m2.byteValueExact())));

		}
		System.out.println();
		//System.out.println(Arrays.toString(recMsg.toArray()));
		//System.out.println(recMsg);
		
	}	

}
