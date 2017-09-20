import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.*;

public class ClockApplet extends Applet implements Runnable {
	Thread t;
	Button b;
	public void init() {
		t = new Thread(this);
		t.start();
		b= new Button("Click");
		
	}
	public void paint(Graphics g){
		GregorianCalendar cal = new GregorianCalendar();
		int hour = cal.get(Calendar.HOUR);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		g.setFont(new Font("timesnewroman",Font.ITALIC,20));
		g.drawString("Clock :"+hour+":"+min+":"+sec+"",30,30);
		this.add(b);
	}
	public void run() {
		while(true) {
			repaint();
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e) {

			}
		}
	}

}

