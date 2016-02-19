package clids.ex2.filescript.filters;

import java.io.File;

/**
 * A filter which filters files smaller than a specified size.
 * This class extends SizeFilter.

 */
public class SmallerThanFilter extends SizeFilter {
	double size;
	
	/**
	 * SmallerThanFilter constructor
	 * @param size The size to compare to.
	 */
	public SmallerThanFilter(double size) {
		this.size = size;
	}
	
	/**
	 * Filter a file.
	 * @param file The file to filter.
	 * @return Whether the file's size < size.
	 */
	public boolean filterFile(File file) {
		if (getSize(file) < size) {
			return true;
		}
		return false;
	}
}

	
	
	
