package clids.ex2.filescript;

/**
 * A class that represents an exception that notifies when a name is incorrect.
 * This class extends WarningException.

 */
public class BadNameException extends WarningException {
	/**
	 * BadNameException default constructor.
	 */
	public BadNameException() {
	}
	
	/**
	 * BadNameException constructor
	 * @param message notifying message.
	 */
	public BadNameException(String message) {
		super(message);
	}
}
