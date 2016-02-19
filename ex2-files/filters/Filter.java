package clids.ex2.filescript.filters;

import java.io.File;
import java.io.FileFilter;

/**
 * An abstract class that filters files.
 * This class implements the FileFilter interface.

 */
public abstract class Filter implements FileFilter {
	/**
	 * A method to filter files and directories from the final list.
	 * This method filters out directories and filters files according to filterFile().
	 * @param pathname The file to filter.
	 * @return Whether or not the file should be included in the final list.
	 */
	public boolean accept(File pathname) {
		if (pathname.isFile() && filterFile(pathname)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Filter a file.
	 * @param file The file to filter
	 * @return Whether or not the file should be included in the final list.
	 */
	public abstract boolean filterFile(File file);
}
