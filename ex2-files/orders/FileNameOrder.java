package clids.ex2.filescript.orders;

import java.io.File;

/**
 * An order which orders files by their names.
 * This class extends StringOrder.

 */
public class FileNameOrder extends StringOrder {
	/**
	 * Returns the file's name.
	 * @param The file to compare.
	 * @return The file's name.
	 */
	protected String getString(File file) {
		return file.getName();
	}
}
