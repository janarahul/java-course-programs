import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;


	public class BackgroundExecutor {

	 private static ExecutorService backgroundEx = Executors.newCachedThreadPool(); //UI thread shouldn't do math

	  

	 public BackgroundExecutor(){}

	  

	 public static ExecutorService get() { return backgroundEx;}

	}

public class SeekBar extends JProgressBar {		
	 

	 private int updatedValue = 0; //sharing between different scopes		
	 

	 public void updateSeekBar(long progress, float totalVal)

	 {

	  BackgroundExecutor.get().execute(new UpdatingTask(progress, totalVal)); //Another thread will calculate the relative position

	  setValue(updatedValue);

	 }

	  

	 /**

	  * Task used for updating the seek value in another thread.

	  * @author Pierluigi

	  */

	 private class UpdatingTask implements Runnable {

	 

	  long progress; float totalVal;

	  public UpdatingTask(long progress, float totalVal) {

	   this.progress = progress;

	   this.totalVal = totalVal;

	  }

	   

	  @Override

	  public void run() {

	   int lp = (int) (progress / 1000); //progress comes in microseconds

	   int seekLenght = getMaximum();

	   int n = (int) ((lp/(totalVal*1000))*seekLenght);

	   updatedValue = lastSeekVal+n;

	  }

	 }

	 ///////////////////////////////////////////////////////////

	  

	 /**

	  * New Constructor, sets a mouseListener

	  * (extends JProgressBar)

	  */

	 public SeekBar()

	 {

	  super();

	  setMaximum(10000); //it's smoother this way

	  addMouseListener(new MouseListener() {

	    

	   @Override

	   public void mouseReleased(MouseEvent e) {

	   }

	    

	   @Override

	   public void mousePressed(MouseEvent e) {

	    float val =  ((float)e.getX()/getWidth()) * getMaximum();

	    returnValueToPlayer(val);

	    setValue((int)val);

	    log("SeekBar pressed: " + val + " x: " + e.getX());

	        

	   }

	    

	   @Override

	   public void mouseExited(MouseEvent e) {

	   }

	    

	   @Override

	   public void mouseEntered(MouseEvent e) {

	   }

	    

	   @Override

	   public void mouseClicked(MouseEvent e) {
	   }
	  });

	 }

	  

	 /**

	  * Informs the player about the relative value selected in the seekbar

	  * @throws BasicPlayerException

	  */

	 private void returnValueToPlayer(float val){

	  //TODO inform our player

	 }

	 

	 private void log(String str)

	 {

	  System.out.println("SeekBar] " +str);

	 }

	}

public class MusicView extends JFrame {
	DefaultListModel<String> songList = new DefaultListModel<String>();

	SeekBar seekbar = new SeekBar();
	JPanel container = new JPanel();

	JButton btnPlay = new JButton();
	JButton btnAdd = new JButton();

	JButton btnNext = new JButton();		 
	JButton btnPrev = new JButton();
	JButton btnShSt = new JButton();
	JButton btnShWf = new JButton();		 
	JButton btnShDi = new JButton();

	JButton btnDel = new JButton();
	JButton btnDelAll = new JButton();
	JMenuBar topMenu = new JMenuBar();

	JList<String> jSongList = new JList<String>(songList);

	JLabel lblplaying = new JLabel();
	JLabel lblst = new JLabel();
	JLabel lblet = new JLabel();
	JFileChooser fc = new JFileChooser();
	
	public MusicView() {
		init();
		//initMenu();
		//uiBehaviour();
	}

	/*

	   Init Swing graphics UI

	*/

	private void init() {


	  //MainView

		setTitle("Music Player - Java - 1.0");
		int _H = 300;
		int _W = 330;
		setSize(_W,_H);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setResizable(false);
		//Container
		container.setLayout(null);
		getContentPane().add(container);
		//Buttons
		int btn_h = 35;
		int line1 = 80;
		JPanel contBtns = new JPanel();
		contBtns.setBounds(0, line1, 180, btn_h);
		btnPrev.setText("<<");
		btnPrev.setBounds(0, 0, 50, btn_h);
		btnPlay.setText(">");
		btnPlay.setMnemonic(KeyEvent.VK_SPACE);
		btnPlay.setBounds(0, 0, 50, btn_h);
		btnNext.setText(">>");
		btnNext.setBounds(0, 0, 50, btn_h);
		btnAdd.setText("Add Song");
		btnAdd.setBounds(_W-80,line1,70,btn_h);
		contBtns.add(btnPrev);
		contBtns.add(btnPlay);
		contBtns.add(btnNext);
		container.add(contBtns);
		container.add(btnAdd);
		//Now Playing Panel
		JPanel panelNP = new JPanel();
		panelNP.setLayout(new BoxLayout(panelNP, BoxLayout.PAGE_AXIS));
		panelNP.setToolTipText("Now Playing");
		panelNP.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 0, Color.gray));
		panelNP.setBounds(5, line1-25, _W-15, 20);
		//JLabel lblnp = new JLabel("Now Playing:");
		lblplaying.setText("Now Playing: ");
		lblplaying.setBounds(5, 0, 100, 40);
		//panelNP.add(lblnp);
		panelNP.add(lblplaying);
		container.add(panelNP);
		//SongList
		int h_list = 100;
		//jSongList.setBounds(0, line1+50, _W, h_list);
		JScrollPane listScroller = new JScrollPane(jSongList);
		listScroller.setPreferredSize(new Dimension(_W-10,h_list));
		listScroller.setBounds(0, line1+50, _W-10, h_list);
		container.add(listScroller);
		//container.add(jSongList);
		//2Row Buttons
		int line2 = line1+h_list+50;
		JPanel contBtns2 = new JPanel();
		//contBtns2.setLayout(new BoxLayout(contBtns2, BoxLayout.PAGE_AXIS));
		contBtns2.setBounds(0, line2, 220, 50);
		//contBtns2.setBackground(Color.lightGray);
		btnShSt.setText("STAT");
		btnShWf.setText("ShWf");
		btnShDi.setText("ShDi");
		contBtns2.add(btnShSt);
		contBtns2.add(btnShWf);
		contBtns2.add(btnShDi);
		container.add(contBtns2);

	  //DelBtns

		btnDel.setBounds(_W-55, line2+5, 45, 30);
		btnDel.setText("X");
		container.add(btnDel);
		//Labels song time
		JPanel contSlbl = new JPanel();
		contSlbl.setBounds(10, 15, _W-20, 20);
		contSlbl.add(lblst);
		contSlbl.add(lblet);
		lblst.setText("00:00");
		lblst.setBorder(new EmptyBorder(0, 0, 0, 200));
		lblet.setText("00:00");
		container.add(contSlbl);
		seekbar.setBounds(5, 10, _W-15, 10);
		container.add(seekbar);
	 }
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MusicView mv = new MusicView();
				mv.setVisible(true);
			}
		});
	}
	private void log(String line) {
		System.out.println("UI-Main "+line);
	}
}
