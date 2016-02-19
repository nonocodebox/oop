package clids.ex2.filescript.toolbox;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import clids.ex2.filescript.InvalidParameterException;

/**
 * External tools that are useful in different types of classes.
 * @author User
 */
public class ToolBox {
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final String NO = "NO";
	private static final String YES = "YES";

	/**
	 * Parses a YES/NO boolean.
	 * @param stringParse The string to parse.
	 * @return true for "YES", false for "NO".
	 * @throws InvalidParameterException The string is not "YES" or "NO".
	 */
	public static boolean parseBoolean(String stringParse) throws InvalidParameterException {
		switch (stringParse) {
		case YES:
			return true; 
		case NO:
			return false;
		default:
			throw new InvalidParameterException("Invalid boolean value");
		}
	}
	
	/**
	 * Parses a date string (format dd/MM/yyyy).
	 * @param stringDate The string to parse.
	 * @return The parsed Date object.
	 */
	public static Date parseDate(String stringDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		try {
			return dateFormat.parse(stringDate);
		} catch (ParseException e) {
			return new Date(); // Should never happen as long as stringDate has the correct format
		}
	}
	
	/**
	 * Concatenates a path with a relative path.
	 * @param first The first (directory, absolute or relative) path to concatenate.
	 * @param second The second (directory or file, relative) path to concatenate.
	 * @return The concatenated path.
	 */
	public static String ConcatPaths(String first, String second) {
		if (first.endsWith(File.separator)) {
			return first + second;
		}
		else {
			return first + File.separator + second;
		}
	}
}
