package clids.ex2.filescript.actions;

import clids.ex2.filescript.WarningException;
import clids.ex2.filescript.toolbox.ToolBox;

/**
 * A factory class to create actions based on action strings.
 */
public class ActionFactory {
	private static final String LAST_MOD = "last_mod";
	private static final String WRITE = "write";
	private static final String EXEC = "exec";
	private static final String COPY = "copy";
	private static final String PRINT_DATA = "print_data";
	private static final String PRINT_NAME = "print_name";
	final static String PARAMETERS_SEPARATOR = "%";

	/**
	 * Creates an action from an action string.
	 * @param actionString The whole action string, including the action name and parameters.
	 * @param sourceDir The source directory (needed by some actions).
	 * @return A new Action object.
	 * @throws WarningException An error occured while creating the action.
	 */
	public static Action createAction(String actionString, String sourceDir)
			throws WarningException {
		String [] actionParameters = actionString.split(PARAMETERS_SEPARATOR);
		Action action = null;
		switch (actionParameters[0]) {
		case PRINT_NAME:
			action = new PrintNameAction();
			break;
		case PRINT_DATA:
			action = new PrintDataAction();
			break;
		case COPY:
			action = new CopyAction(sourceDir, actionParameters[1]);
			break;
		case EXEC:
			action = new SetXPermissionAction(ToolBox.parseBoolean(actionParameters[1]));
			break;
		case WRITE:
			action = new SetWPermissionAction(ToolBox.parseBoolean(actionParameters[1]));
			break;
		case LAST_MOD:
			action = new SetLastModifiedTime(ToolBox.parseDate(actionParameters[1]));
			break;
		default:
			throw new BadActionNameException("Bad action name");
		}
		return action;

	}
}
