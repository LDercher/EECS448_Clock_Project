package clock;

import java.io.IOException;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 
 * @author Victor Berger, Jesse Yang, Jeromy Tsai, and Cammy Vo
 * EECS 448 - Clock Project
 * prof: John Gibbons
 * code freeze: February 14th, 2016 11:59 PM
 * 
 * For information on the Joda time library:
 * @see http://stackoverflow.com/questions/12105164/java-joda-time-download-and-install-step-by-ste
 */

/**
 * How to import timing library taken from:
 * http://stackoverflow.com/questions/12105164/java-joda-time-download-and-install-step-by-step 
 * 
 */
public class clock {
	/**
	 * Constructor method for main Clock class.
	 * @throws InterruptedException, IOException
	 */
	
	/**
	 * hh is hours, mm is minutes, ss is seconds, AM_or_PM is AM or PM,
	 * militaryTime switches 12-24 hour format
	 */
	int hh;
	int mm;
	int ss;
	public enum_AM_PM AM_PM;
	boolean militaryTime = false;
	String Num_S = "";
	String timeString;
	boolean readyFlag = false;
	int timeDifference;
	boolean changetimeDifference = false;
	public boolean mNextDayFlag = false;
	public enum enum_AM_PM {AM, PM}
	
	public clock() {
		/**
		 * Sets initial values for time, format, and timeDifference variables
		 */

		hh = 12;
		mm = 0;
		ss = 0;
		AM_PM =enum_AM_PM.AM ;
		timeString = "";
		timeDifference = 0;

		try {
			initializeClock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public clock(int hh, int mm, int ss) {
		// pass an integer to skip input() call; this is used to get menu output
		
		
		this.hh = hh;
		this.mm = mm;
		this.ss = ss;
		AM_PM = enum_AM_PM.AM;
	
		
	}
	
	


	/** @return timeString */
	public String getTime() 
	{
		this.timeString = String.format("%02d:%02d:%02d", this.hh, this.mm, this.ss);
		return timeString;
	}

	/**
	 * set timeString variable
	 * 
	 * @return void
	 * @post Time input is parsed and implemented
	 * dead code?
	 */
	public void setTime() {
		DateTimeFormatter dtf = DateTimeFormat
				.forPattern("mm/dd/yyyy hh:mm:ss a");
		/** Parsing the date */
		
		DateTime jodatime = dtf.parseDateTime(dtf.print(DateTime.now()
				.plusMillis(timeDifference)));
		/** Format for output */
		DateTimeFormatter dtfOut;
		if (militaryTime == true) {
			dtfOut = DateTimeFormat.forPattern("HH:mm:ss");
		} else {
			dtfOut = DateTimeFormat.forPattern("hh:mm:ss a");
		}
		/** Printing the time */
		timeString = dtfOut.print(jodatime);

	}

	/**
	 * @param hh
	 * @return void Set method for 'hours' variable
	 */
	public void sethh(int hh)
	{
		int lMaxHour = 12;
		int lMinHour = 1;
		if(this.militaryTime)
		{
			lMaxHour = 23;
			lMinHour = 0;
		}
		if(hh > lMaxHour)
		{
			this.hh = lMinHour;
			if(this.militaryTime)
			{
				this.mNextDayFlag = true;
			}
		}
		else if(hh < lMinHour)
		{
			this.hh = lMaxHour;
		}
		else
		{
			this.hh = hh;
		}
		if(this.gethh() == 12 && !this.militaryTime)
		{
			this.switchAmPm();
			if(this.getAmPm() == enum_AM_PM.AM)
			{
				this.mNextDayFlag = true;
			}
		}
	}

	/**
	 * @return int (hh)
	 * @post Get method for 'hours' variable
	 */
	public int gethh() 
	{
		
		return hh;
	}

	/**
	 * @param mm
	 * @return void Set method for 'minutes' variable
	 */
	public void setmm(int mm)
	{
		if(mm==60)
		{
			this.mm=0;
			this.sethh(this.gethh()+1);
		}
		else if(mm<0)
		{
			this.mm = 59;
			this.sethh(this.gethh()-1);
		}
		else
		{
			this.mm = mm;
		}
	}

	/**
	 * @return int (mm)
	 * @post Get method for 'minutes' variable
	 */
	public int getmm() {
		return mm;
	}

	/**
	 * @param ss
	 * @return void Set method for 'seconds' variable
	 */
	public void setss(int ss) 
	{
		if(ss==60)
		{
			this.ss = 0;
			this.setmm(this.getmm()+1);
		}
		else if(ss<0)
		{
			this.ss = 59;
			this.setmm(this.getmm()-1);
		}
		else
		{
			this.ss = ss;
		}
		
	}

	/**
	 * @return int (ss)
	 * @post Get method for 'seconds' variable
	 */
	public int getss() {
		return ss;
	}
	
	public void setAmPm(enum_AM_PM aTimeOfDay)
	{
		this.AM_PM = aTimeOfDay;
	}
	public enum_AM_PM getAmPm()
	{
		return(this.AM_PM);
	}
	
	public void switchAmPm()
	{
		if(this.getAmPm()== enum_AM_PM.AM)
		{
			this.setAmPm(enum_AM_PM.PM);
		}
		else
		{
			this.setAmPm(enum_AM_PM.AM);
		}
	}
	/**
	 * 
	 * @param a24hours true if mode should be changed to 24 false if mode should be 12
	 */
	public void switchClockMode(boolean a24hours)
	{
		if(a24hours)
		{
			this.militaryTime = true;
			if((this.getAmPm() == enum_AM_PM.PM && this.gethh() != 12) || (this.getAmPm() == enum_AM_PM.AM && this.gethh() == 12))
			{
				this.sethh(this.gethh()+12);
				
			}
		}
		else
		{
			this.militaryTime = false;
			if(this.gethh()> 12)
			{
				this.sethh(this.gethh()-12);
				this.setAmPm(enum_AM_PM.PM);
			}
			else if(this.gethh() == 0)
			{
				this.sethh(12);
				this.setAmPm(enum_AM_PM.AM);
			}
			else
			{
				this.setAmPm(enum_AM_PM.AM);
			}
		}
	}
	
	
	public void setReadyFlag(boolean aFlag)
	{
		this.readyFlag = aFlag;
	}

	/**
	 * Convert hh to 24 hour equivalent; used to calculate the milliseconds of
	 * the day
	 * Dead code?
	 */
	/*public void settimeDifference() {

		
		System.out.println("The clocks hours: "+this.hh);
		/**
		 * if PM, add 12 hours except if it's 12 PM else-if: if 12 AM, it's 0
		 * hours from midnight
		 
		if (AM_or_PM.equals("PM") && hh != 12) {
			hh += 12;

		} else if (AM_or_PM.equals("AM") && hh == 12) {
			hh = 0;
		}

		// to calculate time difference between present time and input time
		timeDifference = (hh * 3600 + mm * 60 + ss) * 1000
				- DateTime.now().getMillisOfDay();
	}*/

	/**
	 * @pre 
	 * @return boolean
	 * @post Get method for 'seconds' variable
	 */
	public boolean getReadyFlag() {
		return readyFlag;
	}

	/**
	 * @return void 
	 * method created to design menu and receive input from user
	 * dead code?
	 */
	/*
	public void input() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		int temp = -1;

		System.out.println(" ");
		System.out.println("Choose:");
		System.out.println("1.) Military time(24 hour)");
		System.out.println("2.) Non-military time(12 hour) ");
		System.out.println("3.) Switch between AM/PM and military time formats");
		while (temp < 1 || temp > 3) {
			Num_S = input.nextLine();
			while (!Num_S.matches("\\d+$"))
			/**
			 * @see http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
			 *//*

			{
				System.out.print("Invalid number. Please try again: ");
				Num_S = input.nextLine();
			}
			temp = Integer.parseInt(Num_S);
			if (temp < 1 || temp > 3) {
				System.out.println("Choose:");
				System.out.println("1.) military time(24 hour)");
				System.out.println("2.) Non-military time(12 hour): ");
				System.out.println("3.) Switch between AM/PM and time formats");
			}
			if (temp == 1) {
				militaryTime = true;
			}
			if (temp == 2) {
				militaryTime = false;
			}
			if (temp == 3) {
				militaryTime = !militaryTime;
				changetimeDifference = false;
			}
		}
		/** set hh *//*

		if (temp == 1 || temp == 2) {
			hh = -1;
			mm = -1;
			ss = -1;
			System.out.print("Insert hh: ");
			if (militaryTime == true) {
				while (hh < 0 || hh > 23) {
					Num_S = input.nextLine();
					while (!Num_S.matches("\\d+$"))
					/**
					 * @see http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
					 *//*
					{
						System.out.print("Invalid number. Please try again: ");
						Num_S = input.nextLine();
					}
					hh = Integer.parseInt(Num_S);
					if (hh > 12) {
						AM_or_PM = "PM";
						hh -= 12;
					} else {
						AM_or_PM = "AM";
						if (hh == 0)
							hh = 12;
					}

					if (hh < 0 || hh > 23) {
						System.out.print("HH must be between 0-23, try again:");
					}
				}
			} else {
				while (hh < 1 || hh > 12) {
					Num_S = input.nextLine();
					while (!Num_S.matches("\\d+$")) 
					/**
					 * @see http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
					 *//*
					{
						System.out.print("Invalid number. Please try again: ");
						Num_S = input.nextLine();
					}
					hh = Integer.parseInt(Num_S);
					if (hh < 1 || hh > 12) {
						System.out.print("HH must be between 1-12, try again:");
					}
				}
			}

			/** set mm *//*
			System.out.print("Insert mm: ");
			while (mm < 0 || mm > 59) {
				Num_S = input.nextLine();
				while (!Num_S.matches("\\d+$")) 
				/**
				* @see http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
				*//*
				{
					System.out.print("Invalid number. Please try again: ");
					Num_S = input.nextLine();
				}
				mm = Integer.parseInt(Num_S);
				if (mm < 0 || mm > 59) {
					System.out.print("MM must be between 0-59, try again:");
				}
			}
			/** set ss *//*
			System.out.print("Insert ss: ");
			while (ss < 0 || ss > 59) {
				Num_S = input.nextLine();
				while (!Num_S.matches("\\d+$")) 
				/**
				* @see http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
				*//*
				{
					System.out.print("Invalid number. Please try again: ");
					Num_S = input.nextLine();
				}
				ss = Integer.parseInt(Num_S);
				if (ss < 0 || ss > 59) {
					System.out.print("SS must be between 0-59, try again:");
				}
			}
			if (militaryTime == false) {
				System.out.print("Insert AM or PM:");
				AM_or_PM = input.nextLine();
				while (!AM_or_PM.equalsIgnoreCase("AM")
						&& !AM_or_PM.equalsIgnoreCase("PM")) {
					AM_or_PM = input.nextLine();
					if (!AM_or_PM.equalsIgnoreCase("AM")
							&& !AM_or_PM.equalsIgnoreCase("PM")) {
						System.out.print("AM or PM, try again:");
					}
				}
			}
			changetimeDifference = true;
		}
	}*/

	/** 
	* @return void
	* @post AM or PM is set
	*//*
	public void AMorPM() {
		Scanner input = new Scanner(System.in);
		System.out.print("Insert AM or PM:");
		AM_or_PM = input.nextLine();
		while (!AM_or_PM.equals("AM") && !AM_or_PM.equals("PM")) {
			AM_or_PM = input.nextLine();
			if (!AM_or_PM.equals("AM") && !AM_or_PM.equals("PM")) {
				System.out.print("AM or PM, try again:");
			}
		}
		input.close();

	}*/

	/**
	 * This is the important part of the class. This will print the Clock in
	 * hh:mm:ss XX format. It will also utilize the joda-time library.
	 *
	 * HOW TIME IS CALCULATED: timeDifference between actual time and user
	 * specified time is calculated. This difference is used to find the user
	 * specified time on each iteration.
	 * 
	 * @return void
	 * @see http://kodejava.org/how-to-add-hours-minutes-seconds-into-datetime-in-joda-time/
	 * @see http://stackoverflow.com/questions/20331163/how-to-format-joda-time-datetime-to-only-mm-dd-yyyy
	 * @throws InterruptedException
	 * @throws IOException
	 */

	private void initializeClock() throws InterruptedException, IOException {
		//settimeDifference();
		readyFlag = true;

	}

}
