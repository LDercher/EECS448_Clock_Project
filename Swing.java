
/*

	EECS 448 -- CLOCK PROJECT
	JAVA SWING SET - DIGITAL DISPLAY
	VICTOR BERGER - KUID 2770737 - victorluiz@ku.edu

*/

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.*;
public class Swing3 extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] a) {
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.getAllFonts();
		
        JFrame f = new JFrame("EECS 448 - DIGITAL CLOCK");
        JFrame.setDefaultLookAndFeelDecorated(true);
        f.setContentPane(new Swing3());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400,150);
        f.setLocation(250,250);
        f.setVisible(true);
        
        f.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent e) {
              System.exit(0);
           }
        }
     );
         
     
  }
	
	 public void paint(Graphics g) 
	 {
         
            Font myFont = new Font("Georgia", Font.BOLD, 46);
            g.setFont(myFont);
            g.drawString("12 : 45 AM", 75, 66);
            
       
         
      }
	 
}
	


