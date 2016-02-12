
//	EECS 448 -- CLOCK PROJECT
//	JAVA SWING SET - DIGITAL DISPLAY
//	Original author: VICTOR BERGER - KUID 2770737 - victorluiz@ku.edu


import java.awt.*;
import javax.swing.*;


public class Swing extends JFrame {
    WatchPanel watch = new WatchPanel();
   // Test a = new Test();

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
/*
class Test implements Runnable{
	Thread runner;
	Test() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }
	public void run(){
		while(true){
			System.out.println("WORKS");
		}
	}
}
*/
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

