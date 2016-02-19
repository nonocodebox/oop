package clids.ex2.filescript;

/**
 * A class that represents an exception that notifies when the program arguments are incorrect.
 * This class extends ErrorException.

 */
public class ProgramArgumentsException extends ErrorException {
	/**
	 * ProgramArgumentsException default constructor.
	 */
	public ProgramArgumentsException() {
	}

	/**
	 * ProgramArgumentsException constructor
	 * @param message notifying message.
	 */
	public ProgramArgumentsException(String message) {
		super(message);
	}
}
