import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.net.*;

public class FApplet extends Applet implements ActionListener {
	Button b;
	public void init() {
 		b =new Button("Click me");
                b.addActionListener(this);
	}
	public void paint(Graphics g){
		add(b);
		showStatus("Applet Running");
		//g.setFont(new Font("timesnewroman",Font.BOLD,30));
		//g.drawString("This the Fon Applet",50,50);	
	
	}
	public void actionPerformed(ActionEvent e){
                try {
                        getAppletContext().showDocument(new URL(getCodeBase()+"../l.html"),"_blank");
                }catch(Exception ex){

                }
        }


} 
