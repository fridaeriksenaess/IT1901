package periodtracker.core;

import java.time.LocalDate;
import java.util.Calendar;


/**
 * Class for getting the Localdate object
 */
public class CalendarLogic {

	Calendar calendar = Calendar.getInstance();

	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH);

	/**
	 * Method to get the current date
	 * 
	 * @return the current date
	 */
	public LocalDate getDate() {
		return LocalDate.now();
		}

	/**
	 * Method to get the current year
	 * 
	 * @return the current year
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * Method to get the current month
	 * 
	 * @return the current month
	 */
	public int getMonth() {
		return this.month;
	}

}
