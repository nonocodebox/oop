package clids.ex2.filescript.filters;

import java.io.File;

/**
 * A decorator class for performing a NOT operation on a filter.
 * This class extends Filter.

 */
public class NotFilter extends Filter {
	private Filter filter;

	/**
	 * NotFilter constructor
	 * @param filter The original filter.
	 */
	public NotFilter(Filter filter) {
		this.filter = filter;
	}
	
	/**
	 * Filters a file.
	 * @param file The file to filter.
	 * @return NOT on the original filter's result.
	 */
	public boolean filterFile(File file) {
		return !filter.filterFile(file);
	}
}
