package clids.ex2.filescript.filters;

import java.io.File;

/**
 * A filter to filter files greater than a specified size.
 * This class extends SizeFilter.

 */
public class GreaterThanFilter extends SizeFilter {
	double size;
	
	/**
	 * GreaterThanFilter constructor
	 * @param size The size to compare to.
	 */
	public GreaterThanFilter(double size) {
		this.size = size;
	}
	
	/**
	 * Filters a file based on its size
	 * @param file The file to filter
	 * @return Whether the file's size > size
	 */
	public boolean filterFile(File file) {
		if (getSize(file) > size) {
			return true;
		}
		return false;
	}
}
