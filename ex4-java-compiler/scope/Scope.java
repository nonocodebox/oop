package clids.ex4.scope;

import java.util.LinkedList;

import clids.ex4.toolbox.NumberedLine;
import clids.ex4.variable.Variable;

/**
 * Represent a scope, (not linked one)
 *
 */
public abstract class Scope {
	
	protected LinkedList<Variable> vars;
	protected NumberedLine firstLine, lastLine;//not sure if needed at all
	
	/**
	 * Creates a new scope
	 * @param lines of the whole file
	 * @param firstLine where the scope begins
	 * @param lastLine where the scope ends
	 */
	public Scope(LinkedList<NumberedLine> lines, NumberedLine firstLine, NumberedLine lastLine) {
		vars = new LinkedList<Variable>();
		this.firstLine = firstLine;
		this.lastLine = lastLine;
	}

	/**
	 * Checks if a variable is declared within a scope
	 * @param searchVar the variable to find
	 * @return true if is found, else otherwise
	 */
	public boolean contains(String searchVar){
		for(Variable var : vars){
			if(var.getName().equals(searchVar)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param searchVar to find
	 * @return the variable, if is declared within the scope. null otherwise
	 */
	public Variable getVar(String searchVar){ 
		for(Variable var : vars){
			if(var.getName().equals(searchVar)){
				return var;
			}
		}
		return null;
	}
	
	/**
	 * @param newVar to add to the scope
	 * @throws ScopeException if the variable with the same name already declared in this scope
	 */
	public void addVar(Variable newVar) throws ScopeException {
		if ( contains(newVar.getName()) )
			throw new ScopeException("This scope owns 2 variables with the same", firstLine.getLineNumber());
		vars.addLast(newVar);
	}

	/**
	 * @return scope first line
	 */
	public NumberedLine getFirstLine(){
		return firstLine;
	}

	/**
	 * @return scope last line
	 */
	public NumberedLine getLastLine(){
		return lastLine;
	}

}