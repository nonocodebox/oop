package clids.ex2.filescript.orders;

import java.io.File;

/**
 * An order which orders files by their last modified date.
 * This class extends LongOrder.

 */
public class LastModifiedOrder extends LongOrder {
	/**
	 * Returns the file's last modified date as long.
	 * @param The file to compare.
	 * @return The file's last modified date as long.
	 */
	protected long getLong(File file) {
		return file.lastModified();
	}
}
