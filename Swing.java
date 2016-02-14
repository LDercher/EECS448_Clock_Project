
//	EECS 448 -- CLOCK PROJECT
//	JAVA SWING SET - DIGITAL DISPLAY
//	Original author: VICTOR BERGER - KUID 2770737 - victorluiz@ku.edu


import java.awt.*;
import javax.swing.*;


public class Swing extends JFrame {
    WatchPanel watch = new WatchPanel();
    ContinuousMenu printMenu = new ContinuousMenu();

	public Swing() 
    {
        setSize(445, 160);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(1, 1, 15, 15));
        pane.add(watch);
        setContentPane(pane);
        setVisible(true);
    }

    public static void main(String[] arguments) {
    	Swing clock = new Swing();
    }
}

class ContinuousMenu implements Runnable{
	Thread runner; int test = 0;
	ContinuousMenu() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }
	public void run(){
		clock a = new clock(1);
		while(true){
			
			//Insert printMenu() call on while loop
			
			a.input();
			
			hhmmss_meridian_military.hh_ = a.hh;
			hhmmss_meridian_military.mm_ = a.mm;
			hhmmss_meridian_military.ss_ = a.ss;
			  
			  hhmmss_meridian_military.timeChanged = true;
		}
	}
}

//This class is used to communicate between ContinuousMenu thread and the WatchPanel thread
//ContinuousMenu takes input from the user to specify hh,mm,ss,AM or PM, or military time
//Those specified values are set in this class by ContinuosMenu which are then pulled from by WatchPanel.
//WatchPanel pulls the values then sets them into the clock.
class hhmmss_meridian_military
{
	public static int hh_ = 0;
	public static int mm_ = 0;
	public static int ss_ = 0;
	public static boolean AM_or_PM_ = false;
	public static boolean military_ = false;
	
	public static boolean timeChanged = false;
}


class WatchPanel extends JPanel implements Runnable {

	Thread runner;

    WatchPanel() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
        }
    }
    
    clock swingClock = new clock();
    
    @Override
    public void paintComponent(Graphics g) 
	 {
    	if(hhmmss_meridian_military.timeChanged)
    	{
	    	swingClock.sethh(hhmmss_meridian_military.hh_);
	    	swingClock.setmm(hhmmss_meridian_military.mm_);
	    	swingClock.setss(hhmmss_meridian_military.ss_);
	    	swingClock.settimeDifference();
	    	
	    	hhmmss_meridian_military.timeChanged = false;
    	}

    	
    	Font myFont = new Font("Georgia", Font.BOLD, 46);
        g.setFont(myFont);
    	if(swingClock.getReadyFlag())
    	{
 	        g.setColor(getBackground());
 	        g.fillRect(0, 0, getSize().width, getSize().height);
            swingClock.setTime();
            String time = swingClock.getTime();
            g.setColor(Color.black);
            g.drawString(time, 65, 66);
            	
    	}
       
         
      }
}
