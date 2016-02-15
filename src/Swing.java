
//	EECS 448 -- CLOCK PROJECT
//	JAVA SWING SET - DIGITAL DISPLAY
//	Original author: VICTOR BERGER - KUID 2770737 - victorluiz@ku.edu


import java.awt.*;
import javax.swing.*;


public class Swing extends JFrame {
    WatchPanel watch = new WatchPanel();
    ConsoleMenu printMenu = new ConsoleMenu();

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

class ConsoleMenu implements Runnable{
	Thread runner; int test = 0;
	ConsoleMenu() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }
	public void run(){
		clock a = new clock(1, hhmmss_meridian_military.hh_,
		hhmmss_meridian_military.mm_,
		hhmmss_meridian_military.ss_);
		while(true){
			
			//Insert printMenu() call on while loop
			
			a.input();
			
			hhmmss_meridian_military.hh_ = a.hh;
			hhmmss_meridian_military.mm_ = a.mm;
			hhmmss_meridian_military.ss_ = a.ss;
			
			if(a.AM_or_PM.equals("PM"))
				hhmmss_meridian_military.AM_or_PM_ = true;
			else
				hhmmss_meridian_military.AM_or_PM_ = false;
			
			hhmmss_meridian_military.military_ = a.militaryTime;
			  
			hhmmss_meridian_military.timeChanged = true;
			
			hhmmss_meridian_military.changetimeDifference = a.changetimeDifference;
		}
	}
}

//This class is used to communicate between ConsoleMenu thread and the WatchPanel thread
//ConsoleMenu takes input from the user to specify hh,mm,ss,AM or PM, or military time
//Those specified values are set in this class by ContinuosMenu which are then pulled from by WatchPanel.
//WatchPanel pulls the values then sets them into the clock.
class hhmmss_meridian_military
{
	public static int hh_ = 0;
	public static int mm_ = 0;
	public static int ss_ = 0;
	public static boolean AM_or_PM_ = false;//AM == false, PM == true
	public static boolean military_ = false;
	
	public static boolean timeChanged = false;
	
	public static boolean changetimeDifference = false;
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
	    	
	    	if(hhmmss_meridian_military.AM_or_PM_ == true)
	    		swingClock.AM_or_PM = "PM";
			else
				swingClock.AM_or_PM = "AM";
	    	
	    	swingClock.militaryTime = hhmmss_meridian_military.military_;
	    	
	    	if(hhmmss_meridian_military.changetimeDifference)
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