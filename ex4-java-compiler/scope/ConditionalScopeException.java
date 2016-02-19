package clids.ex4.scope;

/**
 * 
 * Represent a problem with the condition of the conditional Scope
 *
 */
public class ConditionalScopeException extends ScopeException {
	private static final long serialVersionUID = 1L;

	 public ConditionalScopeException (String msg, int lineNumber) {
		   super(msg + " line number: ", lineNumber);
	}
}
