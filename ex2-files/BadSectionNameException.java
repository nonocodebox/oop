package clids.ex2.filescript;

/**
 * A class that represents an exception that notifies when a subsection name is incorrect.
 * This class extends ErrorException (and not BadNameException since this should be a type 2 error).

 */
public class BadSectionNameException extends ErrorException {
	/**
	 * BadSectionNameException default constructor.
	 */
	public BadSectionNameException() {
	}

	/**
	 * BadSectionNameException constructor
	 * @param message notifying message.
	 */
	public BadSectionNameException(String message) {
		super(message);
	}
}
