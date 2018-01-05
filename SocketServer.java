
import java.net.*;
import java.util.*;
import java.io.*;
class SocketServer {
	static final int SIZE1 = 10; 
	static final int SIZE2 = 4;
	static final int PSIZE = SIZE1+SIZE2-1;
	static int divisor[] = new int[SIZE2];
	static int pack[] = new int[PSIZE];
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
		for(int g=0;g<SIZE2;g++){
			System.out.print(res[g]);
		}
		System.out.println();
		return res;

	}
	static Socket socket;
	public static void main(String args[]){	
		int data;
		divisor[0]= 1;
		divisor[1]= 0;

		divisor[2]= 1;
		divisor[3]= 0;
		int x=0;
		Random rand = new Random();
		int f= rand.nextInt(SIZE1);
		try {
			ServerSocket socketserver = new ServerSocket(1256);
			socket = socketserver.accept();
			int z=0;
			while(true) {
				try {
					InputStream is = socket.getInputStream();
					DataInputStream isr = new DataInputStream(is);
					System.out.print("Received data is :");
					while((data = isr.readInt()) != -1){
						pack[z] = data;
						if(x == f) {
							pack[z] = rand.nextInt(2);
							data = pack[z];
						}
						System.out.print(data);
						z++;
					}
					
					System.out.println();
					genCRC();
					z=0;
					x++;
					//System.out.println("pack completed");
				}catch(Exception e) {
					z=0;
					socket = socketserver.accept();
					x=0;
				}
			}
			


		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
