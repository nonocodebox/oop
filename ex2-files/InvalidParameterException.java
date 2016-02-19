package clids.ex2.filescript;

/**
 * A class that represents an exception that notifies when an invalid parameter is found.
 * This class extends WarningException.

 */
public class InvalidParameterException extends WarningException {
	/**
	 * InvalidParameterException default constructor.
	 */
	public InvalidParameterException() {
	}

	/**
	 * InvalidParameterException constructor
	 * @param message notifying message.
	 */
	public InvalidParameterException(String message) {
		super(message);
	}
}
