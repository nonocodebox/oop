package clids.ex2.filescript.actions;

import java.io.File;
import java.util.Date;

/**
 * An action that sets a file's last modified date.
 * This class implements the Action interface.
 */
public class SetLastModifiedTime implements Action {
	private Date newDate;

	/**
	 * SetLastModifiedTime constructor
	 * @param newDate The new date to set
	 */
	public SetLastModifiedTime (Date newDate) {
		this.newDate = newDate;
	}

	/**
	 * Set the file's last modified date
	 * @param file The file to set its date
	 */
	public void doAction(File file) {
		file.setLastModified(newDate.getTime());
	}
}
