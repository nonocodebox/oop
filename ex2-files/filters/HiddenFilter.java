package clids.ex2.filescript.filters;

import java.io.File;

/**
 * A filter which filters hidden files.
 * This class extends BooleanFilter.

 */
public class HiddenFilter extends BooleanFilter {
	/**
	 * HiddenFilter constructor
	 * @param parameter Whether files should be hidden
	 */
	public HiddenFilter(boolean parameter) {
		super(parameter);
	}
	
	/**
	 * Returns whether a file is hidden
	 * @param file The file to check
	 * @return Whether the file is hidden
	 */
	protected boolean getBoolean(File file) {
		return file.isHidden();
	}
}
