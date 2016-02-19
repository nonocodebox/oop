package clids.ex2.filescript.orders;

import java.io.File;

/**
 * An order which orders files by their size.
 * This class extends LongOrder.

 */
public class SizeOrder extends LongOrder {
	/**
	 * Returns the file's size in bytes.
	 * @param The file to compare.
	 * @return The file's size in bytes.
	 */
	protected long getLong(File file) {
		return file.length();
	}
}
