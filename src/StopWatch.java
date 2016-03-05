
/*
** EECS 448 Project #2
** Authors: Sri Gayatri Sundar
** Last update: March 5, 2016.
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
	JButton start,stop,reset;
	
	//stopwatch variables
	int ticks;  	//number of clock ticks
	int min;
	int sec;
	double time_in_sec;  	//ticks is incremented every milli second and is divided by 10 to give a decimal number. this decimal is the seconds passed after start
	
	Font myfont;
	Timer stopwatch; //instance of timer class
	

	
	public StopWatch()
	{
		min=0;
		sec=0;
		ticks = 0;  		//initial clock setting in ticks
		time_in_sec = ((double)ticks)/10.0;
		
		myfont = new Font("Serif", Font.PLAIN, 50);
		
		//Creating buttons
		start=new JButton("Start");
		stop=new JButton("Pause");
		reset=new JButton("Reset");
		
		//setting the label
		time = new JLabel();
		time.setFont(myfont);
		time.setText(min + " : "+ sec + " : " + ticks);
		
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
		
		//Reset button
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
		
	}//constructor ends here

	public void myFrame()
	{
		
		//created frame and set dimension
		frame=new JFrame("Stopwatch");
		frame.setVisible(true);
		frame.setSize(500, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		panel1.add(label,BorderLayout.SOUTH);
		
		//adding to panel2
		panel2.add(time);
	
		//adding to panel3
		panel3.add(start);
		panel3.add(stop);
		panel3.add(reset);
	}	
	
	//Main function
	public static void main(String[] args)
	{
		StopWatch myStopWatch;
		myStopWatch = new StopWatch();
		myStopWatch.myFrame();
	}
	
}//class StopWatch ends here


