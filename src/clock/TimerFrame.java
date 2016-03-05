package clock;

import java.applet.AudioClip;
import java.awt.*; 
import java.awt.event.*;
import java.text.NumberFormat;

import javax.swing.*;

import java.text.NumberFormat;
class TimerFrame extends JFrame implements ActionListener
{Timer timer;
AudioClip sound; 
//Reference to Buttons
JButton hour_up ;
  JButton min_up;
  JButton sec_up;
  JButton hour_down ; 
  JButton min_down;
  JButton sec_down; 
  JButton reset;
  JButton pause;
  JButton set_time;
 //Reference to label objects
  JLabel m_hour;
  JLabel m_min;
  JLabel m_sec;
  JLabel m_timer;
 // 
  int minute=0;
  int hour=0;
  int sec=0;
  NumberFormat format;
  long remain;
  long lastUpdate;


  TimerFrame(String title) 
  {
    super( title );                 
    setLayout( new GridLayout(0, 3) );    // set the layout Grid
    format = NumberFormat.getNumberInstance();
    format.setMinimumIntegerDigits(2);
    //Button Grid
    hour_up = new JButton("+");
    min_up=new JButton("+");
    sec_up=new JButton("+");
    set_time=new JButton("Start");
    //
    m_hour=new JLabel("		"+hour);
    m_min= new JLabel("		"+minute);
    m_sec= new JLabel("		"+sec);
    //
    hour_down = new JButton("-");
    min_down=new JButton("-");
    sec_down=new JButton("-");
    pause=new JButton("Pause");
    reset=new JButton("Reset");
    
 //Action Listeners   
    m_timer= new JLabel("00:00:00");
    timer = new Timer(1000, new ActionListener(){
    	public void actionPerformed(ActionEvent evt){
    		updateclock();
    	}
    });
    pause.addActionListener( new ActionListener(){
    	public void actionPerformed(ActionEvent evt){
    		  timer.stop();
    	
    	      repaint();  
    	  
    	}
    }); 
   
    
    // register the Frame object as the listener for the JButton.
    hour_up.addActionListener( new ActionListener(){
    	public void actionPerformed(ActionEvent evt){
    		  hour=hour+1;
    		  updateDisplay();
    	      repaint();  // ask the system to paint the screen.
    	  
    	}
    }); 
  
    
    
    
    reset.addActionListener( new ActionListener(){
    	public void actionPerformed(ActionEvent evt){
    		  hour=0;
    		  minute=0;
    		  sec=0;
    		  timer.stop();
    		  updateDisplay();
    	      repaint();  // ask the system to paint the screen.
    	  
    	}
    }); 
    min_up.addActionListener( new ActionListener(){
    	public void actionPerformed(ActionEvent evt){
    		  minute=minute+1;
    		  if(minute>=60)
    			  minute=0;
    		  updateDisplay();
    	      repaint();  // ask the system to paint the screen.
    	  
    	}
    }); 
    sec_up.addActionListener( new ActionListener(){
    	public void actionPerformed(ActionEvent evt){
    		  sec=sec+1;
    		  if(sec>=60)
    			  sec=0;
    		  updateDisplay();
    	      repaint();  // ask the system to paint the screen.
    	  
    	}
    }); 
    hour_down.addActionListener( new ActionListener(){
    	public void actionPerformed(ActionEvent evt){
    		  hour=hour-1;
    		  if(hour<=0)
    			  hour=0;
    		  updateDisplay();
    	      repaint();  // ask the system to paint the screen.
    	  
    	}
    }); 
    min_down.addActionListener( new ActionListener(){
    	public void actionPerformed(ActionEvent evt){
    		  minute=minute-1;
    		  if(minute<=0)
    			  minute=0;
    		  updateDisplay();
    	      repaint();  // ask the system to paint the screen.
    	  
    	}
    }); 
    sec_down.addActionListener( new ActionListener(){
    	public void actionPerformed(ActionEvent evt){
    		  sec=sec-1;
    		  if(sec<=0)
    			  sec=0;
    		  updateDisplay();
    	      repaint();  // ask the system to paint the screen.
    	  
    	}
    }); 
    set_time.addActionListener( new ActionListener(){
    	public void actionPerformed(ActionEvent evt){
    		 start();
    		
    	      repaint();  // ask the system to paint the screen.
    	  
    	}
    }); 
    
     // add the button to the JFrame
    add( hour_up );                  
    add(min_up);
    add(sec_up);
    
    add(m_hour);
    add(m_min);
    add(m_sec);
    
    add( hour_down );                  
    add(min_down);
    add(sec_down);
    add(set_time);
    add(pause);
    add(reset);
    
    add(m_timer);
    setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );   
  }
 
 
  
  //update Display
  void updateDisplay(){
	  m_hour.setText("		"+hour);;
	  m_min.setText("		"+minute);
	  m_sec.setText("		"+sec);
	  m_timer.setText(format.format(hour) + ":" +format.format(minute) + ":" + format.format(sec));
	  remain =hour*3600000+minute * 60000 + sec*1000;
   }
  
  /**
   * Starts Timer
   */
  
  public void start() {
	  lastUpdate = System.currentTimeMillis();
	  timer.start(); // Start the timer
  } 
/**
 * update timer values
 */
void updateclock(){ 
	
    

	long now = System.currentTimeMillis(); // current time in ms
    long elapsed = now - lastUpdate; // ms elapsed since last update
    remain -= elapsed; // adjust remain time
    lastUpdate = now; // remember this update time

    // Convert remain milliseconds to mm:ss format and display
    if (remain < 0)
      remain = 0;
    int hours=(int)(remain/3600000);
    int minutes = (int) (remain %3600000 / 60000);
    int seconds = (int) ((remain % 60000) / 1000);
    m_timer.setText(format.format(hours) + ":"+format.format(minutes) + ":" + format.format(seconds));

    // If we've completed the countdown beep and display new page
    if (remain == 0) {
    	//alarm sound
    	
      // Stop updating now.
      timer.stop();
      }
	
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
  
 
}

