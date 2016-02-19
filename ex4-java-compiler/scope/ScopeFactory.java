package clids.ex4.scope;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clids.ex4.toolbox.ConditionalRegex;
import clids.ex4.toolbox.MethodRegex;
import clids.ex4.toolbox.NumberedLine;

/**
 *  Used to create scope objects and for queries about scopes
 */
public class ScopeFactory {
	
	static Pattern methodPattern = Pattern.compile( MethodRegex.METHOD_FULL );
	static Pattern conditionPattern = Pattern.compile( ConditionalRegex.CON_FULL );
	
	/**
	 * Parse a given line in the file as a scope and returns it
	 * @param lines of the whole file
	 * @param firstLine where the scope starts
	 * @param parent of this scope
	 * @return the new scope
	 * @throws Exception due to many problems in the scope definaition. (see readme)
	 */
	public static LinkedScope parse (LinkedList<NumberedLine> lines, NumberedLine firstLine, LinkedScope parent)
			throws Exception {
		
		NumberedLine lastLine = findLastLine(lines, firstLine);
		
		//if scope is not closed
		if(lastLine == null)
			throw new ScopeException("block not closed", firstLine.getLineNumber());
		
		//if the scope represents a method
		Matcher methodMatcher = methodPattern.matcher( firstLine.getLine() );
		if ( methodMatcher.matches() )
			return MethodScopeFactory.parse( parent, lines, firstLine, lastLine);
		
		//if this scope represent a conditional scope
		Matcher conditionMatcher = conditionPattern.matcher( firstLine.getLine() );
		if ( conditionMatcher.matches() )
			return ConditionalScopeFactory.parse( parent, lines, firstLine, lastLine);
		
		//if non of the above matches
		else
			throw new ScopeException("unidentifined scope type", firstLine.getLineNumber());
	}
	
	/**
	 * @param lines of the whole file
	 * @param firstLine of the scope
	 * @return last line of the scope
	 */
	private static NumberedLine findLastLine(LinkedList<NumberedLine> lines, NumberedLine firstLine){
		
		int counter = 1;
		ListIterator<NumberedLine> current = lines.listIterator( firstLine.getLineNumber() + 1 );
		NumberedLine lastLine = null;
		
		//count brackets
		while( current.hasNext() && counter != 0 ){
			lastLine = current.next();
			counter = scopeStarts(lastLine)? counter+1 : 
					( scopeEnds(lastLine)? counter-1 : counter ) ;
		}

		return counter == 0 ? lastLine : null;
	}

	/**
	 * @param line
	 * @return true if the line is a scope start. false otherwise
	 */
	public static boolean scopeStarts(NumberedLine line) {
		
		Matcher methodMatcher = methodPattern.matcher( line.getLine() );
		if ( methodMatcher.matches() ) { return true; }
		
		Matcher conditionMatcher = conditionPattern.matcher( line.getLine() );
		if ( conditionMatcher.matches() ) { return true; }
		
		return false;
	}
	
	/**
	 * @param line
	 * @return true if the line is a scope end. false otherwise
	 */
	public static boolean scopeEnds(NumberedLine line) {
		return line.getLine().trim().compareTo("}") == 0;
	}
}
