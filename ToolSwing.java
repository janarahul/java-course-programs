import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ToolSwing extends JFrame {
	JTextField a;
	JButton b;
	ToolSwing(){
		b =new JButton("Submit");
		a = new JTextField("Text field",30);
		b.setEnabled(false);
		setBackground(Color.CYAN);
		add(a);
		add(b);
		setSize(500,500);
		setVisible(true);
		setLayout(new FlowLayout());
		a.setToolTipText("<html><font color= green >This is toolTip</font></html>");
		a.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				setTitle(a.getText());
			}
			public void keyReleased(KeyEvent e1){
				String cont = a.getText();
				if(cont.equals("")==true){
					b.setEnabled(false);
				}
				else{
					b.setEnabled(true);
				}
			}
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String args[]){
		new ToolSwing();
	}
}
