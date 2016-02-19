package clids.ex2.filescript;

/**
 * A class that represents a type 2 (Error) exception.
 * This class extends Exception.

 */
public class ErrorException extends Exception {
	/**
	 * ErrorException default constructor.
	 */
	public ErrorException() {
	}

	/**
	 * ErrorException constructor
	 * @param message notifying message.
	 */
	public ErrorException(String message) {
		super(message);
	}
}
