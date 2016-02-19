package clids.ex2.filescript.filters;

import java.io.File;

/**
 * A filter which does not filter any files.
 * This class extends Filter.

 */
public class DefaultFilter extends Filter {
	/**
	 * Filter a file.
	 * @param file The file to filter.
	 * @return Always true (always include the file).
	 */
	public boolean filterFile(File file) {
		return true;
	}
}
