import java.io.*; 
import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import javax.swing.SwingUtilities;
import javax.media.*;
import javax.swing.event.*;
import java.io.File;
import java.net.URL;
import javax.media.Manager;
import javax.media.Player;

class audio extends JPanel implements ActionListener,ListSelectionListener,Runnable {
	DefaultListModel listModel;
	JList list;

	JFrame frame;
	JButton b1,b2,b3,b4;
	JPanel p,p2;
	JFrame f1;
	
	JFileChooser fc;
	private Player audioplayer=null;
	private File file;

	public audio() {
		listModel = new DefaultListModel();
	
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		JScrollPane listScrollPane = new JScrollPane(list);
		list.setVisibleRowCount(5);

		f1=new JFrame("Audio Player");
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fc=new JFileChooser();
		

		p=new JPanel();
		//p.setLayout(null);
		//p.setBackground(Color.blue);
		//p.add(p2).setBounds(0,0,350,60);
		p.setBackground(Color.darkGray);
		f1.add(p);

		b1=new JButton("Play");
		b1.addActionListener(new playing());
		b3=new JButton("Stop");
		b3.setEnabled(false);
		b3.addActionListener(this);
		b2=new JButton("ADD");
		b3.setEnabled(true);
		b2.addActionListener(this);
		b4=new JButton("Remove");
		b4.addActionListener(this);

		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(b4);
		f1.setSize(400,300);
		f1.setVisible(true);
		p.add(listScrollPane);
	}

	public void valueChanged(ListSelectionEvent e) {


	}
	public void actionPerformed(ActionEvent evt) {

		/*if(evt.getSource()==ofile) {

			int returnVal=fc.showOpenDialog(audio.this);
			if(returnVal==JFileChooser.APPROVE_OPTION) {

				file=fc.getSelectedFile();
			}
			else {

				System.out.println("File Access Canceled by user"); 
			}
		}*/
		if(evt.getSource() == b2){
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnVa=fc.showOpenDialog(audio.this);
			if(returnVa==JFileChooser.APPROVE_OPTION) {

				file=fc.getSelectedFile();
			}
			else {
				file=null;
				System.out.println("File Access Canceled by user"); 
			}
			listModel.addElement(file);
		}
		if(evt.getSource()==b3) {

			audioplayer.stop();
			
		}
		if(evt.getSource()==b4) {
			try {
				listModel.removeElementAt(list.getSelectedIndex());
			}catch(Exception e) {
		
			}
		}
		
 		
 	}
 	public void oplay() {

		/*fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result=fc.showOpenDialog(this);
		if(result==JFileChooser.CANCEL_OPTION)
			file=null;
		else
			file=fc.getSelectedFile();
			*/
		int index = list.getSelectedIndex();
		Thread t = new Thread(this);
		t.start();
		try {
		//	t.join();
		}catch(Exception e){}
		//System.out.println(name);
 	}
	public void run() {
		int index = list.getSelectedIndex();
		for(int i=index;i<list.getModel().getSize() ;i++){
			file =(File)  listModel.getElementAt(i);
			cplay();
			//listModel.removeElementAt(i);			
			
		}

	}
	public void cplay() {

		if(file==null)
			return;
		try {

			audioplayer = Manager.createRealizedPlayer(file.toURI().toURL());
 			audioplayer.start();
			Thread.currentThread().sleep(((int)audioplayer.getDuration().getSeconds())*1000+300);
 		}catch(Exception xe) {

			JOptionPane.showMessageDialog(frame,"Invalid Files Types..","Error  loading page",JOptionPane.ERROR_MESSAGE);
		}
 	}
	public class playing implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			oplay();
		
		}
 	}
	public static void main(String args[]) {

		audio me = new audio();
	}
}
