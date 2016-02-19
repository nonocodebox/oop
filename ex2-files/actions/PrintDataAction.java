package clids.ex2.filescript.actions;

import java.io.File;
import java.util.Date;

/**
 * An action that prints file information.
 * This class implements the Action interface.
 */
public class PrintDataAction implements Action {
	private static final double BYTE = 1024.0;
	private static final char EXECUTE = 'x';
	private static final char WRITE = 'w';
	private static final char NO_OPERATION = '-';
	private static final char HIDDEN = 'h';

	/**
	 * Print a file's information.
	 * @param file The file to print.
	 */
	public void doAction(File file) {
		if (file.isHidden()) {
			System.out.print(HIDDEN);
		}
		else {
			System.out.print(NO_OPERATION);
		}
		if (file.canWrite()) {
			System.out.print(WRITE);
		}
		else {
			System.out.print(NO_OPERATION);
		}
		if (file.canExecute()) {
			System.out.print(EXECUTE);
		}
		else {
			System.out.print(NO_OPERATION);
		}
		System.out.print(" ");
		System.out.print((double)file.length() / BYTE);
		System.out.print(" ");
		System.out.print(new Date(file.lastModified()).toString());
		System.out.print(" ");
		System.out.print(file.getAbsolutePath());
		System.out.println("");
	}

}
