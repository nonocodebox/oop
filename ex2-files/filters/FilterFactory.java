package clids.ex2.filescript.filters;

import clids.ex2.filescript.WarningException;
import clids.ex2.filescript.toolbox.*;

/**
 * A factory class to create filters from filter strings.

 */
public class FilterFactory {
	private static final String HIDDEN = "hidden";
	private static final String EXECUTABLE = "executable";
	private static final String WRITABLE = "writable";
	private static final String SUFFIX = "suffix";
	private static final String PREFIX = "prefix";
	private static final String FILE = "file";
	private static final String SMALLER_THAN = "smaller_than";
	private static final String BETWEEN = "between";
	private static final String GREATER_THAN = "greater_than";
	private static final String AFTER = "after";
	private static final String BEFORE = "before";
	final static String PARAMETERS_SEPARATOR = "%";
	
	/**
	 * Creates a filter from a filter string.
	 * @param filterString The filter string.
	 * @return A new Filter object.
	 * @throws WarningException An error occurred when creating the filter.
	 */
	public static Filter createFilter(String filterString)
			throws WarningException {
		String [] filterParameters = filterString.split(PARAMETERS_SEPARATOR);
		Filter filter = null;
		int parametersLength = 1;

		switch (filterParameters[0]) {
		case BEFORE:
			filter = new BeforeFilter(ToolBox.parseDate(filterParameters[1]));
			break;
		case AFTER:
			filter = new AfterFilter(ToolBox.parseDate(filterParameters[1]));
			break;
		case GREATER_THAN:
			filter = new GreaterThanFilter(Double.parseDouble(filterParameters[1]));
			break;
		case BETWEEN:
			filter = new BetweenFilter(Double.parseDouble(filterParameters[1]), 
					Double.parseDouble(filterParameters[2]));
			parametersLength = 2;
			break;
		case SMALLER_THAN:
			filter = new SmallerThanFilter(Double.parseDouble(filterParameters[1]));
			break;
		case FILE:
			filter = new FileNameFilter(filterParameters[1]);
			break;
		case PREFIX:
			filter = new PrefixFilter(filterParameters[1]);
			break;
		case SUFFIX:
			filter = new SuffixFilter(filterParameters[1]);
			break;
		case WRITABLE:
			filter = new WritableFilter(ToolBox.parseBoolean(filterParameters[1]));
			break;
		case EXECUTABLE:
			filter = new ExecutableFilter(ToolBox.parseBoolean(filterParameters[1]));
			break;
		case HIDDEN:
			filter = new HiddenFilter(ToolBox.parseBoolean(filterParameters[1]));
			break;
		default:
			throw new BadFilterNameException("Bad filter name");
		}
		
		if (filterParameters.length > 1 + parametersLength && 
				filterParameters[1 + parametersLength].equals("NOT")) {
			filter = new NotFilter(filter);
		}
		
		return filter;
		
	}
}
