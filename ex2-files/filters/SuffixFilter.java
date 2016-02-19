package clids.ex2.filescript.filters;

import java.io.File;

/**
 * A filter which filters files that ends with the specified suffix.
 * This class extends Filter.

 */
public class SuffixFilter extends Filter {
	String suffix;
	
	/**
	 * SuffixFilter constructor
	 * @param suffix The suffix to compare to.
	 */
	public SuffixFilter(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * Filter a file.
	 * @param file The file to filter
	 * @return Whether the file's name ends with suffix.
	 */
	public boolean filterFile(File file) {
		return file.getName().endsWith(suffix);
	}
}


	