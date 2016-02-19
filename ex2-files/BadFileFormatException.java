package clids.ex2.filescript;

/**
 * A class that represents an exception that notifies when a recieved file format is in correct.
 * This class extends ErrorException.

 *
 */
public class BadFileFormatException extends ErrorException {
	/**
	 * BadFileFormatException default constructor
	 */
	public BadFileFormatException() {
	}
	
	/**
	 * BadFileFormatException constructor
	 * @param message notifying message.
	 */
	public BadFileFormatException(String message) {
		super(message);
	}
}
