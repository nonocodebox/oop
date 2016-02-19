package clids.ex2.filescript.actions;

import java.io.File;

/**
 * An action that sets a file's write permission.
 * This class extends SetActionPermission.
 */
public class SetWPermissionAction extends SetPermissionAction {
	/**
	 * SetWPermissionAction constructor
	 * @param set Whether to set or clear the permission.
	 */
	public SetWPermissionAction(boolean set) {
		super(set);
	}

	/**
	 * Set the file's write permission.
	 * @param fie The file to update its permission.
	 * @param set Whether to set or clear the permission.
	 */
	protected void setPermission(File file, boolean set) {
		file.setWritable(set);
	}
}
