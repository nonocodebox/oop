package clids.ex2.filescript.orders;

import java.io.File;

/**
 * An abstract order which orders two file based on long values.
 * This class implements Order.

 */
public abstract class LongOrder implements Order {
	/**
	 * Compares to files based on long values.
	 * @param f1 The first file.
	 * @param f2 The second file.
	 * @return positive it f1 > f2, negative if f1 < f2, or 0 if f1 = f2.
	 */
	public int compare(File f1, File f2) {
		if (getLong(f1) > getLong(f2)) {
			return 1;
		}
		else if (getLong(f1) < getLong(f2)) {
			return -1;
		}
		
		return 0;
	}

	/**
	 * Gets the long from a file to use when comparing.
	 * @param file The file to compare.
	 * @return The file's long value to compare.
	 */
	abstract protected long getLong(File file);
}
