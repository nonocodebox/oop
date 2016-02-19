package clids.ex2.filescript.filters;

import clids.ex2.filescript.BadNameException;

/**
 * A class that represents an exception that notifies when a filter name is incorrect.
 * This class extends BadNameException.

 */
public class BadFilterNameException extends BadNameException {
	/**
	 * BadFilterNameException default constructor.
	 */
	public BadFilterNameException() {
	}

	/**
	 * BadFilterNameException constructor
	 * @param message notifying message.
	 */
	public BadFilterNameException(String message) {
		super(message);
	}
}
