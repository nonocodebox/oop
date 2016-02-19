package clids.ex2.filescript.actions;

import java.io.File;

/**
 * An action that sets a file's execute permission.
 * This class extends SetActionPermission.

 */
public class SetXPermissionAction extends SetPermissionAction {
	/**
	 * SetXPermissionAction constructor
	 * @param set Whether to set or clear the permission.
	 */
	public SetXPermissionAction(boolean set) {
		super(set);
	}

	/**
	 * Set the file's execute permission.
	 * @param fie The file to update its permission.
	 * @param set Whether to set or clear the permission.
	 */
	protected void setPermission(File file, boolean set) {
		file.setExecutable(set);
	}
}
