//Generates Data and CRC and Checks 
import java.io.*;
import java.util.*;

public class CrcChecker {
	static final int SIZE = 200;
	static final int SIZE1 = 20; 
	static final int SIZE2 = 16;
	static final int PSIZE = SIZE1+SIZE2-1;
	static int data[];
	static int divisor[];
	static int pack[];
	
	static int[] genCRC() {
		
		int fir=0;
		int res[] = new int[SIZE2];
		//System.out.println(Arrays.toString(pack));
		for(int i=0;i<SIZE2;i++){
			res[i]=pack[i];

		}
		for(int p=0;p<SIZE1;p++) {
			fir = res[0];
			for(int i=1;i<SIZE2;i++){
								
				res[i-1] = res[i] ^ (fir*divisor[i]);
				
			}
			if(p<(SIZE1-1)){
				res[(SIZE2-1)]=pack[p+SIZE2];
			}
			else{
				for(int i=(SIZE2-1);i>0;i--)
					res[i] = res[i-1];
				res[0] = 0;
			}
			
		}		
		System.out.print("Generated CRC : ");
		for(int v=0;v<SIZE2;v++) {
			System.out.print(res[v]);
		}
		System.out.println();
		return res;

	}
	static void appendCRC(int res[]){
		int s=1;
		for(int p=SIZE1;p<pack.length;p++){
			pack[p]=res[s];
			s++;
		}
		System.out.print("data after appending CRC: ");
		for(int v=0;v<PSIZE;v++) {
			System.out.print(pack[v]);
		}
		System.out.println();
		//System.out.println();


	}

	public static void main(String args[]){
		data = new int[SIZE];
		int res[] = new int[SIZE2]; 
		divisor = new int[SIZE2];
		pack = new int[PSIZE];
		int count=0;
		Random rand = new Random();

		for(int i=0;i< SIZE;i++){
			data[i] = rand.nextInt(2);
		}

		divisor[0] = 1;
		for(int i=1;i<SIZE2;i++){
			divisor[i] = rand.nextInt(2);
		}
		System.out.println("divisor: ");
		for(int i=0;i<SIZE2;i++){
		
			System.out.print(divisor[i]);

		}
		System.out.println("");
		count = 0; 
		//Random rand = new Random();
		int wrong = rand.nextInt(10);
		int g=0;
		for(int i=0;i< SIZE;i++) {	
			//System.out.println("i: "+i);
			count++;
			//System.out.println("count: "+count);
			if(count <= SIZE1) {
				pack[count-1] = data[i];
				//System.out.println(count);
			}
			else {
				g++;
				for(int j=0;j<(SIZE2-1);j++){
					pack[count-1] = 0;
					count++;
				}
				i--;
				System.out.print("data before CRC: ");
				for(int v=0;v<PSIZE;v++){
					System.out.print(pack[v]);
				}
				System.out.println();
				res = genCRC();
				if(g==wrong) {
					for(int x=0;x<PSIZE;x++){
						pack[x] = rand.nextInt(2);
					}

				}
				appendCRC(res);
				genCRC();
				System.out.println("------------------------");
				/*for(int z=0;z<(PSIZE-1);z++) {
				//	System.out.println(z+"pack "+pack[z]);

				}
				//System.out.println("---------");
				//checkCRC();*/
				//System.out.println(count);
				count = 0;
			}
			
			/*if((i == SIZE-1 )&& (count ==0)) {
				System.out.println("i= "+i+" count made 999");
				count =SIZE1;
			}*/

		}
		for(int j=0;j<(SIZE2-1);j++){
			pack[count-1] =0;
			count++;
		}
		System.out.print("data before CRC: ");
		for(int v=0;v<PSIZE;v++){
			System.out.print(pack[v]);
		}
		System.out.println();
		res = genCRC();
		appendCRC(res);
		genCRC();
		System.out.println("------------------------");

		/*System.out.println("data before CRC: "+Arrays.toString(pack));
		res = genCRC();
		appendCRC(res);
		genCRC();*/
				
	}
}
