import java.awt.*;
import java.awt.event.*;

class Calc extends Frame implements ActionListener{
	TextField t1,t2,t3;
	Label l1,l2,l3; 
	Button b1,b2,b3,b4,b5,b6,b7;
	Calc() {
		setTitle("Calculator");
		setLayout(new GridLayout());
		setVisible(true);
		setSize(500,500);
		
		t1 = new TextField();		
		t2 = new TextField();	
		t3 = new TextField();
		
		l1 = new Label();
		l1.setText("input1");	
		l2 = new Label();
		l2.setText("input2");
		l3 = new Label();
		l3.setText("answer");
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(l3);
		add(t3);

		b1 = new Button();
		b1.setLabel("+");
		b1.addActionListener(this);
		b2 = new Button();
		b2.setLabel("-");
		b2.addActionListener(this);
		b3 = new Button();
		b3.setLabel("*");
		b3.addActionListener(this);
		b4 = new Button();
		b4.setLabel("/");
		b4.addActionListener(this);
		b5 = new Button();
		b5.setLabel("^");
		b5.addActionListener(this);
		b6 = new Button();
		b6.setLabel("clear");
		b6.addActionListener(this);
		b7 = new Button();
		b7.setLabel("exit");
		b7.addActionListener(this);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(b6);
		add(b7);
		t1.setBounds(50,140,100,20);
		
	}

	public void actionPerformed(ActionEvent ae) {
		float x,y;		
		
		if(ae.getSource()== b1) {
			x = Float.parseFloat(t1.getText());
			y = Float.parseFloat(t2.getText());
			t3.setText(String.valueOf(x+y));				
		}
		if(ae.getSource()== b2) {
			x = Float.parseFloat(t1.getText());
			y = Float.parseFloat(t2.getText());
			t3.setText(String.valueOf(x-y));				
		}
		if(ae.getSource()== b3) {
			x = Float.parseFloat(t1.getText());
			y = Float.parseFloat(t2.getText());
			t3.setText(String.valueOf(x*y));				
		}

		if(ae.getSource()== b4) {
			x = Float.parseFloat(t1.getText());
			y = Float.parseFloat(t2.getText());
			t3.setText(String.valueOf(x/y));				
		}
		if(ae.getSource()== b5) {
			x = Float.parseFloat(t1.getText());
			y = Float.parseFloat(t2.getText());
			Double d = Math.pow(x,y);
			t3.setText(d.toString());				
		}
		if(ae.getSource()== b6) {
			t1.setText("");
			t2.setText("");				
		}
		if(ae.getSource()== b7) {
			System.exit(0);				
		}
				
	}
	public static void main(String args[]){
		new Calc();
	}

}