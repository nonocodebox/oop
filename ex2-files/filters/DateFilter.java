package clids.ex2.filescript.filters;

import java.io.File;
import java.util.Date;

/**
 * An abstract filter class that filters files based on their date.
 * This class extends Filter.

 */
public abstract class DateFilter extends Filter {
	private Date compareDate;
	
	/**
	 * DateFilter constructor
	 * @param compareDate The date to compare to.
	 */
	public DateFilter(Date compareDate) {
		this.compareDate = compareDate;
	}
	
	/**
	 * Filter the file.
	 * @param file The file to filter.
	 * @return Whether the file should be included in the final list.
	 */
	public boolean filterFile(File file) {
		Date fileDate = new Date(file.lastModified());
		
		return filterDate(fileDate, compareDate);
	}
	
	/**
	 * Filter a file based on its date and a given date.
	 * @param fileDate The file's date.
	 * @param compareDate The date to compare to.
	 * @return Whether the file should be included in the final list.
	 */
	protected abstract boolean filterDate(Date fileDate, Date compareDate);
}
