import java.io.*;
import java.util.*;
import java.net.*;

public class FileClient {  

	public static void main(String args[]) throws UnknownHostException,IOException{
		System.out.println("Client Started");
		Socket sock = new Socket("localhost",43231);
		System.out.println("Enter filename to get from server");
		
		BufferedReader uin = new BufferedReader(new InputStreamReader(System.in));
		
		String fileName = uin.readLine();


		OutputStream out = sock.getOutputStream(); 
		PrintWriter pw = new PrintWriter(out);	
		pw.println(fileName);
		pw.flush();

		InputStream in = sock.getInputStream();

		BufferedReader inBuff = new BufferedReader(new InputStreamReader(in));


		if(inBuff.readLine().equals("Found")){
			File file = new File(fileName);

			PrintWriter fpw = new PrintWriter(file);

			String lineRead;

			while((lineRead = inBuff.readLine())!= null){
				fpw.println(lineRead);
			}
			System.out.println("File Received...");	
			fpw.close();
			//file.close();


		}else{
			System.out.println("File not Found");

		}
		pw.close();
		uin.close();
		in.close();
		out.close();


	}

}
