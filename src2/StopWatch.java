package src2;

//AUTHOR: SRI GAYATRI

//importing libraries
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

//just named it swing for now
public class StopWatch extends JPanel
{
	//declare frame and panel variables
	JFrame frame;
	JPanel panel1, panel2, panel3;
	JLabel label,time;
	JButton start,stop,reset;
	
	//delcare stopwatch variables
	int ticks;  	//number of clock ticks; tick can be 1.0 s or 0.1 s
	int min;
	int sec;
	double time_in_sec;  	//time in seconds
	String time_string;
	Font myfont;
	Timer stopwatch;
	
	/*
	long startTime;
	int ms,s,m,h;
	boolean isRunning;
	ms=00;
	s=00;
	m=00;
	h=00;
	isRunning = false;
	*/
	
	public Swing()
	{
		min=0;
		sec=0;
		ticks = 0;  		//initial clock setting in ticks
		time_in_sec = ((double)ticks)/10.0;
		time_string = new Double(time_in_sec).toString();
		
		myfont = new Font("Serif", Font.PLAIN, 50);
		
		//Creating buttons
		start=new JButton("Start");
		stop=new JButton("Pause");
		reset=new JButton("Reset");
		
		//setting the label
		time = new JLabel();
		time.setFont(myfont);
		time.setText(time_string);
		
		stopwatch = new Timer(100, new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				ticks++;
				time_in_sec = ((double)ticks)/10.0;
				time_string = new Double(time_in_sec).toString();
				time.setText(min + " : "+ sec + " : " + ticks);
				if(ticks==9)
				{
					ticks=0;
					sec++;
					time.setText(min + " : "+ sec + " : " + ticks);
				}
				if(time_in_sec==60.0)
				{
					min++;
					ticks=0;
					time_in_sec = ((double)ticks)/10.0;
					time.setText(min + " : "+ sec + " : " + ticks);
				}
			 }
			});

		
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				
				/*isRunning = true;
				while (true)
				{
			        startTime = System.nanoTime();
			        while (isRunning) 
			        {
			            if (System.nanoTime() - startTime >= 10000000) 
			            {
			                startTime = System.nanoTime();
			                ms++;
			                if (ms == 100) 
			                {
			                    s++;
			                    ms = 0;
			                    if (s == 60) 
			                    {
			                        m++;
			                        s = 0;
			                        if (m == 60) 
			                        {
			                            h++;
			                            m = 0;
			                        }
			                    }
			                }
			                
			                time.setText(h+" : "+m+" : "+s+" : "+ms);
			                
			            }
			        }
				}*/
				stopwatch.start();
				
			}
		});
		
		
		stop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				 //isRunning = false;
				stopwatch.stop();
			}
		});

		reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				/* ms = 0;
			     s = 0;
			     m = 0;
			     h = 0;
				time.setText(h+" : "+m+" : "+s+" : "+ms);*/
				ticks=0;
				min=0;
				sec=0;
				time_in_sec = ((double)ticks)/10.0;
				time_string = new Double(time_in_sec).toString();
				time.setText(min + " : "+ sec + " : " + ticks);
				/*if(ticks==10)
				{
					ticks=0;
					sec++;
					time.setText(min + ":"+ sec + ":" + ticks);
				}
				if(time_in_sec==60.0)
				{
					min++;
					ticks=0;
					time_in_sec = ((double)ticks)/10.0;
					time.setText(min + ":"+ sec + ":" + ticks);
				}*/
			}
		});
		
	}//constructor ends here
/*public void run()
{
	while (true)
	{
        startTime = System.nanoTime();
        while (isRunning) 
        {
            if (System.nanoTime() - startTime >= 10000000) 
            {
                startTime = System.nanoTime();
                ms++;
                if (ms == 100) 
                {
                    s++;
                    ms = 0;
                    if (s == 60) 
                    {
                        m++;
                        s = 0;
                        if (m == 60) 
                        {
                            h++;
                            m = 0;
                        }
                    }
                }
                
                time.setText(h+" : "+m+" : "+s+" : "+ms);
                
            }
        }
	}
}*/
	public void myFrame()
	{
		
		//created frame and set dimension
		frame=new JFrame("Stopwatch");
		frame.setVisible(true);
		frame.setSize(445, 160);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//created 3 panels and set backgrounds
		panel1= new JPanel();
		panel2= new JPanel();
		panel3= new JPanel();
		panel1.setBackground(Color.yellow);
		panel3.setBackground(Color.green);
		
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
	}	
	
	public static void main(String[] args)
	{
		Swing myStopWatch;
		myStopWatch = new Swing();
		myStopWatch.myFrame();
	}
	
}//class swing ends here


