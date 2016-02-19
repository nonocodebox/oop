package clids.ex4.toolbox;

/**
 * A class containing regular expressions used for variable parsing.
 */
public class VariableRegex {
	public static final String VAR_NAME = "[a-zA-Z]\\w*|_\\w+";
	public static final String VAR_NAME_AND_VALUE = "(" + VAR_NAME + ")(?:\\s*=\\s*(?:%s|(" + VAR_NAME + ")))?";
	public static final String VAR_DECL = "\\s*(final\\s+|)%s\\s+(" + VAR_NAME_AND_VALUE + "\\s*(?:,\\s*" + VAR_NAME_AND_VALUE + "\\s*)*);\\s*";
	
	public static final String VAR_ASSIGN = "\\s*([a-zA-Z]\\w*|_\\w+)\\s*=\\s*(?:%s|([a-zA-Z]\\w*|_\\w+))\\s*;\\s*";
	
	public static final String CHAR_VALUE = "'(.)'";
	public static final String BOOLEAN_VALUE = "([-+]?\\d*\\.?\\d+|[-+]?\\d+\\.|true|false)";
	public static final String DOUBLE_VALUE = "([-+]?\\d*\\.?\\d+|[-+]?\\d+\\.)";
	public static final String INT_VALUE = "([-+]?\\d+)";
	public static final String STRING_VALUE = "\"(.)*\"";
	
	public static final String regexChar = String.format(VAR_DECL, "char", CHAR_VALUE, CHAR_VALUE);
	public static final String regexBoolean = String.format(VAR_DECL, "boolean", BOOLEAN_VALUE, BOOLEAN_VALUE);
	public static final String regexDouble = String.format(VAR_DECL, "double", DOUBLE_VALUE, DOUBLE_VALUE);
	public static final String regexInt = String.format(VAR_DECL, "int", INT_VALUE, INT_VALUE);
	public static final String regexString = String.format(VAR_DECL, "String", STRING_VALUE, STRING_VALUE);

	public static final String regexNameChar = String.format(VAR_NAME_AND_VALUE, CHAR_VALUE);
	public static final String regexNameBoolean = String.format(VAR_NAME_AND_VALUE, BOOLEAN_VALUE);
	public static final String regexNameDouble = String.format(VAR_NAME_AND_VALUE, DOUBLE_VALUE);
	public static final String regexNameInt = String.format(VAR_NAME_AND_VALUE, INT_VALUE);
	public static final String regexNameString = String.format(VAR_NAME_AND_VALUE, STRING_VALUE);
	
	public static final String regexAssignChar = String.format(VAR_ASSIGN, CHAR_VALUE);
	public static final String regexAssignBoolean = String.format(VAR_ASSIGN, BOOLEAN_VALUE);
	public static final String regexAssignDouble = String.format(VAR_ASSIGN, DOUBLE_VALUE);
	public static final String regexAssignInt = String.format(VAR_ASSIGN, INT_VALUE);
	public static final String regexAssignString = String.format(VAR_ASSIGN, STRING_VALUE);
	
	public static final String regexAssign = String.format(VAR_DECL, "String", STRING_VALUE, STRING_VALUE);
}
