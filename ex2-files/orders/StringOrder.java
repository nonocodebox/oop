package clids.ex2.filescript.orders;

import java.io.File;

/**
 * An abstract order which orders two file strings.
 * This class implements Order.

 */
public abstract class StringOrder implements Order {
	/**
	 * Compares to files based on some strings.
	 * @param f1 The first file.
	 * @param f2 The second file.
	 * @return positive it f1 > f2, negative if f1 < f2, or 0 if f1 = f2.
	 */
	public int compare(File f1, File f2) {
		return getString(f1).compareTo(getString(f2));
	}
	
	/**
	 * Gets the string from a file to use when comparing.
	 * @param file The file to compare.
	 * @return The file's string to compare.
	 */
	abstract protected String getString(File file);
}
