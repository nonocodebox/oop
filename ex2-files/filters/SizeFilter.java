package clids.ex2.filescript.filters;

import java.io.File;

/**
 * An abstract filter which filters files based on their size.
 * This class extends Filter.

 */
public abstract class SizeFilter extends Filter {
	private static final double BYTES_IN_KBYTE = 1024.0;
	
	/**
	 * Gets the size of the file, in kBytes.
	 * @param file The file to get its size.
	 * @return The file size, in kBytes.
	 */
	protected double getSize(File file) {
		return (double)file.length() / BYTES_IN_KBYTE;
	}
}
