package clids.ex2.filescript.actions;

import clids.ex2.filescript.BadNameException;

/**
 * A class that represents an exception that notifies when an action name is incorrect.
 * This class extends BadNameException.
 */
public class BadActionNameException extends BadNameException {
	/**
	 * BadActionNameException default constructor.
	 */
	public BadActionNameException() {
	}

	/**
	 * BadActionNameException constructor
	 * @param message notifying message.
	 */
	public BadActionNameException(String message) {
		super(message);
	}
}
