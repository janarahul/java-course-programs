import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class JTableDemo extends JFrame {
	static JTable jtab;
	static JLabel jlab;
	static String[][] val;
	JTableDemo() {
		setLayout(new FlowLayout());
		String[][] val = {{"Raghavendr.g", "113",  "80"},
			          {"Rahil", "001", "114", "90"},
		        	  {"Rahul J", "001", "115", "95"}
				};

		String[] header = {"Name", "USN", "Percentage"};
		jtab = new JTable(val, header);
		JScrollPane jsp = new JScrollPane(jtab);
		add(jsp);
		jlab =  new JLabel(" ");
		ListSelectionModel jl = jtab.getSelectionModel();
		jl.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent le) {
				int x = jtab.getSelectedRow();
				int y = jtab.getSelectedColumn();
				jlab.setText("Name: "+val[x][0]+" USN: "+val[x][1]+" percentage "+val[x][2]);

			}
		});
		add(jlab);
		setSize(500,500);
		jtab.setBounds(50, 50, 250, 250);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		

	}


	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JTableDemo();
			}
		});
	}
}
