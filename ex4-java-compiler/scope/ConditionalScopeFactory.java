package clids.ex4.scope;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clids.ex4.toolbox.ConditionalRegex;
import clids.ex4.toolbox.NumberedLine;
import clids.ex4.variable.Variable;
import clids.ex4.variable.Variable.TypeVariable;

/**
 *  Uses to create scope objects represent if/while scopes
 */
public class ConditionalScopeFactory {
	
	/**
	 * Parse a given line in the file as a if/while scope and returns it
	 * @param parent scope
	 * @param lines of the whole file
	 * @param firstLine where the scope begins
	 * @param lastLine where the scope ends
	 * @return the new LinkedScope
	 * @throws ScopeException if the condition is invalid, if the scope is defined under the root scope and not
	 *  within a method, if the scope is not closed
	 */
	public static LinkedScope parse (LinkedScope parent, LinkedList<NumberedLine> lines, NumberedLine firstLine,
			NumberedLine lastLine) throws ScopeException {

		//validates it is a conditional scope
		Matcher conditionMatcher = Pattern.compile(ConditionalRegex.CON_FULL).matcher(firstLine.getLine());
		if (!conditionMatcher.matches()) {
			throw new ScopeException("Illegal condition", firstLine.getLineNumber());
		}
		
		//gets the condition itself - maybe complex condition
		String complicatedCondition = conditionMatcher.group(4);
		conditionMatcher = Pattern.compile(ConditionalRegex.CONDITION).matcher(complicatedCondition);

		//validate every minor condition within the complex condition
		while (conditionMatcher.find()) {
			
			//get the minor condition
			String singleCondition = conditionMatcher.group();

			if (conditionMatcher.group(6) != null) {
			
				//if the condition doesn't exist
				Variable var = parent.getVar(singleCondition);
				if ( var == null) { 
					throw new ScopeException("reference to a non-existing var", firstLine.getLineNumber());
				}
				
				//if the variable not initialized
				if (!var.getIsInitialized()) {
					throw new ScopeException("reference to a non-initialized var", firstLine.getLineNumber());				
				}
				
				//if the variable is not of the right type
				if (var.getType() != TypeVariable.BOOLEAN && 
						var.getType() != TypeVariable.INT && 
						var.getType() != TypeVariable.DOUBLE) {
					throw new ConditionalScopeException
					("reference to a non-boolean and non-integer var", firstLine.getLineNumber());
				}
			}
			
			//if trying to create a conditional scope not within a method scope
			if (parent.getParent() == null) {
				throw new ConditionalScopeException
				("No conditionals allowed in root scope", firstLine.getLineNumber());
			}

		}
		
		return new LinkedScope (parent, lines, firstLine, lastLine);
	}
	
}
