import java.io.*;
import java.net.*;

public class FileServer {

	public static void main(String args[]) throws IOException {
		ServerSocket sSock = new ServerSocket(43231);
		System.out.println("Server started");
		while(true) {
			Socket sock = sSock.accept();
			System.out.println("Server got Client");
		
			InputStream in = sock.getInputStream();
			OutputStream out = sock.getOutputStream();
			PrintWriter pw = new PrintWriter(out);
			BufferedReader buf = new BufferedReader(new InputStreamReader(in));
			String fileName = buf.readLine();

			File file = new File(fileName);
			if(file.exists()) {
				BufferedReader fbr = new BufferedReader(new FileReader(file));
				pw.println("Found");
				System.out.println("Found");
				pw.flush();
				String lineRead;
				while((lineRead = fbr.readLine()) != null) {
					pw.println(lineRead);
					pw.flush();
				}
	
				fbr.close();

			}else {
				pw.println("Error");
				pw.flush();

			}
			//file.close();
			pw.close();
		
		}
	}

}
