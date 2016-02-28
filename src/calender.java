import static java.lang.System.*;
public class calendar
{
public enum DAY_OF_WEEK {Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }
	
	    public static DAY_OF_WEEK dayOftheWeek(int aMonth, int aDay, int aYear, boolean aLeap)
	    {
	    	
	    	double answer; //pre mod by 7
	    	double DayOfWeek;// after mod by 7
	    	double NewYear;
	    	double lNewMonth = 0;//mod aMonths and year
	    	double century;
	    	int i=0;
	    	char leapYear; // is leap year
	    	char repeat = 'y';
	    	
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
	public static void main(String[] args)
	{
		DAY_OF_WEEK lDay = dayOftheWeek(2,24,2016,true);
		out.println(lDay);
	}
	
}
	    
	    	
	    	
	    