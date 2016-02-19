package clids.ex2.filescript.orders;

import java.io.File;

/**
 * An order which orders files by their absolute path.
 * This class extends StringOrder.

 */
public class AbsolutePathOrder extends StringOrder {
	/**
	 * Returns the file's absolute path.
	 * @param The file to compare.
	 * @return The file's absolute path.
	 */
	protected String getString(File file) {
		return file.getAbsolutePath();
	}
}
