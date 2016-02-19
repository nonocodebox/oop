package clids.ex2.filescript.actions;

import java.io.File;

/**
 * Represends a file action. This in an interface which all actions implement.
 */
public interface Action {
	/**
	 * Perform the action on a file.
	 * @param file The file on which to perform the action.
	 * @throws Exception Some error occured while performing the action.
	 */
	public void doAction(File file) throws Exception;
}
