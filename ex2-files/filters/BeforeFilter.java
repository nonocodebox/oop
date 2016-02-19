package clids.ex2.filescript.filters;

import java.util.Date;

/**
 * A filter that filters files modified before (or at) a given date.
 * This class extends DateFilter.

 */
public class BeforeFilter extends DateFilter {
	/**
	 * BeforeFilter constructor
	 * @param compareDate The date to compare to.
	 */
	public BeforeFilter(Date compareDate) {
		super(compareDate);
	}
	
	/**
	 * Filter a file based on its date and a given date.
	 * @param fileDate The file's date.
	 * @param compareDate The date to compare to.
	 * @return Whether date <= compareDate.
	 */
	protected boolean filterDate(Date fileDate, Date compareDate) {
		if (fileDate.compareTo(compareDate) <= 0) {
			return true;
		}
		return false;
	}
}
