package clids.ex2.filescript.actions;

import java.io.File;

/**
 * An abstract action that sets a file's permission.
 * This class implements the Action interface.
 */
public abstract class SetPermissionAction implements Action {
	private boolean parameter;

	/**
	 * SetPermissionAction constructor
	 * @param set Whether to set or clear the permission.
	 */
	public SetPermissionAction(boolean set) {
		this.parameter = set;
	}

	/**
	 * Update the file's permission.
	 * @param file The file to update its permission.
	 */
	public void doAction(File file) {
		setPermission(file, parameter);
	}

	/**
	 * Actually set the file's permission, should be implemented by this class' children.
	 * @param fie The file to update its permission.
	 * @param set Whether to set or clear the permission.
	 */
	protected abstract void setPermission(File file, boolean set);
}
