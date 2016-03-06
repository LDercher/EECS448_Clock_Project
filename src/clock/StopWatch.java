package clock;

/*
** EECS 448 Project #2
** Authors: Sri Gayatri Sundar
** Last update: March 6, 2016.
*/

//importing libraries
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


public class StopWatch extends JPanel
{
	//declare frame and panel variables
	JFrame frame;
	JPanel panel1, panel2, panel3;
	JLabel label,time;
	JButton start,stop,reset,zoomin,zoomout;
	
	//delcare stopwatch variables
	int ticks;  	//number of clock ticks; tick can be 1.0 s or 0.1 s
	int min;
	int sec;
	double time_in_sec;  	//ticks is incremented every milli second and is divided by 10 to give a decimal number. this decimal is the seconds passed after start
	Font myfont1, myfont2, myfont3;
	Timer stopwatch; //instance of timer class
	
	public StopWatch()
	{
		min=0;
		sec=0;
		ticks = 0;  		//initial clock setting in ticks
		time_in_sec = ((double)ticks)/10.0;
	
		myfont2 = new Font("Serif", Font.PLAIN, 100);
		myfont1 = new Font("Serif", Font.PLAIN, 50);
		myfont3 = new Font("Serif", Font.PLAIN, 150);
		
		//Creating buttons
		start=new JButton("Start");
		stop=new JButton("Pause");
		reset=new JButton("Reset");
		zoomin=new JButton("+");
		zoomout=new JButton("-");
		
		//setting the label
		time = new JLabel();
		time.setFont(myfont2);

		
		//action listener
		stopwatch = new Timer(100, new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				ticks++;
				time_in_sec = ((double)ticks)/10.0;
				time.setText(min + " : "+ sec + " : " + ticks);
				
				//incrementing seconds
				if(ticks==9)
				{
					ticks=0;
					sec++;
					time.setText(min + " : "+ sec + " : " + ticks);
				}
				
				//incrementing minutes
				if(time_in_sec==60.0)
				{
					min++;
					ticks=0;
					time_in_sec = ((double)ticks)/10.0;
					time.setText(min + " : "+ sec + " : " + ticks);
				}
			 }
			});

		//Start Button
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				stopwatch.start();
			}
		});
		
		//Stop Button
		stop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				stopwatch.stop();
			}
		});
		
		//Reset Button
		reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
			
				ticks=0;
				min=0;
				sec=0;
				time_in_sec = ((double)ticks)/10.0;
				time.setText(min + " : "+ sec + " : " + ticks);
			}
		});
		
		//Zoom in button
		zoomin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				if(time.getFont()==myfont1)
					time.setFont(myfont2);
				else
					time.setFont(myfont3);
			}
		});
		
		//Zoom out button
				zoomout.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						if(time.getFont()==myfont3)
							time.setFont(myfont2);
						else
							time.setFont(myfont1);
					}
				});
				
	}//constructor ends here

	public void myFrame()
	{
		//created frame and set dimension
		frame=new JFrame("Stopwatch");
		frame.setVisible(true);
		frame.setSize(530, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//created 3 panels and set backgrounds
		panel1= new JPanel();
		panel2= new JPanel();
		panel3= new JPanel();
		panel1.setBackground(Color.WHITE);
		panel2.setBackground(Color.ORANGE);
		panel3.setBackground(Color.BLACK);
		
		//positioned the panels in frame
		frame.add(panel1,BorderLayout.NORTH);
		frame.add(panel2,BorderLayout.CENTER);
		frame.add(panel3,BorderLayout.SOUTH);
		
		//adding to panel1
		label = new JLabel("EECS 448 StopWatch");
		panel1.add(label);
		
		//adding to panel2
		panel2.add(time);
	
		//adding to panel3
		panel3.add(start);
		panel3.add(stop);
		panel3.add(reset);
		panel3.add(zoomin);
		panel3.add(zoomout);
	}	
	
	/*Main function
		public static void main(String[] args)
		{
			StopWatch myStopWatch;
			myStopWatch = new StopWatch();
			myStopWatch.myFrame();
		}*/
	
}//class swing ends here
