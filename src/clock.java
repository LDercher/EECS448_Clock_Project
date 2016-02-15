import java.io.IOException;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 
 * @authors Victor Berger, Jesse Yang, Jeromy Tsai, and Cammy Vo
 * EECS 448 - Clock Project
 * prof: John Gibbons
 * code freeze: February 14th, 2016
 * 
 * @see http://stackoverflow.com/questions/12105164/java-joda-time-download-and-install-step-by-ste
 */


/**
 * How to import timing library taken from:
 * http://stackoverflow.com/questions/12105164
 * /java-joda-time-download-and-install-step-by-step Clock class
 */
public class clock {
	/** constructor method */

	public clock() {
		hh = 0;
		mm = 0;
		ss = 0;
		AM_or_PM = "AM";
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

	public clock(int menu, int hh, int mm, int ss) {
		// pass an integer to skip input() call; this is used to get menu output
		this.hh = hh;
		this.mm = mm;
		this.ss = ss;
		AM_or_PM = "AM";
		timeString = "";
		timeDifference = 0;
	}

	/**
	 * hh is hours, mm is minutes, ss is seconds, AM_or_PM is AM or PM,
	 * militaryTime switches 12-24 hour format
	 */
	int hh;
	int mm;
	int ss;
	String AM_or_PM;
	boolean militaryTime = false;
	String Num_S = "";
	String timeString;
	boolean readyFlag = false;
	int timeDifference;
	boolean changetimeDifference = false;

	/** @return timeString */
	public String getTime() {
		return timeString;
	}

	/** set timeString variable */
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

	public void sethh(int hh) {
		this.hh = hh;
	}

	public int gethh() {
		return hh;
	}

	public void setmm(int mm) {
		this.mm = mm;
	}

	public int getmm() {
		return mm;
	}

	public void setss(int ss) {
		this.ss = ss;
	}

	public int getss() {
		return ss;
	}

	public void settimeDifference() {
		// Convert hh to 24 hour equivalent; used to calculate the milliseconds of
		// the day
		int HH = hh;
		if (AM_or_PM.equals("PM") && HH != 12)// if PM add 12 hours except if it
												// is 12 PM
		{
			HH += 12;
		} else if (AM_or_PM.equals("AM") && HH == 12)// if 12 AM, it is 0 hours
														// from midnight
		{
			HH = 0;
		}

		timeDifference = (HH * 3600 + mm * 60 + ss) * 1000
				- DateTime.now().getMillisOfDay();
	}

	public boolean getReadyFlag() {
		return readyFlag;
	}

	public void input() {
		Scanner input = new Scanner(System.in);

		int temp = -1;

		System.out.println("Choose:");
		System.out.println("1.) Military time(24 hour)");
		System.out.println("2.) Non-military time(12 hour) ");
		System.out
				.println("3.) Switch between AM/PM and military time formats");
		while (temp < 1 || temp > 3) {
			Num_S = input.nextLine();
			while (!Num_S.matches("\\d+$")) // from:
											// http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
			{
				System.out.print("not valid number. try again: ");
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
		/** set hh */

		if (temp == 1 || temp == 2) {
			hh = -1;
			mm = -1;
			ss = -1;
			System.out.print("Insert hh:");
			if (militaryTime == true) {
				while (hh < 0 || hh > 23) {
					Num_S = input.nextLine();
					while (!Num_S.matches("\\d+$")) // from:
													// http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
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
					while (!Num_S.matches("\\d+$")) // from:
													// http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
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

			/** set mm */
			System.out.print("Insert mm:");
			while (mm < 0 || mm > 59) {
				Num_S = input.nextLine();
				while (!Num_S.matches("\\d+$")) // from:
												// http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
				{
					System.out.print("Invalid number. Please try again: ");
					Num_S = input.nextLine();
				}
				mm = Integer.parseInt(Num_S);
				if (mm < 0 || mm > 59) {
					System.out.print("MM must be between 0-59, try again:");
				}
			}
			/** set ss */
			System.out.print("Insert ss:");
			while (ss < 0 || ss > 59) {
				Num_S = input.nextLine();
				while (!Num_S.matches("\\d+$")) // from:
												// http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
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
	}

	/** set AM or PM */
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

	}

	/**
	 * This is the important part of the class. This will print the Clock in
	 * hh:mm:ss XX format. It will also utilize the joda-time library.
	 *
	 * HOW TIME IS CALCULATED: timeDifference between actual time and user
	 * specified time is calculated. This difference is used to find the user
	 * specified time on each iteration.
	 * 
	 * @see http 
	 *      ://kodejava.org/how-to-add-hours-minutes-seconds-into-datetime-in
	 *      -joda-time/
	 * @see http 
	 *      ://stackoverflow.com/questions/20331163/how-to-format-joda-time-
	 *      datetime -to-only-mm-dd-yyyy
	 * @throws InterruptedException
	 * @throws IOException
	 */
	private void initializeClock() throws InterruptedException, IOException {
		settimeDifference();
		readyFlag = true;

	}

}
