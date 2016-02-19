package clids.ex4.method;

import clids.ex4.main.CodeException;

/**
 * Represent a problem with method declaration or method call
 */
public class MethodException extends CodeException {
	
	private static final long serialVersionUID = 1L;

	public MethodException(String msg, int lineNumber) {
		super(msg + " line number: " + lineNumber);
	}
	
}
