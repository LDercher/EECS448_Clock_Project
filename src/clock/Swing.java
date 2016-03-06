package clock;

import java.awt.*;
import clock.Calendar.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;  
import java.awt.image.BufferedImage;  
import java.awt.image.DataBuffer;  
import java.awt.image.DataBufferInt;  
import java.awt.image.WritableRaster;  
import javax.swing.ImageIcon;  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;


/**
 * 
 * @author Victor Berger, Jesse Yang, Jeromy Tsai, and Cammy Vo
 * EECS 448 - Clock Project
 * prof: John Gibbons
 * code freeze: February 14th, 2016 11:59 PM
 * 
 */

/**
 * @param Class
 *            'Swing' is used to generate and control the Java Swing Set
 *            functionality of the program.
 * 
 */
public class Swing extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	WatchPanel watch = new WatchPanel();
	//ConsoleMenu printMenu = new ConsoleMenu();
	JPanel pane = new JPanel();

	/**
	 * Constructor for the 'Swing' class. Launches clock window, with clock
	 * originally set at 12:00:00 AM. Will run for entire duration of program.
	 * Includes many simple Java Swing Set look&feel commands.
	 * 
	 * @pre -
	 * @post -
	 * 
	 */
	public Swing() {
		
		super("EECS448 - Clock Project");
		
		setSize(1494, 473);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		pane.setLayout(new GridLayout(1, 1, 15, 15));
		
		pane.add(watch);
		
		setContentPane(pane);
		
		setVisible(true);
	}

	/**
	 * Main method. Initializes 'clock' object used to run the clock counter
	 * 
	 */
	public static void main(String[] arguments) {
		@SuppressWarnings("unused")
		Swing clock = new Swing();
	}
}

/**
 * 
 * Class 'ConsoleMenu' implements the thread used to have the console menu
 * continually running and able to accept real-time input.
 *
 * All classes that implement the 'Runnable' interface were based on:
 * @see http://workbench.cadenhead.org/book/21java/source/day13/DigitalClock.java
 *
 */
/*
class ConsoleMenu implements Runnable {
	Thread runner;
	int test = 0;

	ConsoleMenu() {
		if (runner == null) {
			runner = new Thread(this);
			runner.start();
		}
	}

	/**
	 * Continuously takes clock input from user and makes the necessary changes on time set
	 * and format
	 * 
	 * @pre continuously called by thread
	 * @post time set and format get updated
	 * @return void
	 *//*
	public void run() {
		clock a = new clock(1, hhmmss_meridian_military.hh_,
				hhmmss_meridian_military.mm_, hhmmss_meridian_military.ss_);
		while (true) {

			//a.input();

			hhmmss_meridian_military.hh_ = a.hh;
			hhmmss_meridian_military.mm_ = a.mm;
			hhmmss_meridian_military.ss_ = a.ss;

			//if (a.AM_or_PM.equals("PM"))
				//hhmmss_meridian_military.AM_or_PM_ = true;
			//else
				hhmmss_meridian_military.AM_or_PM_ = false;

			hhmmss_meridian_military.military_ = a.militaryTime;

			hhmmss_meridian_military.timeChanged = true;

			hhmmss_meridian_military.changetimeDifference = a.changetimeDifference;
		}
	}
}*/

/**
 * The 'hhmmss_meridian_military' class is used to communicate between
 * ConsoleMenu thread and the WatchPanel thread ConsoleMenu takes input from the
 * user to specify hh, mm, ss, AM or PM, or military time Those specified values
 * are set in this class by ContinuosMenu which are then pulled from by
 * WatchPanel. WatchPanel pulls the values then sets them into the clock.
 *//*

class hhmmss_meridian_military {
	public static int hh_ = 0;
	public static int mm_ = 0;
	public static int ss_ = 0;
	public static boolean AM_or_PM_ = false; // AM == false, PM == true
	public static boolean military_ = false;
	public static boolean timeChanged = false;
	public static boolean changetimeDifference = false;
}*/

/**
 * The "WatchPanel" class implements 'thread runner' to set the time values into the clock
 * 
 * Based on:
 * @see http://workbench.cadenhead.org/book/21java/source/day13/DigitalClock.java * 
 */
class WatchPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	protected static final MONTH December = null;
	Thread runner;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	Font myFont = new Font("Georgia", Font.BOLD, 45);	
	int flag=2;
	Calendar lCalendar = new Calendar(1, MONTH.January, 2016);
	clock swingClock = new clock(); // initializes swingClock object of type
	// 'clock' used in the 'paintComponent'
	// method
	

	
	WatchPanel()
	{
		if (runner == null) {
			runner = new Thread(this);
			runner.start();
		}
		
		//LDercher created the buttons
		
		//HOURS INC/DEC
				JButton hours_minus = new JButton("-");
				hours_minus.addActionListener(new ActionListener() 
				{ 
				    public void actionPerformed(ActionEvent e) 
				    { 
				    	swingClock.sethh(swingClock.gethh()-1);
				    	
				    } 
				});
				
				JButton hours_plus = new JButton("+");
				hours_plus.addActionListener(new ActionListener() 
				{ 
				    public void actionPerformed(ActionEvent e) 
				    { 
				    	swingClock.sethh(swingClock.gethh()+1);
				    } 
				});
				
				hours_plus.setAlignmentX(150);
				hours_plus.setAlignmentY(500);
				
			//MINUTES INC/DEC
				JButton minutes_plus = new JButton("+");
				minutes_plus.addActionListener(new ActionListener() 
				{ 
				    public void actionPerformed(ActionEvent e)
				    {	
				    	
				    	swingClock.setmm(swingClock.getmm()+1);
				       // add function to increment m
				    } 
				});
				
				JButton minutes_minus = new JButton("-");
				minutes_minus.addActionListener(new ActionListener() 
				{ 
				    public void actionPerformed(ActionEvent e) 
				    { 
				    	swingClock.setmm(swingClock.getmm()-1);
				       // add function to decrement m
				    } 
				});
				
				
				//SECONDS INC/DEC
				JButton seconds_plus = new JButton("+");
				seconds_plus.addActionListener(new ActionListener() 
				{ 
				    public void actionPerformed(ActionEvent e) 
				    { 
				       // add function to increment seconds
				    	swingClock.setss(swingClock.getss()+1);
				    } 
				});
				
				JButton seconds_minus = new JButton("-");
				seconds_minus.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						//add function to decrement seconds
						swingClock.setss(swingClock.getss()-1);
					}
				});
				//MONTHS INC/DEC
				JButton months_plus = new JButton("+");
				months_plus.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
					
						lCalendar.incrementMonth();
						
					}
				});
				
				JButton months_minus = new JButton("-");
				months_minus.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						lCalendar.decrementMonth();
					}
						
				});
				
				//DAYS INC/DEC
				JButton days_plus = new JButton("+");
				days_plus.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						//add function to increment days
						lCalendar.incrementDay();
					}
				});
				
				JButton days_minus = new JButton("-");
				days_minus.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						//add function to decrement days
						lCalendar.decrementDay();
						
					}
				});
				
				
				JButton twelveHR = new JButton("12 hr");
				twelveHR.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						swingClock.switchClockMode(false);
						//add function to switch to 12hr mode
					}
				});
				
				JButton twentyFourHR = new JButton("24 hr");
				twentyFourHR.addActionListener(new ActionListener() 
				{	
					public void actionPerformed(ActionEvent arg0) 
					{
						swingClock.switchClockMode(true);
						//add function to switch to 24hr mode
					}
				});
				
				ImageIcon timerButton = new ImageIcon(getClass().getResource("../images-button/timer_thumbnail.jpg"));  
				JButton btnTimer = new JButton(timerButton);  
				//JButton btnTimer = new JButton("timer");  

				btnTimer.addActionListener(new ActionListener() 
				{	
					public void actionPerformed(ActionEvent arg0) 
					{
						TimerFrame frm = new TimerFrame("Timer");
					    frm.setSize( 400, 200 );     
					    frm.setVisible( true ); 
						//add function to switch to timer mode
					}
				});
				
				ImageIcon stopWatchButton = new ImageIcon(getClass().getResource("../images-button/stop-watch_thumbnail.png"));  
				JButton btnStopwatch = new JButton(stopWatchButton);  
				//JButton btnStopwatch = new JButton("stopwatch");  

				btnStopwatch.addActionListener(new ActionListener() 
				{	
					public void actionPerformed(ActionEvent arg0) 
					{
						StopWatch myStopWatch;
						myStopWatch = new StopWatch();
						myStopWatch.myFrame();
						//add function to switch to stopwatch mode
					}
				});
				JButton zoomin=new JButton("ZOOM IN");
				zoomin.addActionListener(new ActionListener() 
				{	
					public void actionPerformed(ActionEvent arg0) 
					{
						if(flag==1){
							myFont = new Font("Georgia", Font.BOLD, 45);
							flag=2;
						}
						else if(flag==2){
							myFont = new Font("Georgia", Font.BOLD, 60);
							flag=3;}
						
						
					}
				});
				JButton zoomout=new JButton("ZOOM OUT");
				zoomout.addActionListener(new ActionListener() 
				{	
					public void actionPerformed(ActionEvent arg0) 
					{
						if(flag==3){
							myFont = new Font("Georgia", Font.BOLD, 45);
							flag=2;
						}
						else{
							myFont = new Font("Georgia", Font.BOLD, 30);	
							flag=1;}
						
					}
				});
				
				
			
				
				JButton btnReset = new JButton("reset");
				GroupLayout groupLayout = new GroupLayout(this);
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(115)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(hours_minus)
										.addComponent(hours_plus))
									.addGap(34)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(minutes_plus)
										.addComponent(minutes_minus))
									.addGap(31)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(seconds_minus)
										.addComponent(seconds_plus)))
								.addGap(100)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnStopwatch)
												.addComponent(twentyFourHR))
								.addGap(100)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(twelveHR)
										.addComponent(btnTimer))
									.addGap(310)
									.addComponent(zoomin)
									.addGap(34)
									.addComponent(zoomout)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(months_plus)
											.addComponent(months_minus))
										.addGap(20)	
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(days_plus)
										.addComponent(days_minus))	
									.addGap(66)
									))
							.addGap(145))
				);
				groupLayout.setVerticalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(seconds_plus)
								.addComponent(minutes_plus)
								.addComponent(hours_plus)
								.addComponent(months_plus)
								.addComponent(days_plus))
							.addGap(57)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(seconds_minus)
								.addComponent(minutes_minus)
								.addComponent(hours_minus)
								.addComponent(days_minus)
								.addComponent(months_minus))
							.addGap(43)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(twelveHR)
								.addComponent(twentyFourHR))
							.addGap(45)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnTimer)
								.addComponent(btnStopwatch)
								.addComponent(zoomin)
								.addComponent(zoomout))				
							.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE))
							.addGap(34))
				);
				this.setLayout(groupLayout);

				
			
	}


	/**
	* @post implements while(true) loop that generates thread
	* @return void
	*/
	public void run() {
		int Counter = 0;
		while (true) 
		{
			if(Counter%10==0)
			{
				this.swingClock.setss(this.swingClock.getss()+1);
			}
			
			repaint();
			try {
				Thread.sleep(100);
				Counter++;
			} catch (InterruptedException e) {
			}
		}
	}

	
	/**
	 * @pre called by the swing window constructor
	 * @return void
	 * @post sets 'paint' configurations of swing window, including parameters such as
	 * color and font. Receives time parameter and updates the swing window.
	 */
	@Override
	public void paintComponent(Graphics g) {
		
		/*
		if (hhmmss_meridian_military.timeChanged) {
			//swingClock.setReadyFlag(true);
			
			
			swingClock.sethh(hhmmss_meridian_military.hh_);
			swingClock.setmm(hhmmss_meridian_military.mm_);
			swingClock.setss(hhmmss_meridian_military.ss_);
		
			if (hhmmss_meridian_military.AM_or_PM_ == true)
				swingClock.AM_or_PM = "PM";
			else
				swingClock.AM_or_PM = "AM";

			swingClock.militaryTime = hhmmss_meridian_military.military_;

			if (hhmmss_meridian_military.changetimeDifference)
				swingClock.settimeDifference();

			hhmmss_meridian_military.timeChanged = false;
		}
		*/
		
		
		g.setFont(myFont);
		
		if (swingClock.getReadyFlag()) 
		{
			
			g.setColor(getBackground());
			
			g.fillRect(0, 0, getSize().width, getSize().height);
			
			swingClock.setTime();
			
			String time = swingClock.getTime();
			
			int day = lCalendar.getDayOfMonth();
			
			String dayOfWeek = lCalendar.getDayOfWeek().toString();
			
			int month = lCalendar.getMonthValue();
			
			int year = lCalendar.getYear();
			
			String date = String.format("%02d/%02d/%04d", month, day, year);
	
			
			if(!swingClock.militaryTime)
			{
				time = time + " " + swingClock.getAmPm().toString();
			}
			
			if(swingClock.mNextDayFlag)
			{
				lCalendar.incrementDay();
				swingClock.mNextDayFlag = false;
			}
			else if(swingClock.mLastDayFlag)
			{
				lCalendar.decrementDay();
				swingClock.mLastDayFlag = false;
			}
			g.setColor(Color.black);
			//paints the time string
			g.drawString(time, 100, 100);
			//paints day of week
			g.drawString(dayOfWeek, 600, 100);
			//paints date in frame
			g.drawString(date, 900, 100);
			
		

		}

	}
}
