package clids.ex4.toolbox;


/**
 * A class containing regular expressions used for conditional scope parsing.
 */
public class ConditionalRegex {

	public static final String MUST_SPACE = "\\s++", MAYBE_SPACE = "\\s*+";
	public static final String OPEN_BRACKET = "\\(", CLOSE_BRACKET = "\\)";
	public static final String OPEN_BLOCK = "\\{", COMMA = ",", SEMICOLON = ";";

	public static final String VAR_NAME = "(_\\w|[a-zA-Z]\\w*+)";
	
	public static final String WHILE = "(while){1}+", IF = "(if){1}+";
	public static final String OR = "\\|\\|", AND = "&&";
	public static final String TRUE = "(true){1}+", FALSE="(false){1}+";
	public static final String TRUE_STRING = "true", FALSE_STRING="false";
	
	public static final String CONDITION=
			"("+VariableRegex.INT_VALUE+"|"+VariableRegex.DOUBLE_VALUE+"|"+TRUE+"|"+FALSE+"|"+VAR_NAME+")";
	//group 4 - condition itself
	public static final String CONDITION_CMPLX=
			"("+CONDITION+MAYBE_SPACE+"(("+OR+"|"+AND+")"+MAYBE_SPACE+CONDITION+MAYBE_SPACE+")*)";
	//groups 1,2,3 - determine if/while
	public static final String CON_STRT=MAYBE_SPACE+"("+WHILE+"|"+IF+")"+MAYBE_SPACE;
	//group 4 - condition itself
	public static final	String CON_MID=OPEN_BRACKET+MAYBE_SPACE+CONDITION_CMPLX+MAYBE_SPACE+CLOSE_BRACKET;
	public static final String CON_END=MAYBE_SPACE+OPEN_BLOCK+MAYBE_SPACE;
	public static final String CON_FULL=CON_STRT+CON_MID+CON_END;

}
