package clids.ex4.main;

/**
 * Represents a code parsing exception
 */
public class ParserException extends CodeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * ParserException constructor
	 * @param msg The exception message
	 * @param lineNumber The source line number
	 */
	public ParserException (String msg, int lineNumber) {
	   super(msg + " line number: " + lineNumber);
	}
}
