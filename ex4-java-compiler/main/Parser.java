package clids.ex4.main;

import java.util.LinkedList;
import java.util.regex.Pattern;

import clids.ex4.method.Method;
import clids.ex4.method.MethodException;
import clids.ex4.method.MethodFactory;
import clids.ex4.scope.*;
import clids.ex4.toolbox.MethodRegex;
import clids.ex4.toolbox.NumberedLine;
import clids.ex4.variable.Variable;
import clids.ex4.variable.VariableFactory;

/**
 * Parser class for parsing s-java files
 */
public class Parser {
	
	/**
	 * Parses a boolean string
	 * @param str The string to parse
	 * @return The boolean value
	 * @throws Exception If the value is not a valid boolean
	 */
	public static boolean parseBoolean(String str) throws Exception {
		if (str.equals("true")) {
			return true;
		}
		else if (str.equals("false")) {
			return false;
		}
		
		double doubleValue = Double.parseDouble(str);
		
		return (doubleValue > 0);
	}
	
	/**
	 * Checks if a code line is empty
	 * @param line The line to check
	 * @return true if the line is empty, false otherwise
	 */
	public static boolean isEmpty(NumberedLine line) {
		return line.getLine().trim().compareTo("") == 0;
	}

	/**
	 * Checks if a code line is a comment
	 * @param line The line to check
	 * @return true if the line is a comment, false otherwise
	 */
	public static boolean isComment(NumberedLine line) {
		return line.getLine().trim().startsWith("//");
	}

	/**
	 * Checks if a code line is a return statement
	 * @param line The line to check
	 * @return true if the line is a return statement, false otherwise
	 */
	private static boolean isReturn (NumberedLine line) {
		Pattern p = Pattern.compile(MethodRegex.RETURN);
		return p.matcher(line.getLine()).matches();
	}

	/**
	 * Parses an s-java file's contents
	 * @param lines The lines to parse
	 * @param root The root scope of the code
	 * @throws Exception If an error occurred while parsing
	 */
	public static void parse(LinkedList<NumberedLine> lines, LinkedScope root) throws Exception {
		LinkedScope currentScope = root;
		int scopeLevel = 0;
		
		// Collect all the method declarations
		Method[] methods = MethodFactory.collectMethods(lines);
		
		for (NumberedLine line : lines) {
			// Check scope start/end
			if (ScopeFactory.scopeStarts(line)) {
				scopeLevel++;
				continue;
			}
			else if (ScopeFactory.scopeEnds(line)) {
				scopeLevel--;
									
				if (scopeLevel < 0) {
					throw new ParserException ("Too many scopes closed", line.getLineNumber());
				}
				
				continue;
			}
			
			// Skip lines not in the root scope
			if (scopeLevel > 0) {
				continue;
			}
			
			// Skip empty lines and comments
			if (isEmpty(line) || isComment(line) || isReturn(line)) {
				continue;
			}
			
			// Allow only variable declarations in the root scope
			else if (VariableFactory.isVaraible(line)) {
				LinkedList<Variable> var = VariableFactory.parseVariable(line, currentScope);
				root.addVars(var);
			}
			else
			{
				throw new ParserException ("Unknown line",line.getLineNumber());
			}
		}
		
		if (scopeLevel > 0) {
			throw new ParserException ("Unclosed scope",lines.size());
		}
		
		for (NumberedLine line : lines) {
			// Check scope start/end
			if (ScopeFactory.scopeStarts(line)) {//to be fixed
				scopeLevel++;
				LinkedScope childScope = ScopeFactory.parse(lines, line, currentScope);
				currentScope = childScope;
				continue;
			}
			else if (ScopeFactory.scopeEnds(line)) {
				scopeLevel--;
				currentScope = currentScope.getParent();

				if (currentScope == null || scopeLevel < 0) {
					throw new ParserException("Too many scopes closed", line.getLineNumber());
				}
				
				continue;
			}
			
			// Skip lines in the root scope
			if (scopeLevel <= 0) {
				continue;
			}

			// Skip empty lines and comments
			if (isEmpty(line) || isComment(line))
			{
				continue;
			}
			
			// Allow variable declarations, assignments, return statements and method calls
			if (isReturn(line))
			{
				continue;
			}
			else if (VariableFactory.isVaraible(line)) {
				LinkedList<Variable> var = VariableFactory.parseVariable(line, currentScope);
				currentScope.addVars(var);
			}
			else if (VariableFactory.isAssignment(line)) {
				VariableFactory.parseAssignment(line, currentScope);
			}
			else if (MethodFactory.isMethodCall(line)) { // A method call
				if ( ! MethodFactory.getMethodRefferd(line, methods).isCallLegal(line, currentScope) )
					throw new MethodException("Call is not legal", line.getLineNumber());
			}
			else
			{
				throw new ParserException ("Unknown line",line.getLineNumber());
			}
		}
		
		if (!currentScope.equals(root)) {
			throw new ParserException("Unclosed scope",lines.size());
		}
	}
}