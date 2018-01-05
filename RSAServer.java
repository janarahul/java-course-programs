import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.util.*;
import javax.swing.*;

public class RSAServer implements ActionListener {
	static BigInteger p,q,n,e;
	static String msg = "";
	private static BigInteger d,phin;
	static TextArea tA;
	static JButton b1;
	static JFrame sFrame;
	static ServerSocket sSock;

	RSAServer() throws IOException {
		//Initialize Server and its UI
		sSock = new ServerSocket(43231);
		//System.out.println("Server started");
		
		sFrame = new JFrame();
		sFrame.setLayout(new FlowLayout());
		sFrame.setSize(500,250);
		sFrame.setTitle("Client receiver");
		sFrame.setVisible(true);
		sFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tA = new TextArea();
		b1 = new JButton("Decrypt");
		b1.setEnabled(false);
		sFrame.add(b1);
		//sFrame.add(tA);
		b1.addActionListener(this);
		sFrame.validate();


	}
	public void generateRSA() {
		Random rd = new Random();
		p = BigInteger.probablePrime(32,rd);	
		//System.out.println(p.toString()+"  - "+p.subtract(BigInteger.valueOf(1)).toString());
		q = BigInteger.probablePrime(32,rd);	
		n = p.multiply(q);
		phin = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));	
		//System.out.println("p: "+p.toString()+" q: "+q.toString());
		 
		//boolean check = p1.primeToCertainty(32,rd);
		//System.out.println(phin.bitLength());

		e = new BigInteger(16,rd);
		while(!phin.gcd(e).equals(BigInteger.ONE)){

			e = new BigInteger(16,rd);

		}
		d= e.modInverse(phin);
		//tA.append(e.toString()+" "+n.toString() );


	}
	public static void main(String args[])  throws IOException,ClassNotFoundException {
		RSAServer fs = new RSAServer();
		fs.generateRSA();
		InputStream in;
		OutputStream out;
		PrintWriter pw;
		ObjectInputStream buf;
			Socket sock = sSock.accept();
			System.out.println("Server got Client");
		
			in = sock.getInputStream();
			out = sock.getOutputStream();
			pw = new PrintWriter(out);
			buf = new ObjectInputStream(in);
		while(true) {

			Object x = buf.readObject();
			try {
				msg = (String) x;
				//tA.append(msg);
			}catch(ClassCastException c) {

				msg = "";
			}
			if (msg.equals("GIVE")) {
				//tA.append("GIVE");
				pw.println(e.toString());
				pw.println(n.toString());
				pw.flush();

			}else {
				b1.setEnabled(true);
				TextField tf = new TextField();
				//sFrame.remove(tA);
				String encoded = "";
				tf.setText(encoded);
				sFrame.add(new Label("encrypted info: "));
				sFrame.add(tf);
				sFrame.validate();
				do {
					BigInteger m2 = ((BigInteger)x).modPow(d,n);
					encoded += (m2.toString());
					tf.setText(encoded);
					sFrame.remove(tf);
					sFrame.add(tf);
					sFrame.validate();
				
						//System.out.print(m2.byteValueExact());
					tA.append(""+(char)m2.intValueExact());
					

				}while((x = (BigInteger)buf.readObject())!= null);

			}
		
		}
	}	
	public void actionPerformed(ActionEvent ae) {
		sFrame.add(tA);
		sFrame.validate();
	}
	

}
