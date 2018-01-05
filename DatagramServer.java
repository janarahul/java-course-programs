import java.net.*;
import java.io.*;

public class DatagramServer {
	static byte[] buffer = new byte[1024];
	static int portNo= 7171;
	public static void main(String args[]) throws IOException {
		
		DatagramPacket recPacket = new DatagramPacket(buffer,buffer.length);
		DatagramSocket sSocket = new DatagramSocket(portNo);
		System.out.println("Server started..");
		while(true) {
			sSocket.receive(recPacket);
			System.out.println("Client :"+recPacket.getAddress().toString()+": ");
			String dataRec = new String(recPacket.getData(), 0, recPacket.getLength());

			System.out.println("Client: "+dataRec);

			if(dataRec.equals("DONE")) {
				System.out.println("Client Stopped communicating");
				continue;	

			}

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String dataSend = br.readLine();
			buffer = dataSend.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(buffer,buffer.length,InetAddress.getByName("localhost"),recPacket.getPort());
			sSocket.send(sendPacket);
			


		}
		

	}

}
