package clock;


public class Calendar
{
	
	public enum DAY_OF_WEEK {Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }
	public enum MONTH{January,February,March,April,May,June,July,August,September,October,November,December}
	
	/**
	 * 
	 * @author Luke Weber
	 * 
	 */
	private class cMonth
	{
		public MONTH mName;
		public int mValue;
		public int mTotalDays;
		
		/**
		 * Constructor for month
		 * @param aName the name of the month based on the MONTH enums i.e. March
		 * @param aLeap if this is a leap year.
		 */
		private cMonth(MONTH aName, boolean aLeap)
		{
			this.mName = aName;
			this.mValue = aName.ordinal()+1;
			switch(aName)
			{
				case January:
				case March:
				case May:
				case July:
				case August:
				case October:
				case December:
					this.mTotalDays = 31;
					break;
				case April:
				case June:
				case September:
				case November:
					this.mTotalDays = 30;
					break;
				case February:
					if(aLeap)
					{
						this.mTotalDays = 29;
					}
					else
					{
						this.mTotalDays = 28;
					}
					break;
				
				
			}
		}
	}
	
	private int mDayOfMonth;
	private DAY_OF_WEEK mDayOfWeek;
	private int mYear;
	private cMonth mMonth;
	
	
	
	public Calendar(int aDay, MONTH aMonth, int aYear)
	{
		this.mDayOfMonth = aDay;
		this.mMonth = new cMonth(aMonth, this.getLeap());
		this.mDayOfWeek = calcDayOfTheWeek(this.mMonth.mValue, aDay, aYear, this.getLeap());
		this.mYear = aYear;
	}
	/**
	 * 
	 * @return the day of the month
	 */
	public int getDayOfMonth()
	{
		return(this.mDayOfMonth);
	}
	
	/**
	 * 
	 * @param aDay an int to set the day.
	 */
	public void setDayOfMonth(int aDay)
	{
		this.mDayOfMonth = aDay;
	}
	/**
	 * 
	 * @return the day of the week i.e. Monday
	 */
	public DAY_OF_WEEK getDayOfWeek()
	{
		return(mDayOfWeek);
	}
	/**
	 * 
	 * @return the name of the month i.e March
	 */
	public MONTH getMonth()
	{
		return(mMonth.mName);
	}
	
	/**
	 * 
	 * @param aMonth the name of the month to set the calendar to.
	 */
	public void setMonth(MONTH aMonth)
	{
		cMonth lDummyMonth = new cMonth(aMonth, this.getLeap());
		this.mMonth = lDummyMonth;
		
	}
	/**
	 * 
	 * @return the ordinality of the month. e.g. December would return 12
	 */
	public int getMonthValue()
	{
		return(this.mMonth.mValue);
	}
	
	/**
	 * 
	 * @param aMonth: the month to check what is its ordinality.
	 * @return the ordinality of the month. e.g. December would return 12
	 */
	public int getMonthValue(MONTH aMonth)
	{
		return(aMonth.ordinal()+1);
	}
	
	/**
	 * 
	 * @return the year.
	 */
	public int getYear()
	{
		return(this.mYear);
	}
	
	/**
	 * 
	 * @param aYear the year to set the value to.
	 */
	public void setYear(int aYear)
	{
		this.mYear = aYear;
	}
	
	/**
	 * //based on https://en.wikipedia.org/wiki/Leap_year
	 * @return True if it is a leap year.
	 */
	public boolean getLeap()
	{
		
		boolean lReturn = false;
		if (this.getYear()%4!=0)
		{
			lReturn = false;
		}
		else if (this.getYear()%100!=0)
		{
			lReturn = true;
		}
		else if (this.getYear()%400!=0)
		{
			lReturn = false;
		}
		else 
		{
			lReturn = true;
		}
		return(lReturn);
	}
	
	/**
	 * Increases the day of the month calculating whether it is a new month.
	 */
	public void incrementDay()
	{
		this.mDayOfMonth++;
		if(this.mDayOfMonth > this.mMonth.mTotalDays)
		{
			this.mDayOfMonth = 1;
			this.incrementMonth();
		
		}
		this.mDayOfWeek = this.calcDayOfTheWeek(this.mMonth.mValue, this.mDayOfMonth, this.mYear, this.getLeap());
	}
	
	/**
	 * Increase the month calculating whether it should be a new month.
	 */
	private void incrementMonth()
	{
		boolean lFlag = false;//flag to indicate it is the next month
		for(MONTH iMonth: MONTH.values())
		{
			if(lFlag)
			{
				this.mMonth = new cMonth(iMonth, this.getLeap());
				break;
			}
			if(iMonth == this.mMonth.mName)
			{
				lFlag = true;
				if(iMonth == MONTH.December)
				{
					this.mYear++;
					this.mMonth = new cMonth(MONTH.January, this.getLeap());
					break;
				}
					
			}
		}
	}
	/**
	 * based on https://en.wikipedia.org/wiki/Determination_of_the_day_of_the_week
	 * @param aMonth: The ordinality of the month.
	 * @param aDay: The Day of the month
	 * @param aYear: The Year
	 * @param aLeap: If the year is a leap
	 * @return the day of the week
	 */
	    private DAY_OF_WEEK calcDayOfTheWeek(int aMonth, int aDay, int aYear, boolean aLeap)
	    {
	    	
	    	double answer; //pre mod by 7
	    	double DayOfWeek;// after mod by 7
	    	double NewYear;
	    	double lNewMonth = 0;//mod aMonths and year
	    	double century;
	    	
	    		    	
	    	DAY_OF_WEEK lDay;
	    	
    		switch(aMonth)
    		{
    			case 1:
				if(aLeap)
				{
					lNewMonth = (-1);
				}
				else
				{
					lNewMonth = 0;
				}
				break;
    			case 2:
    				if(aLeap)
    				{
    					lNewMonth = 2;
    				}
    				else
    				{
    					lNewMonth = 3;
    				}
    				break;
    			case 3:
    				lNewMonth = 3;
    				break;
    			case 4:
    				lNewMonth = 6;
    				break;
    			case 5:
    				lNewMonth = 1;
    				break;
    			case 6:
    				lNewMonth = 4;
    				break;
    			case 7:
    				lNewMonth = 6;
    				break;
    			case 8:
    				lNewMonth = 2;
    				break;
    			case 9:
    				lNewMonth = 5;
    				break;
    			case 10:
    				lNewMonth = 0;
    				break;
    			case 11:
    				lNewMonth = 3;
    				break;
    			case 12:
    				lNewMonth = 5;
    				break;
    			default:
    				System.out.println("Only 12 Months");
    				break;
    		}
    
    		//sets new year and century
    		if (aYear >= 2000)
    		{
    			century = 6;
    			NewYear = (aYear - 2000);
    		}
    		else
    		{
    			century = 0;
    			NewYear = (aYear - 1900);
    		}
	    
	    
    		answer = (aDay + lNewMonth + NewYear + (NewYear/4) + century);
    		DayOfWeek = answer;
	    
	    
    		while (DayOfWeek >=7)
    		{
     			DayOfWeek = (DayOfWeek - 7); 
    		}
    
    		if ((DayOfWeek >=0) && (DayOfWeek <1))
    		{
    			lDay = DAY_OF_WEEK.Sunday; 
    		}
    		else if ((DayOfWeek >=1) && (DayOfWeek <2))
    		{
    			 lDay = DAY_OF_WEEK.Monday; 
    		}
    		else if ((DayOfWeek >=2) && (DayOfWeek <3))
    		{
    			lDay = DAY_OF_WEEK.Tuesday; 
    		}
    		else if ((DayOfWeek >=3) && (DayOfWeek <4))
    		{
    			lDay = DAY_OF_WEEK.Wednesday; 
    		}
    		else if ((DayOfWeek >=4) && (DayOfWeek <5))
    		{
    			lDay = DAY_OF_WEEK.Thursday; 
    		}
    		else if ((DayOfWeek >=5) && (DayOfWeek <6))
    		{
    			lDay = DAY_OF_WEEK.Friday; 
    		}
    		else if ((DayOfWeek >=6) && (DayOfWeek <7))
    		{
    			lDay = DAY_OF_WEEK.Friday; 
    		}
    		else
    		{
    			lDay = DAY_OF_WEEK.Sunday;
    			System.out.println("Error calculating day of the week");
    		}
	    	
	    	return lDay;	
	    }
		
}
