package clids.ex4.variable;

import clids.ex4.main.CodeException;


/**
 * Represents a variable parsing exception
 */
public class VariableException extends CodeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * VariableException constructor
	 * @param msg The exception message
	 * @param lineNumber The source line number
	 */
	 public VariableException(String msg, int lineNumber) {
	   super("Problem in line " + lineNumber);
	 }
}
