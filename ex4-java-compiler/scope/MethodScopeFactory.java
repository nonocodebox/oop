package clids.ex4.scope;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clids.ex4.main.Parser;
import clids.ex4.method.MethodFactory;
import clids.ex4.toolbox.MethodRegex;
import clids.ex4.toolbox.NumberedLine;
import clids.ex4.variable.Variable;
import clids.ex4.variable.VariableFactory;

/**
 * 
 *  Uses to create scopes objects represent methods
 *
 */
public class MethodScopeFactory {

	/**
	 * Parse a given line in the file as a method scope and returns it
	 * @param parent scope
	 * @param lines of the whole file
	 * @param firstLine where the scope starts
	 * @param lastLine where the scope ends
	 * @return new LinkedScope
	 * @throws ScopeException if there's no return phrase, if the scope is not closed
	 */
	public static LinkedScope parse (LinkedScope parent, LinkedList<NumberedLine> lines, NumberedLine firstLine,
			NumberedLine lastLine) throws Exception {

		//check there's a return phrase
		if ( ! checkReturnPhrase(lines, firstLine, lastLine) )
			throw new MethodScopeException ("No return phrase", firstLine.getLineNumber());

		LinkedScope newScope = new LinkedScope (parent, lines, firstLine, lastLine);

		//initialize params
		Matcher paramsMatcher = Pattern.compile( MethodRegex.PARAM ).matcher( firstLine.getLine() );
		while ( paramsMatcher.find() ){
			String param[] = paramsMatcher.group().split(MethodRegex.MUST_SPACE);
			Variable var = VariableFactory.methodParameter(param[0], param[1] );
			newScope.addVar(var);
		}
		
		//verifies that a method scope is not encapsulated within another scope
		if (parent.getParent() != null) {
			throw new ScopeException("No methods allowed outside the root scope", firstLine.getLineNumber());
		}
		
		return newScope;
	}
	
	/**
	 * Checks a return phrase exist
	 * @param lines of the whole file
	 * @param firstLine where the scope starts
	 * @param lastLine where the scope ends
	 * @return true if a return phrase exists, false otherwise
	 */
	private static boolean checkReturnPhrase(LinkedList<NumberedLine> lines, NumberedLine firstLine,
			NumberedLine lastLine) {
		
		NumberedLine prevLine = null;
		int scopeLevel = 0;
		
		for (NumberedLine line : lines) {
			// Skip empty lines and comments
			if (Parser.isEmpty(line) || Parser.isComment(line)) {
				continue;
			}
			
			// Check scope start/end
			if (ScopeFactory.scopeStarts(line)) {
				scopeLevel++;
			}
			else if (ScopeFactory.scopeEnds(line)) {
				scopeLevel--;
				
				// If back to scope 0 check the previous code line
				if (scopeLevel == 0) {
					if (prevLine == null || !MethodFactory.isReturn(prevLine)) {
						return false;
					}
					
					return true;
				}
			}
			
			prevLine = line;
		}
		
		// Probably unclosed scope
		return false;
	}
}
