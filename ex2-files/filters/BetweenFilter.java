package clids.ex2.filescript.filters;

import java.io.File;

import clids.ex2.filescript.InvalidParameterException;

/**
 * A filter that filters files with a size in a specified range.
 * This class extends SizeFilter.

 */
public class BetweenFilter extends SizeFilter {
	double from;
	double to;
	
	/**
	 * BetweenFilter constructor
	 * @param from Range start, in kBytes.
	 * @param to Range end, in kBytes.
	 * @throws InvalidParameterException from is bigger than to.
	 */
	public BetweenFilter(double from, double to) throws InvalidParameterException {
		if (from > to) {
			throw new InvalidParameterException("First parameter must be smaller than the second");
		}
		this.from = from;
		this.to = to;
	}
	
	/**
	 * Filters a file based in its size.
	 * @param file The file to filter
	 * @return Whether the file size is in the range from <= size <= to
	 */
	public boolean filterFile(File file) {
		if (getSize(file) >= from && getSize(file) <= to) {
			return true;
		}
		return false;
	}
}
