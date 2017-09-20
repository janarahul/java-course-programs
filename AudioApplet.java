import java.awt.*;
import javax.swing.*;
import java.applet.Applet;
import java.applet.*;
import java.awt.event.*;
/*
<applet code="AudioApplet" width="600" height="300"></applet>
*/
public class AudioApplet extends JApplet implements ActionListener {
	public JButton play,stop,adda;
	public AudioClip audio;
	public void init() {
		setLayout(new FlowLayout());
		play = new JButton("play");
		stop = new JButton("stop");
		adda = new JButton("add .wav");
		add(play);
		add(stop);
		add(adda);
		play.addActionListener(this);
		stop.addActionListener(this);
		adda.addActionListener(this);
		audio = getAudioClip(getCodeBase(),"audio1.wav");
	}
	public void actionPerformed(ActionEvent e) {
		JButton src =(JButton) e.getSource();
		if(src.getLabel() == "play"){
			audio.play();
		}
		else if(src.getLabel() == "stop"){
			audio.stop();
		}
		else if(src.getLabel() == "add .wav"){
			JFileChooser fc = new JFileChooser();
			int rval = fc.showOpenDialog(this);
		}
	}
}
