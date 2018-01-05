import java.util.*;
import java.io.*;

public class Leak {
	
	public static void main(String args[]) throws InterruptedException {

		int NOP;
		Random rd = new Random();
		Scanner sc = new Scanner(System.in);

	
		int packet_sz;
		int  i, clk, bufferSize, outRate, packSizeRem=0, packTime, outPack;

		

		System.out.println("Enter output rate (bytes/sec)");
		outRate = sc.nextInt();
		System.out.println("Enter Buffer size (bytes)");
		bufferSize = sc.nextInt();
		i =1;
		while(true) {
			
			packet_sz = (rd.nextInt(7)+1)*100;
			System.out.print("\npacket["+i+"]:"+packet_sz+" bytes\t");

			if( (packet_sz + packSizeRem) > bufferSize) {
 
				if(packet_sz > bufferSize) //compare the packet siz with bucket size 
					System.out.print("Incoming packet size ("+packet_sz+" bytes) is Greater than bucket capacity ("+bufferSize+" bytes)-PACKET REJECTED");
				else
					System.out.print("\nBucket capacity exceeded-PACKETS REJECTED!!");
			}
			else {
				packSizeRem += packet_sz;
				System.out.print("\nIncoming Packet size: "+packet_sz);
				System.out.print("\nBytes remaining to Transmit: "+ packSizeRem);
			}
			if(packSizeRem != 0) {
				if(packSizeRem <= outRate){ 
			  		outPack = packSizeRem;packSizeRem = 0; 
				}
				else{
					outPack = outRate; packSizeRem -= outRate;
				}
				System.out.println("\nPacket of size "+ outPack+" Transmitted");
				System.out.println("----Bytes Remaining to Transmit: "+ packSizeRem);
			}
			else {
				System.out.print("\nNo packets to transmit!!");
			}

			Thread.sleep(1000);
			i++;
		}
		
	} 
			 	
}


