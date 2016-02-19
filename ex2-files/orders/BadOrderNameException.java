package clids.ex2.filescript.orders;

import clids.ex2.filescript.BadNameException;

/**
 * A class that represents an exception that notifies when an order name is incorrect.
 * This class extends BadNameException.

 */
public class BadOrderNameException extends BadNameException {
	/**
	 * BadOrderNameException default constructor.
	 */
	public BadOrderNameException() {
	}
	
	/**
	 * BadOrderNameException constructor
	 * @param message notifying message.
	 */
	public BadOrderNameException(String message) {
		super(message);
	}
}
