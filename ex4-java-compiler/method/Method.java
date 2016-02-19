package clids.ex4.method;

import clids.ex4.main.*;
import clids.ex4.scope.LinkedScope;
import clids.ex4.toolbox.MethodRegex;
import clids.ex4.toolbox.NumberedLine;
import clids.ex4.toolbox.VariableRegex;

import java.util.regex.*;

/**
 * Represent a method within Sjava file
 * Represent the method but as a function, not as a scope.
 */
public class Method {

	private String name;
	private String[] paramTypes, paramNames;

	/**
	 * Create a new method object
	 * @param name - method name
	 * @param paramTypes - in the order they appear
	 * @param paramNames - in the order they appear
	 */
	public Method (String name, String[] paramTypes, String[] paramNames) {
		if ( paramTypes == null) { paramTypes = new String[0]; }
		if ( paramNames == null) { paramNames = new String[0]; }
		this.name=name;
		this.paramTypes=paramTypes;
		this.paramNames=paramNames;
	}
	
	/**
	 * @return method name
	 */
	public String getName (){
		return name;
	}
	
	/**
	 * @return paramTypes - in the order they appear
	 */
	public String[] getParamTypes () {
		return paramTypes;
	}
	
	/**
	 * @return paramNames - in the order they appea
	 */
	public String[] getParamNames () {
		return paramNames;
	}
	
	//checks if a call to this method is legal
	public boolean isCallLegal (NumberedLine line, LinkedScope current) {

		Pattern p = Pattern.compile(MethodRegex.METHOD_CALL);
		Matcher m = p.matcher (line.getLine());
		if( ! m.matches() ) return false;
		
		//group 2 = param_cmplx
		String[] userParams = m.group(2) != null ? m.group(2).split(MethodRegex.COMMA) : new String[0];
		if (userParams.length != paramTypes.length) { return false; }
		
		//get rid of space before and after the word
		for( int i = 0 ; i < userParams.length ; i++ ) {
			userParams[i] = userParams[i].trim();
		}

		//check all params
		//should be edited
		for ( int i = 0 ; i < userParams.length ; i++ ) {
			
			if ( userParams[i].matches(VariableRegex.INT_VALUE) )
				return paramTypes[i].matches("boolean"+"|"+"int");
	
			if ( userParams[i].matches(VariableRegex.DOUBLE_VALUE) )
				return paramTypes[i].matches("boolean"+"|"+"double");
			
			if ( userParams[i].matches(VariableRegex.BOOLEAN_VALUE))
				return paramTypes[i].matches("boolean");
			
			if ( userParams[i].matches(VariableRegex.STRING_VALUE) )
				return paramTypes[i].matches("String");
			
			if ( userParams[i].matches(VariableRegex.CHAR_VALUE) )
				return paramTypes[i].matches("char");
	
			//if there is a variable give, check its type
			if ( ! current.getVar(userParams[i]).getType().toString().equals(paramTypes[i]) ) { return false; }			
		}
		return true;
	}
}