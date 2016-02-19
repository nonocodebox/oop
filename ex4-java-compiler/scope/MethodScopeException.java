package clids.ex4.scope;

/**
 * 
 * Represent a problem with the method Scope (not the method itself)
 *
 */
public class MethodScopeException extends ScopeException {
	private static final long serialVersionUID = 1L;
	
	 public MethodScopeException(String msg, int lineNumber) {
		   super(msg + " line number: ", lineNumber);
	 }
}


