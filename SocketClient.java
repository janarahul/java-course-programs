import java.net.*;
import java.util.*;
import java.io.*;
class SocketClient {
	static Socket socketclient;
	static OutputStream oss;
	static DataOutputStream os;
	static final int SIZE = 100;// Total data size
	static final int SIZE1 = 10; //size of packet to apply crc
	static final int SIZE2 = 4; //no of crc bits
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
		System.out.println("Generated CRC :");
		for(int g=0;g<SIZE2;g++)
			System.out.print(res[g]);
		System.out.println();
		return res;

	}

	static void appendCRC(int res[]){
		int s=1;
		for(int p=SIZE1;p<pack.length;p++){
			pack[p]=res[s];
			//System.out.println("pack of "+p+" is "+pack[p]);
			s++;
		}
		System.out.println("data after appending CRC: ");
		for(int g=0;g<PSIZE;g++) {
			System.out.print(pack[g]);
		}
		System.out.println();

		try {
	
			for(int p=0;p<PSIZE;p++) {		
					os.writeInt(pack[p]);//(new Integer(pack[p])).byteValue());
			
			}
			//System.out.println("h");
			os.writeInt(-1);//new Integer(-1).byteValue());
		}catch(Exception e) {

		}


	}
	
	public static void main(String args[]){
		try {
			socketclient = new Socket("localhost",1256);
			oss = socketclient.getOutputStream();
			os = new DataOutputStream(oss);
			//byte x =1;
			//os.write(x);
			


		}catch(Exception e){
			e.printStackTrace();
		}
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
		divisor[1] = 0;
		divisor[2] = 1;
		divisor[3] = 0;
		/*for(int i=1;i<SIZE2;i++){
			divisor[i] = rand.nextInt(2);
		}*/
		System.out.println("divisor: ");
		for(int i=0;i<SIZE2;i++){
		
			System.out.print(divisor[i]);

		}
		System.out.println("");
		count = 0; 
		for(int i=0;i< SIZE;i++) {	
			count++;
			if(count <= SIZE1) {
				pack[count-1] = data[i];
				//System.out.println("pack of "+(count-1)+" is "+pack[count-1]);
			}
			else {
				for(int j=0;j<(SIZE2-1);j++){
					pack[count-1] = 0;
					count++;
				}
				i--;
				System.out.println("data before CRC: ");
				for(int g=0;g<PSIZE;g++)
					System.out.print(pack[g]);
				System.out.println();
				res = genCRC();
				appendCRC(res);
				//genCRC();
				System.out.println("------------------------");
				/*for(int z=0;z<(PSIZE-1);z++) {
				//	System.out.println(z+"pack "+pack[z]);

				}
				//System.out.println("---------");
				//checkCRC();*/
				count = 0;
			} 

		}
		for(int j=0;j<(SIZE2-1);j++){
			pack[count-1] = 0;
			count++;
		}
		System.out.println("data before CRC: ");
		for(int g=0;g<PSIZE;g++)
			System.out.print(pack[g]);
		System.out.println();
		res = genCRC();
		appendCRC(res);
		//genCRC();
		System.out.println("------------------------");
		//System.out.println("data before CRC: "+Arrays.toString(pack));
	}
}
