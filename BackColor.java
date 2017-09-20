import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
class Coloring extends JFrame implements ChangeListener{
	Container x;
	JSlider js1,js2,js3;
	Coloring(){
		x = getContentPane();
		setLayout(new GridLayout(5,2,5,5));
		

		setVisible(true);
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		js1 = new JSlider(0,255,255);
		js2 = new JSlider(0,255,255);
		js3 = new JSlider(0,255,255);
		js1.addChangeListener(this);
		js2.addChangeListener(this);
		js3.addChangeListener(this);

		js1.setMajorTickSpacing(50);  
		js1.setPaintTicks(true);  
		js1.setPaintLabels(true); 

		js2.setMajorTickSpacing(50);  
		js2.setPaintTicks(true);  
		js2.setPaintLabels(true);
 
		js3.setMajorTickSpacing(50);  
		js3.setPaintTicks(true);  
		js3.setPaintLabels(true);
 
		add(new JLabel("RED"));
		add(js1); 
		add(new JLabel("GREEN"));
		add(js2); 
		add(new JLabel("BLUE"));
		add(js3); 
	}
	public void stateChanged(ChangeEvent e) {
		//JSlider source =(JSlider) e.getSource();
		
		x.setBackground(new Color(js1.getValue(),js2.getValue(),js3.getValue()));
	}

}

class BackColor {
	public static void main(String args[]){
		new Coloring();
	}
}
