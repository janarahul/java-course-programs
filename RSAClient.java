import java.io.*;
import java.util.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.awt.*;
public class RSAClient implements ActionListener {  
	static Socket sock;
	JButton b1,b2;
	TextArea tA;
	BigInteger e,n;
	static ObjectOutputStream oOS;
	RSAClient() throws UnknownHostException,IOException{
		//System.out.println("Client Started");
		sock = new Socket("localhost",43231);


	}
	public void initUI() throws IOException {
		JFrame jf = new JFrame();
		jf.setLayout(new FlowLayout());
		jf.setSize(500,250);
		jf.setTitle("Client sender");
		jf.setVisible(true);
		b1 = new JButton("Get Public key");
		jf.add(b1);
		tA = new TextArea();
		jf.add(tA);
		b2 = new JButton("Send");
		b2.setEnabled(false);
		jf.add(b2);
		b1.addActionListener(this);
		b2.addActionListener(this);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.validate();

	}
	public void actionPerformed(ActionEvent ae )  {
		try {
		if(ae.getActionCommand() == "Send") {
			String msg = tA.getText();
			byte[] msgBytes = msg.getBytes();
			for(byte m: msgBytes) {
			
				BigInteger m1 = FileClient.encrypt(m,e,n);
				//PrintWriter pw = new PrintWriter(sock.getOutputStream());
				//tA.append(m1.toString());
				oOS.writeObject(m1);
				oOS.flush();
				

			}	

		}else {
			oOS.writeObject("GIVE");
			oOS.flush();
			
			try {
				Thread.sleep(500);
				BufferedReader buffInput = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				e = new BigInteger(buffInput.readLine());
				n = new BigInteger(buffInput.readLine());
				//tA.append(e.toString());
				//tA.append(n.toString());
				b2.setEnabled(true);
			}catch(Exception ie){

			}
		}
		}catch(IOException ie) {

		}
		

	}
	public static BigInteger encrypt(int m,BigInteger e,BigInteger n){
		
		return BigInteger.valueOf(m).modPow(e,n);

	}
	public static void main(String args[]) throws UnknownHostException,IOException{
		RSAClient fc = new RSAClient();
		fc.initUI();
		//System.out.println("Enter filename to get from server");
		
		//ufferedReader uin = new BufferedReader(new InputStreamReader(System.in));
		
		//String fileName = uin.readLine();


		OutputStream out = sock.getOutputStream(); 
		oOS = new ObjectOutputStream(out);	
		//pw.println(fileName);
		oOS.flush();
	}

}
