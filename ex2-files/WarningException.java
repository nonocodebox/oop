package clids.ex2.filescript;

/**
 * A class that represents a type 1 (Warning) exception.
 * This class extends Exception.

 */
public class WarningException extends Exception {
	/**
	 * WarningException default constructor.
	 */
	public WarningException() {
	}
	
	/**
	 * WarningException constructor
	 * @param message notifying message.
	 */
	public WarningException(String message) {
		super(message);
	}
}
