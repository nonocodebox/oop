package clids.ex2.filescript.filters;

import java.io.File;

/**
 * An abstract filter which filters files based on a boolean value.
 * This class extends Filter.

 */
public abstract class BooleanFilter extends Filter {
	private boolean parameter;
	
	/**
	 * BooleanFilter constructor
	 * @param parameter The boolean's expected value
	 */
	public BooleanFilter(boolean parameter) {
		this.parameter = parameter;
	}
	
	/**
	 * Filter a file.
	 * @param file The file to filter.
	 * @return Whether to include the file in the final list.
	 */
	public boolean filterFile(File file) {
		if (this.parameter == getBoolean(file)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Gets the boolean value for filtering.
	 * @param file The file of which to get the value
	 * @return The boolean value
	 */
	protected abstract boolean getBoolean(File file);
}
