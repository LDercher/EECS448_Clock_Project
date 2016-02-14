import java.io.IOException;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 
 * @author Jesse Yang
 * @author Cammy Vo 
 * @author Jeromy Tsai 
 * @author Victor Luiz
 * @see http://stackoverflow.com/questions/12105164/java-joda-time-download-and-install-step-by-step
 *
 */

/**
 * How to import timing library taken from: http://stackoverflow.com/questions/12105164/java-joda-time-download-and-install-step-by-step
 * Clock class
 */
public class clock
{
<<<<<<< HEAD
 /** constructor method*/
 public clock()
 {
  hh = -1; 
  mm = -1; 
  ss = -1; 
  AM_or_PM = "AM";
  input();
  timeString = "";
  
  try {
   printClock();
  } catch (InterruptedException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }

 }

 /**
  * hh is hours, mm is minutes, ss is seconds, AM_or_PM is AM or PM, militaryTime switches 12-24 hour format
  */
 int hh;
 int mm;
 int ss;
 String AM_or_PM;
 boolean militaryTime = false;
 String timeString;
 boolean readyFlag = false;
 int timeDifference;
 
 /** @return timeString*/
 public String getTime()
 {
	 return timeString;
 }
 
 /** set timeString variable*/
 public void setTime()
 {
	 DateTimeFormatter dtf = DateTimeFormat.forPattern("mm/dd/yyyy hh:mm:ss a");
	 /** Parsing the date*/
	 DateTime jodatime = dtf.parseDateTime(dtf.print(DateTime.now().plusMillis(timeDifference)));
	 /** Format for output*/
	 DateTimeFormatter dtfOut = DateTimeFormat.forPattern("hh:mm:ss a");
	 /** Printing the time*/
	 timeString = dtfOut.print(jodatime);
	 /** old code*/
	 //System.out.println(dtfOut.print(jodatime));
	 //Thread.sleep(700); //This line is put in to prevent the terminal from getting spammed.
 }
 
 public boolean getReadyFlag()
 {
	 return readyFlag;
 }
 /**
 //This method will take the user specified input for hh,mm,ss,AMorPM.
 //I assume this method is only temporary and will be changed as we employ a GUI.
 //Note there is no exception handling for this method yet so giving non-numerical input for hh,mm, or ss crashes the program.
 */
 private void input() 
 {
  // TODO Auto-generated method stub
  Scanner input = new Scanner(System.in);
  /** set hh*/
  System.out.print("Insert hh:");
  while(hh < 1 || hh > 12)
  {
   hh = input.nextInt();
   if(hh < 1 || hh > 12)
   {
    System.out.print("hh must be between 1-12, try again:");
   }
  }
  /** set mm*/
  System.out.print("Insert mm:");
  while(mm < 0 || mm > 59)
  {
   mm = input.nextInt();
   if(mm < 0 || mm > 59)
   {
    System.out.print("mm must be between 0-59, try again:");
   }
  }
  /** set ss*/
  System.out.print("Insert ss:");
  while(ss < 0 || ss > 59)
  {
   ss = input.nextInt();
   if(ss < 0 || ss > 59)
   {
    System.out.print("ss must be between 0-59, try again:");
   }
  }
 }
 /** set AM or PM*/
  public void AMorPM(){
  Scanner input = new Scanner(System.in);
  System.out.print("Insert AM or PM:");
  AM_or_PM = input.nextLine();
  while(!AM_or_PM.equals("AM") && !AM_or_PM.equals("PM"))
  {
   AM_or_PM = input.nextLine();
   if(!AM_or_PM.equals("AM") && !AM_or_PM.equals("PM"))
   {
    System.out.print("AM or PM, try again:");
   }
  }
  
  input.close();
  
 }
 

 //This is the important part of the class. This will print the Clock in hh:mm:ss XX format. 
 //It will also utilize the joda-time library. 
  /**
   * HOW TIME IS CALCULATED: timeDifference between actual time and user specified time is calculated.
   * This difference is used to find the user specified time on each iteration. 
   * @see http://kodejava.org/how-to-add-hours-minutes-seconds-into-datetime-in-joda-time/
   * @see http://stackoverflow.com/questions/20331163/how-to-format-joda-time-datetime-to-only-mm-dd-yyyy
   * @throws InterruptedException
   * @throws IOException
   */
 private void printClock() throws InterruptedException, IOException 
 { 
  // Convert hh to 24 hour equiv; used to calculate the milliseconds of the day
  int HH = hh;

  if(AM_or_PM.equals("PM") && HH != 12)//if PM add 12 hours except if it is 12 PM
  { HH += 12; }
   else if(AM_or_PM.equals("AM") && HH == 12)//if 12 AM, it is 0 hours from midnight
   { HH = 0; }
  
  /**Find the difference in milliseconds between user specified time and actual time*/
  timeDifference = (HH * 3600 + mm * 60 + ss) * 1000 - DateTime.now().getMillisOfDay();

  
  
  System.out.println("---------------printClock()---------------");
  
  
  // Format for input
  DateTimeFormatter dtf = DateTimeFormat.forPattern("mm/dd/yyyy hh:mm:ss a");
  
  // Print until the program stops
  readyFlag = true;
 /* while(true)
  {
   // Parsing the date
   DateTime jodatime = dtf.parseDateTime(dtf.print(DateTime.now().plusSeconds(timeDifference)));
   // Format for output
   DateTimeFormatter dtfOut = DateTimeFormat.forPattern("hh:mm:ss a");
   // Printing the time
   //System.out.println(dtfOut.print(jodatime));
   timeString = dtfOut.print(jodatime);
   Thread.sleep(700); //This line is put in to prevent the terminal from getting spammed.
  }*/
  
 }

 
 //Added code
 //changes to 24hour format
 //i changed the code in the printClock() a little.
 //the menu so far. i'm not sure if i should include anything else.
 /**
  * Prints the Menu 
  */
 public void printMenu()
 {
	 System.out.println("1) To switch time format from AM/PM to military, enter 'switch'.");
	 System.out.println("2) To change the time, enter 'time'");
	 System.out.println("3) To exit, enter 'exit.'");
	 
	 Scanner input = new Scanner(System.in);
	 String userChoice = input.nextLine();
	 
	 while (!userChoice.equalsIgnoreCase("exit"))
=======
	 //constructor method
	 public clock()
	 {
	  hh = -1; 
	  mm = -1; 
	  ss = -1; 
	  AM_or_PM = "AM";
	  timeString = "";
	  timeDifference = 0;
	  input();
	  
	  
	  try {
	   initializeClock();
	  } catch (InterruptedException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  } catch (IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	
	 }
	 public clock(int menu)
	 {
		 //pass an integer to skip input constructor; this is used to get menu output
	 }
	
	 //hh is hours, mm is minutes, ss is seconds, AM_or_PM is AM or PM
	 int hh;
	 int mm;
	 int ss;
	 String AM_or_PM;
	 boolean militaryTime = false;
	 String timeString;
	 boolean readyFlag = false;
	 int timeDifference;
 
	 public void setTime()
	 {
		 DateTimeFormatter dtf = DateTimeFormat.forPattern("mm/dd/yyyy hh:mm:ss a");
		// Parsing the date
		   DateTime jodatime = dtf.parseDateTime(dtf.print(DateTime.now().plusMillis(timeDifference)));
		   // Format for output
		   DateTimeFormatter dtfOut = DateTimeFormat.forPattern("hh:mm:ss a");
		   // Printing the time
		   //System.out.println(dtfOut.print(jodatime));
		   timeString = dtfOut.print(jodatime);
		   //Thread.sleep(700); //This line is put in to prevent the terminal from getting spammed.
	 }
	 
	 public String getTime()
	 {
		 return timeString;
	 }
	
	 public void sethh(int hh)
	 {
		 this.hh = hh;
	 }
	 
	 public void setmm(int mm)
	 {
		 this.mm = mm;
	 }
	 
	 public void setss(int ss)
	 {
		 this.ss = ss;
	 }
 
	 public void settimeDifference()
	 {
		// Convert hh to 24 hour equiv; used to calculate the milliseconds of the day
		  int HH = hh;
	
		  if(AM_or_PM.equals("PM") && HH != 12)//if PM add 12 hours except if it is 12 PM
		  { HH += 12; }
		   else if(AM_or_PM.equals("AM") && HH == 12)//if 12 AM, it is 0 hours from midnight
		   { HH = 0; }
		 timeDifference = (HH * 3600 + mm * 60 + ss) * 1000 - DateTime.now().getMillisOfDay();
	 }
 
	 public boolean getReadyFlag()
	 {
		 return readyFlag;
	 }
 
	 //This method will take the user specified input for hh,mm,ss,AM.
	 //I assume this method is only temporary and will be changed as we employ a GUI.
	 //Note there is no exception handling for this method yet so giving non-numerical input for hh,mm, or ss crashes the program.
	 public void input() 
	 {
	  // TODO Auto-generated method stub
	  Scanner input = new Scanner(System.in);
	
	  System.out.print("Insert hh:");hh = -1;
	  while(hh < 1 || hh > 12)
	  {
	   hh = input.nextInt();
	   if(hh < 1 || hh > 12)
	   {
	    System.out.print("hh must be between 1-12, try again:");
	   }
	  }
	  System.out.print("Insert mm:");mm = -1;
	  while(mm < 0 || mm > 59)
	  {
	   mm = input.nextInt();
	   if(mm < 0 || mm > 59)
	   {
	    System.out.print("mm must be between 0-59, try again:");
	   }
	  }
	  System.out.print("Insert ss:");ss = -1;
	  while(ss < 0 || ss > 59)
	  {
	   ss = input.nextInt();
	   if(ss < 0 || ss > 59)
	   {
	    System.out.print("ss must be between 0-59, try again:");
	   }
	  }
	  
	  
	  //input.close();
	 }
	  public void AMorPM(){
	  Scanner input = new Scanner(System.in);
	  System.out.print("Insert AM or PM:");
	  AM_or_PM = input.nextLine();
	  while(!AM_or_PM.equals("AM") && !AM_or_PM.equals("PM"))
	  {
	   AM_or_PM = input.nextLine();
	   if(!AM_or_PM.equals("AM") && !AM_or_PM.equals("PM"))
	   {
	    System.out.print("AM or PM, try again:");
	   }
	  }
	  
	  input.close();
	  
	 }
 

	 //This is the important part of the class. This will print the Clock in hh:mm:ss XX format. 
	 //It will also utilize the joda-time library. 
	 //
	 //HOW TIME IS CALCULATED: timeDifference between actual time and user specified time is calculated. 
	 //This difference is used to find the user specified time on each iteration.
	 //
	 
	 //Sources:
	 //http://kodejava.org/how-to-add-hours-minutes-seconds-into-datetime-in-joda-time/
	 //http://stackoverflow.com/questions/20331163/how-to-format-joda-time-datetime-to-only-mm-dd-yyyy
	 private void initializeClock() throws InterruptedException, IOException 
	 { 
	  settimeDifference();
	
	  System.out.println("---------------print clock---------------");
	  
	  readyFlag = true;
	  
	 }

 
	 //Added code
	 //changes to 24hour format
	 //i changed the code in the printClock() a little.
	 //the menu so far. i'm not sure if i should include anything else.
	 public void printMenu()
>>>>>>> a214d12767ca4d52c6cb2326933358e6790ce6d8
	 {
		 System.out.println("1) To switch time format from AM/PM to military, enter 'switch'.");
		 System.out.println("2) To change the time, enter 'time'");
		 System.out.println("3) To exit, enter 'exit.'");
		 
		 Scanner input = new Scanner(System.in);
		 String userChoice = input.nextLine();
		 
		 while (!userChoice.equalsIgnoreCase("exit"))
		 {
			 if (userChoice.equalsIgnoreCase("switch"))
			 {
				 if (militaryTime == true)
				 {	militaryTime = false;	}
				 else
				 {	 militaryTime = true;	}
				 
				 //changing the time format
				 if (militaryTime == true)
				 {
					 if (AM_or_PM.equalsIgnoreCase("PM"))
					 {	hh += 12; }
				 }
				 else
				 {
					AMorPM(); 
				 }
			 }
			 else if (userChoice.equalsIgnoreCase("time"))
			 {
				 //input();
				 //changes time
			 }
			 else
			 {
				 System.out.println("Not specified.");
			 }
		 }
		 input.close();
	 }
 
	 //catches things that aren't integers, ie. doubles/strings
	 //i'm not sure if it works yet
	 public boolean integerException(String userInput)
	 {
		 try
		 {
			 int check = Integer.parseInt(userInput);
			 return true;
		 }
		 catch (IndexOutOfBoundsException e)
		 {
			 return false;
		 }
	 }
<<<<<<< HEAD
	 input.close();
 }
 
 //catches things that aren't integers, ie. doubles/strings
 //i'm not sure if it works yet
 public boolean integerException(String userInput)
 {
	 try
	 {
		 int check = Integer.parseInt(userInput);
		 return true;
	 }
	 catch (IndexOutOfBoundsException e)
	 {
		 return false;
	 }
 }}
=======
 }
>>>>>>> a214d12767ca4d52c6cb2326933358e6790ce6d8
 
