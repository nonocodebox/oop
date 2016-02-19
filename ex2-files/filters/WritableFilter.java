package clids.ex2.filescript.filters;

import java.io.File;

/**
 * A filter which filters writable files.
 * This class extends BooleanFilter.

 */
public class WritableFilter extends BooleanFilter {
	/**
	 * WritableFilter constructor
	 * @param parameter Whether the file should be writable.
	 */
	public WritableFilter(boolean parameter) {
		super(parameter);
	}

	/**
	 * Returns whether the file is writable.
	 * @param file The file to check.
	 * @return Whether the file is writable.
	 */
	protected boolean getBoolean(File file) {
		return file.canWrite();
	}
}