package clids.ex2.filescript.filters;

import java.io.File;

/**
 * A filter to filter files based on their name.
 * This class extends Filter.

 */
public class FileNameFilter extends Filter {
	String compare;
	
	/**
	 * FileNameFilter constructor
	 * @param name The file name to compare to.
	 */
	public FileNameFilter(String name) {
		this.compare = name;
	}
	
	/**
	 * Filters a file.
	 * @param file The file to filter.
	 * @return Whether the file's name equals the given name.
	 */
	public boolean filterFile(File file) {
		return file.getName().equals(compare);
	}
}
