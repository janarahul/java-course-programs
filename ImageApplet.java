import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.*;
import java.awt.geom.*;

public class ImageApplet extends Applet {
	Button b1,b2,b3,b4;
	Image img;
	public void init(){
		img = getImage(getDocumentBase(),"dream.jpg"); 
		b1 = new Button("Rotate anti-clockwise 90deg");
		b2 = new Button("Rotate anti-clockwise 180deg");
		b3 = new Button("Rotate clockwise 90deg");
		b4 = new Button("resize");
		b1.setBounds(900,50,200,20);

	}
	public void paint(Graphics g){
		add(b1);
		Graphics2D g1 = (Graphics2D) g;
		//g.setFont(new Font("timesnewroman",Font.ITALIC,20));
		//g.drawString("Clock :"+hour+":"+min+":"+sec+"",30,30);
		//img = ImageTool.rotate(img,90);
		AffineTransform trans = new AffineTransform();
		trans.translate(img.getWidth(this)/2,img.getHeight(this)/2);
		trans.rotate( Math.toRadians(90) );
		g1.drawImage(img, trans, this);			
		//g.drawImage(ImageTool.rotate(img,90),0,0,this);
		
	}
	

}

