package clock;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	ConsoleMenu printMenu = new ConsoleMenu();
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
		
		setSize(562, 454);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = new JPanel();
		
		pane.setLayout(new GridLayout(1, 1, 15, 15));
		
		pane.add(watch);
		
		//LDercher created the buttons
		
		JButton hours_minus = new JButton("-");
		hours_minus.addActionListener(new ActionListener() 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    { 
		       // add function to decrement h
		    } 
		});
		
		JButton hours_plus = new JButton("+");
		hours_plus.addActionListener(new ActionListener() 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    { 
		       // add function to increment h
		    } 
		});
		
		hours_plus.setAlignmentX(150);
		hours_plus.setAlignmentY(500);
		
		JButton minutes_plus = new JButton("+");
		minutes_plus.addActionListener(new ActionListener() 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    { 
		       // add function to increment m
		    } 
		});
		
		JButton minutes_minus = new JButton("-");
		minutes_minus.addActionListener(new ActionListener() 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    { 
		       // add function to decrement m
		    } 
		});
		
		JButton seconds_plus = new JButton("+");
		seconds_plus.addActionListener(new ActionListener() 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    { 
		       // add function to increment seconds
		    } 
		});
		
		JButton seconds_minus = new JButton("-");
		seconds_minus.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//add function to decrement seconds
			}
		});
		
		JButton twelveHR = new JButton("12 hr");
		twelveHR.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//add function to switch to 12hr mode
			}
		});
		
		JButton twentyFourHR = new JButton("24 hr");
		twentyFourHR.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent arg0) 
			{
				//add function to switch to 24hr mode
			}
		});
		
		JButton btnTimer = new JButton("timer");
		btnTimer.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent arg0) 
			{
				//add function to switch to timer mode
			}
		});
		
		JButton btnStopwatch = new JButton("stopwatch");
		btnStopwatch.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent arg0) 
			{
				//add function to switch to stopwatch mode
			}
		});
		
		JButton btnStartpause = new JButton("start/pause");
		btnStartpause.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			  //add function to start/stop stopwatch
			}
		});
		buttonGroup.add(btnStartpause);
	
		
		JButton btnReset = new JButton("reset");
		GroupLayout groupLayout = new GroupLayout(watch);
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
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(twelveHR)
								.addComponent(btnTimer)
								.addComponent(btnStartpause))
							.addGap(66)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnReset)
								.addComponent(btnStopwatch)
								.addComponent(twentyFourHR))))
					.addGap(145))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(seconds_plus)
						.addComponent(minutes_plus)
						.addComponent(hours_plus))
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(seconds_minus)
						.addComponent(minutes_minus)
						.addComponent(hours_minus))
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(twelveHR)
						.addComponent(twentyFourHR))
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTimer)
						.addComponent(btnStopwatch))
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStartpause)
						.addComponent(btnReset))
					.addGap(34))
		);
		watch.setLayout(groupLayout);

		
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
	 */
	public void run() {
		Clock a = new Clock(1, hhmmss_meridian_military.hh_,
				hhmmss_meridian_military.mm_, hhmmss_meridian_military.ss_);
		while (true) {

			a.input();

			hhmmss_meridian_military.hh_ = a.hh;
			hhmmss_meridian_military.mm_ = a.mm;
			hhmmss_meridian_military.ss_ = a.ss;

			if (a.AM_or_PM.equals("PM"))
				hhmmss_meridian_military.AM_or_PM_ = true;
			else
				hhmmss_meridian_military.AM_or_PM_ = false;

			hhmmss_meridian_military.military_ = a.militaryTime;

			hhmmss_meridian_military.timeChanged = true;

			hhmmss_meridian_military.changetimeDifference = a.changetimeDifference;
		}
	}
}

/**
 * The 'hhmmss_meridian_military' class is used to communicate between
 * ConsoleMenu thread and the WatchPanel thread ConsoleMenu takes input from the
 * user to specify hh, mm, ss, AM or PM, or military time Those specified values
 * are set in this class by ContinuosMenu which are then pulled from by
 * WatchPanel. WatchPanel pulls the values then sets them into the clock.
 */

class hhmmss_meridian_military {
	public static int hh_ = 0;
	public static int mm_ = 0;
	public static int ss_ = 0;
	public static boolean AM_or_PM_ = false; // AM == false, PM == true
	public static boolean military_ = false;
	public static boolean timeChanged = false;
	public static boolean changetimeDifference = false;
}

/**
 * The "WatchPanel" class implements 'thread runner' to set the time values into the clock
 * 
 * Based on:
 * @see http://workbench.cadenhead.org/book/21java/source/day13/DigitalClock.java * 
 */
class WatchPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	Thread runner;

	WatchPanel() {
		if (runner == null) {
			runner = new Thread(this);
			runner.start();
		}

	}

	/**
	* @post implements while(true) loop that generates thread
	* @return void
	*/
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	Clock swingClock = new Clock(); // initializes swingClock object of type
									// 'clock' used in the 'paintComponent'
									// method

	/**
	 * @pre called by the swing window constructor
	 * @return void
	 * @post sets 'paint' configurations of swing window, including parameters such as
	 * color and font. Receives time parameter and updates the swing window.
	 */
	@Override
	public void paintComponent(Graphics g) {
		if (hhmmss_meridian_military.timeChanged) {
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

		Font myFont = new Font("Georgia", Font.BOLD, 46);
		
		g.setFont(myFont);
		
		if (swingClock.getReadyFlag()) 
		{
			g.setColor(getBackground());
			
			g.fillRect(0, 0, getSize().width, getSize().height);
			
			swingClock.setTime();
			
			String time = swingClock.getTime();
			
			g.setColor(Color.black);
			
			g.drawString(time, 100, 100);			

		}

	}
}
