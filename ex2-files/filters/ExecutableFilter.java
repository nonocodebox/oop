package clids.ex2.filescript.filters;

import java.io.File;

/**
 * A filter which filters executable files.
 * This class extends BooleanFilter.

 */
public class ExecutableFilter extends BooleanFilter {
	/**
	 * ExecutableFilter constructor
	 * @param parameter Whether the file should be executable.
	 */
	public ExecutableFilter(boolean parameter) {
		super(parameter);
	}

	/**
	 * Returns whether the file is executable.
	 * @param file The file to check.
	 * @return Whether the file is executable.
	 */
	protected boolean getBoolean(File file) {
		return file.canExecute();
	}
}
