package clids.ex4.toolbox;


/**
 * A class containing regular expressions used for method parsing.
 */
public class MethodRegex {
	
	public static final String MUST_SPACE = "\\s++", MAYBE_SPACE = "\\s*+";
	public static final String OPEN_BRACKET = "\\(", CLOSE_BRACKET = "\\)";
	public static final String OPEN_BLOCK = "\\{", COMMA = ",{1}+", SEMICOLON = ";{1}+";

	public static final String VOID = "(void){1}+", METHOD_NAME = "([A-Za-z]\\w*+)";
	public static final String FINAL = "(final)+";
	public static final String TYPE = "(String|boolean|int|double|char){1}+", VAR_NAME = "(_\\w++|[a-zA-Z]\\w*+)";
	public static final String RETURN = MAYBE_SPACE+"return;";

	public static final String PARAM = "("+FINAL+MUST_SPACE+")?" + TYPE + MUST_SPACE + VAR_NAME;
	public static final String PARAM_CMPLX = "(" + PARAM + "("+ MAYBE_SPACE + COMMA + MAYBE_SPACE + PARAM +")*)";

	public static final String METHOD_STRT = MAYBE_SPACE + VOID + MUST_SPACE + METHOD_NAME + MAYBE_SPACE;//groups 1,2
	public static final String METHOD_MID = OPEN_BRACKET + MAYBE_SPACE + PARAM_CMPLX+"?" + MAYBE_SPACE + CLOSE_BRACKET;//group 3
	public static final String METHOD_END = MAYBE_SPACE + OPEN_BLOCK + MAYBE_SPACE;
	public static final String METHOD_FULL = METHOD_STRT + METHOD_MID + METHOD_END;
	
	public static final String VALUE = "("+ VariableRegex.INT_VALUE+"|" +VariableRegex.DOUBLE_VALUE+"|"+
											VariableRegex.CHAR_VALUE+"|"+VariableRegex.STRING_VALUE+"|"+
											VariableRegex.BOOLEAN_VALUE +")";
	public static final String INPUT = "(" + VALUE + "|" + VAR_NAME + ")";
	public static final String INPUT_CMPLX = "(" + INPUT + "("+ MAYBE_SPACE + COMMA + MAYBE_SPACE + INPUT +")*)";

	public static final String METHOD_CALL = MAYBE_SPACE + METHOD_NAME + MAYBE_SPACE+ OPEN_BRACKET + MAYBE_SPACE +
			INPUT_CMPLX+"?" + MAYBE_SPACE + CLOSE_BRACKET + MAYBE_SPACE + SEMICOLON + MAYBE_SPACE;

}
