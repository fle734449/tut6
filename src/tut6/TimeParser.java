package tut6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class TimeParser {
	   private static final int MINS_PER_HR = 60;
	   private static final int SECS_PER_MIN = 60;
	   
	   private static final int MIN_HRS = 0;
	   private static final int MAX_HRS = 23;
	   private static final int MIN_MINS = 0;
	   private static final int MAX_MINS = 59;
	   private static final int MIN_SECS = 0;
	   private static final int MAX_SECS = 59; // ignore leap seconds
	   
	   public static int parseTimeToSeconds(String time) throws NumberFormatException {
	       // Normalize the input (trim whitespace and make lower case)
	       time = time.trim().toLowerCase();
	       
	       int firstColon = time.indexOf(':');
	       if (firstColon == -1) {
	           throw new NumberFormatException("Unrecognized time format");
	       }

	       int secondColon = time.indexOf(':', firstColon+1);
	       if (secondColon == -1) {
	           throw new NumberFormatException("Unrecognized time format");
	       }
	       
	       // Interpret everything up to the first colon as the hour
	       int hours = Integer.valueOf(time.substring(0, firstColon));
	       // Interpret everything between the two colons as the minute
	       int minutes = Integer.valueOf(time.substring(firstColon+1, secondColon));
	       // Interpret the two characters after the second colon as the seconds
	       int seconds = Integer.valueOf(time.substring(secondColon+1, secondColon+3));
	       
	       // Adjust hours if 'pm' specified
	       if (time.contains("pm") && hours != 12) {
	           hours += 12;
	       } else if (time.contains("am") && hours == 12) {

	            hours = 0;

	        }
	       
	       // Range check the values
	       if ((hours < MIN_HRS || hours > MAX_HRS) ||
	           (minutes < MIN_MINS || minutes > MAX_MINS) ||
	           (seconds < MIN_SECS || seconds > MAX_SECS)) {
	               throw new IllegalArgumentException("Unacceptable time specified");
	       }

	       // Calculate number of seconds since midnight
	       return (((hours * MINS_PER_HR) + minutes) * SECS_PER_MIN) + seconds;
	   }
	   
	   /**
	     * Statement Coverage Test Suite
	     */
	   @Test
	   public void testStatement1() {
			assertEquals(1, parseTimeToSeconds("12:00:01 am"));
	   }
	   
	   @Test
	   public void testStatement2() {
			assertEquals(46800, parseTimeToSeconds("1:00:00 pm"));
	   }
	   
	   @Test
	   public void testStatement3() {
		   Assertions.assertThrows(NumberFormatException.class, new Executable() {
			   public void execute() throws Throwable {
				   assertEquals(46801, parseTimeToSeconds("1:00:1 pm"));
			   }
		    });		
	   }
	   
	   @Test
	   public void testStatement4() {
		   Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			   public void execute() throws Throwable {
				   assertEquals(46800, parseTimeToSeconds("13:00:00 pm"));
			   }
		    });		
	   }
	   
	   /**
	     * Branch Coverage Test Suite
	     */ 
	   @Test
	   public void testBranch1() {
			assertEquals(1, parseTimeToSeconds("12:00:01 am"));
	   }
	   
	   @Test
	   public void testBranch2() {
			assertEquals(46800, parseTimeToSeconds("1:00:00 pm"));
	   }
	   
	   @Test
	   public void testBranch3() {
		   Assertions.assertThrows(NumberFormatException.class, new Executable() {
			   public void execute() throws Throwable {
				   assertEquals(46801, parseTimeToSeconds("1:00:1 pm"));
			   }
		    });		
	   }
	   
	   @Test
	   public void testBranch4() {
		   Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			   public void execute() throws Throwable {
				   assertEquals(46800, parseTimeToSeconds("13:00:00 pm"));
			   }
		    });		
	   }
	   
	   @Test
	   public void testBranch5() {
			assertEquals(43200, parseTimeToSeconds("12:00:00 pm"));
	   }
	}