package clids.ex2.filescript.actions;

import java.io.File;

/**
 * \prints a file's name.
 * This class implements the Action interface.
 */
public class PrintNameAction implements Action {
	/**
	 * Print a file's name.
	 * @param file The file to print.
	 */
	public void doAction(File file) {
		System.out.println(file.getName());
	}
}
