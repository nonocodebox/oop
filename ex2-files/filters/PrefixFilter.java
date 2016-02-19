package clids.ex2.filescript.filters;

import java.io.File;

/**
 * A filter which filters files that starts with the specified prefix.
 * This class extends Filter.

 */
public class PrefixFilter extends Filter {
	String prefix;
	
	/**
	 * PrefixFilter constructor
	 * @param prefix The prefix to compare to.
	 */
	public PrefixFilter(String prefix) {
		this.prefix = prefix;
	}
	
	/**
	 * Filter a file.
	 * @param file The file to filter
	 * @return Whether the file's name starts with prefix.
	 */
	public boolean filterFile(File file) {
		return file.getName().startsWith(prefix);
	}
}
