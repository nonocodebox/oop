package clids.ex4.scope;

import clids.ex4.main.CodeException;

/**
 * 
 * Represent a problem with the Sjava Scope
 * (Scope not closed, 2 variables with the same name, etc...)
 *
 */
public class ScopeException extends CodeException {
	
	private static final long serialVersionUID = 1L;

	 public ScopeException(String msg, int lineNumber) {
		   super(msg + " line number: " + lineNumber);
	 }
}
