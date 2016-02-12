import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

//How to import timing library taken from: http://stackoverflow.com/questions/12105164/java-joda-time-download-and-install-step-by-step

public class clock // this is Clock class
{
 //constructor method
 public clock()
 {
  hh = -1; 
  mm = -1; 
  ss = -1; 
  AM_or_PM = null;
  input();
  //printMenu();
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

 //hh is hours, mm is minutes, ss is seconds, AM_or_PM is AM or PM
 int hh;
 int mm;
 int ss;
 String AM_or_PM;
 boolean militaryTime = false;
 String Num_S = "";
 
 //This method will take the user specified input for hh,mm,ss,AM.
 //I assume this method is only temporary and will be changed as we employ a GUI.
 //Note there is no exception handling for this method yet so giving non-numerical input for hh,mm, or ss crashes the program.
 private void input() 
 {
  // TODO Auto-generated method stub
  Scanner input = new Scanner(System.in);

  int temp = -1;
  System.out.println("Choose: 1.) military time(24 hour)");
  System.out.print("        2.) Non-military time(12 hour): ");
  while(temp < 1 || temp > 2)
  {
	    Num_S = input.nextLine();
	   while (!Num_S.matches("\\d+$")) //from: http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
	  {
		 System.out.print("not valid number. try again: ");
		 Num_S = input.nextLine();
	  }
	   temp = Integer.parseInt(Num_S);
	  //temp = input.nextInt();
	  if(temp < 1 || temp > 2)
	  {
		  System.out.println("Choose: 1.) military time(24 hour)");
		  System.out.print("        2.) Non-military time(12 hour): ");
	  }
	  if(temp == 1)
	  {
		  militaryTime = true;
	  }
	  if(temp == 2)
	  {
		  militaryTime = false;
	  }
  }
  
  System.out.print("Insert hh:");
  Num_S = input.nextLine();
  hh = -4;
  while(hh < 1 || hh > 12)
  {
	  
	  while (!Num_S.matches("\\d+$")) //from: http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
	  {
		 System.out.print("not valid number. try again: ");
		 Num_S = input.nextLine();
	  }
	   hh = Integer.parseInt(Num_S);
   //hh = input.nextInt();
	 
   if(hh < 1 || hh > 12)
   {
    System.out.print("hh must be between 1-12, try again:");
   }
   
  }
  System.out.print("Insert mm:");
  mm = -4;
  Num_S = input.nextLine();
  while(mm < 0 || mm > 59)
  {
   //mm = input.nextInt();
	  
	  while (!Num_S.matches("\\d+$")) //from: http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
	  {
		 System.out.print("not valid number. try again: ");
		 Num_S = input.nextLine();
	  }
	   mm = Integer.parseInt(Num_S);
   if(mm < 0 || mm > 59)
   {
    System.out.print("mm must be between 0-59, try again:");
   }
  }
  System.out.print("Insert ss:");
  ss = -4;
  Num_S = input.nextLine();
  while(ss < 0 || ss > 59)
  {
   //ss = input.nextInt();
	  
	  while (!Num_S.matches("\\d+$")) //from: http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
	  {
		 System.out.print("not valid number. try again: ");
		 Num_S = input.nextLine();
	  }
	   ss = Integer.parseInt(Num_S);
   if(ss < 0 || ss > 59)
   {
    System.out.print("ss must be between 0-59, try again:");
   }
  }
  if (militaryTime == false)
  {
  System.out.print("Insert AM or PM:");
  AM_or_PM = input.nextLine();
  while(!AM_or_PM.equalsIgnoreCase("AM") && !AM_or_PM.equalsIgnoreCase("PM"))
  {
   AM_or_PM = input.nextLine();
   if(!AM_or_PM.equalsIgnoreCase("AM") && !AM_or_PM.equalsIgnoreCase("PM"))
   {
    System.out.print("AM or PM, try again:");
   }
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
 private void printClock() throws InterruptedException, IOException 
 { 
  // Convert hh to 24 hour equiv which will then be used to calculate the milliseconds of the day from midnight
  int HH = hh;

  if(AM_or_PM.equalsIgnoreCase("PM") && HH != 12)//if PM add 12 hours except if it is 12 PM
  { HH += 12; }
  else if(AM_or_PM.equalsIgnoreCase("AM") && HH == 12)//if 12 AM, it is 0 hours from midnight
  { HH = 0; }
  
  // Find the difference in milliseconds between user specified time and actual time
  int timeDifference = (HH * 3600 + mm * 60 + ss) * 1000 - DateTime.now().getMillisOfDay();

  
  
  System.out.println("---------------printClock()---------------");
  
  
  // Format for input
  DateTimeFormatter dtf = DateTimeFormat.forPattern("mm/dd/yyyy hh:mm:ss a");
  
  // Print until the program stops
  while(true)
  {
   // Parsing the date
   DateTime jodatime = dtf.parseDateTime(dtf.print(DateTime.now().plusMillis(timeDifference)));
   // Format for output
   DateTimeFormatter dtfOut = DateTimeFormat.forPattern("hh:mm:ss a");
   // Printing the date
   System.out.println(dtfOut.print(jodatime));
   Thread.sleep(700); //This line is put in to prevent the terminal from getting spammed.
  }
  
 }

 
 //Added code
 //changes to 24hour format
 //i changed the code in the printClock() a little.
 //the menu so far. i'm not sure if i should include anything else.
 public void printMenu()
 {
	 Scanner input = new Scanner(System.in);
	 String userChoice = "";
	 
	 do 
	 {
		 System.out.println("1) To switch time format from AM/PM to military, enter 'switch'.");
		 System.out.println("2) To change the time, enter 'time'");
		 System.out.println("3) To exit, enter 'exit.'");
		 userChoice = input.nextLine();
		 
		 if (userChoice.equalsIgnoreCase("switch"))
		 {
			 if (militaryTime == true)
			 {	System.out.println("You are now in military time.");
				 militaryTime = false;	}
			 else
			 {	 System.out.println("You are now in AM/PM format.");
				 militaryTime = true;	}
			 
			 //changing the time format
			 if (militaryTime == true)
			 {
				 if (AM_or_PM.equalsIgnoreCase("PM"))
				 {	hh += 12; }
			 }
			 else //if militaryTime == false
			 {
				if (AM_or_PM.equalsIgnoreCase("PM"))
				{
					hh -= 12;
				}
			 }
		 }
		 else if (userChoice.equalsIgnoreCase("time"))
		 {
			 //input();
			 //changes time
			 //jero test push
	
			 if (militaryTime == true)
			 {
				 System.out.println("Please insert in military time(24 hour)!");
				 System.out.print("Insert hh:");
				 hh = -4;
				  while(hh < 0 || hh > 23)
				  {
					  Num_S = input.nextLine();
					  while (!Num_S.matches("\\d+$")) //from: http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
					  {
						 System.out.print("not valid number. try again: ");
						 Num_S = input.nextLine();
					  }
					   hh = Integer.parseInt(Num_S);
					  //hh = input.nextInt();
					  if(hh < 0 || hh > 23)
					  {
						  System.out.print("hh must be between 0-23, try again:");
					  }
				  }
				  System.out.print("Insert mm:");
				  mm = -4;
				  while(mm < 0 || mm > 59)
				  {
					//  mm = input.nextInt();
					  Num_S = input.nextLine();
					  while (!Num_S.matches("\\d+$")) //from: http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
					  {
						 System.out.print("not valid number. try again: ");
						 Num_S = input.nextLine();
					  }
					   mm = Integer.parseInt(Num_S);
					  if(mm < 0 || mm > 59)
					  {
						  System.out.print("mm must be between 0-59, try again:");
					  }
				  }
				  ss = 0;
			 }
			 else
			 {
				 System.out.println("Please insert in non-military time(12 hour)!");
				 System.out.print("Insert hh:");
				 hh = -4;
				 while(hh < 1 || hh > 12)
				 {
					 //hh = input.nextInt();
					  Num_S = input.nextLine();
					  while (!Num_S.matches("\\d+$")) //from: http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
					  {
						 System.out.print("not valid number. try again: ");
						 Num_S = input.nextLine();
					  }
					   hh = Integer.parseInt(Num_S);
					 if(hh < 1 || hh > 12)
					 {
						 System.out.print("hh must be between 1-12, try again:");
					 }
				 }
				 System.out.print("Insert mm:");
				 mm = -4;
				 while(mm < 0 || mm > 59)
				 {
					 //mm = input.nextInt();
					  Num_S = input.nextLine();
					  while (!Num_S.matches("\\d+$")) //from: http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
					  {
						 System.out.print("not valid number. try again: ");
						 Num_S = input.nextLine();
					  }
					   mm = Integer.parseInt(Num_S);
					 if(mm < 0 || mm > 59)
					 {
						 System.out.print("mm must be between 0-59, try again:");
					 }
				 }
				 ss = 0;
				 //System.out.print("Insert AM or PM:");
				 System.out.print("Insert AM or PM:");
				  AM_or_PM = input.nextLine();
				  while(!AM_or_PM.equalsIgnoreCase("AM") && !AM_or_PM.equalsIgnoreCase("PM"))
				  {
				    System.out.print("AM or PM, try again:");
				    AM_or_PM = input.nextLine();
				  }
			 }
		 }
		 else
		 {
		 }
	 } while (!userChoice.equalsIgnoreCase("exit"));
	 input.close();
 }
}
 
