import java.net.*;
import java.io.*;

public class DatagramClient {
	static int portNo = 6161;
	static byte[] buffer = new byte[1024];
	public static void main(String args[]) throws IOException{
		DatagramSocket cSocket = new DatagramSocket(portNo);

		//DatagramPacket sendPacket = new DatagramPacket(buffer,buffer.length,InetAddress.getByName("localhost"),7171);
		while(true ) {
			//buffer = new byte[1024];
			System.out.println("Enter Data or DONE to exit");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String sendData = br.readLine();
			buffer = sendData.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(buffer,buffer.length,InetAddress.getByName("localhost"),7171);


			cSocket.send(sendPacket);
			if(sendData.equals("DONE")) {
	
				System.exit(0);
			}
			buffer = new byte[1024];
			DatagramPacket recPacket = new DatagramPacket(buffer,buffer.length);

			cSocket.receive(recPacket);

			String dataRec = new String(recPacket.getData(), 0, recPacket.getLength());
			System.out.println("Server: "+dataRec);

		}

	}

}
