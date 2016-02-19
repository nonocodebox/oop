package clids.ex4.scope;

import java.util.LinkedList;

import clids.ex4.toolbox.NumberedLine;
import clids.ex4.variable.Variable;

/**
 * Represent a scope conected to other scopes, and therefore is a part in a tree of scopes
 */
public class LinkedScope extends Scope {
	
	private LinkedScope parent;
	private LinkedList<LinkedScope> children;
	
	/**
	 * Constructor for LinkedScope.
	 * @param parent the parent of this scope
	 * @param lines the lines of the whole file
	 * @param firstLine the line where the scope begins
	 * @param lastLine the line where the scope ends
	 */
	public LinkedScope(LinkedScope parent, LinkedList<NumberedLine> lines, NumberedLine firstLine,
			NumberedLine lastLine) {
		
		super (lines, firstLine, lastLine);
		this.parent = parent;
		this.children = new LinkedList<LinkedScope>();
		
		if (parent != null) {
			parent.children.add(this);
		}
	}

	/**
	 * @return parent of this scope
	 */
	public LinkedScope getParent () {
		
		return parent;
	}
	
	/**
	 * Adds a variable for this scope
	 * Before that - checks that the name of variable doesn't exist in the current scope, and go upwards to check
	 * it doesn't exist in none of the scopes above.
	 * (Expect - of members, which can have the same name as a variable)
	 *  @param Variable object to add
	 *  @throws an exception if the variable name exists already
	 */
	@Override
	public void addVar(Variable newVar) throws ScopeException{

		LinkedScope pointer = this;
		while ( pointer != null) {
			
			if ( pointer.contains(newVar.getName()) ) 
				throw new ScopeException("This scope owns 2 variables with the same", firstLine.getLineNumber());
			
			pointer = pointer.getParent();
			//the condition below means that we started from a not-root Scope, and if we now reached the root,
			//there's no need to check the members names, because a var can has a member name, it's ok
			if (pointer != null && pointer.isRoot()) { pointer = null; }
		}

		vars.addLast(newVar);
	}

	/**
	 * @param a String value which is the variable name
	 * @return if found, in this scope or in one above, return the expected Variable object. Else returns null.
	 */
	@Override
	public Variable getVar(String searchVar){ 
		
		LinkedScope pointer = this;
		while ( pointer != null) {
			for(Variable var : pointer.vars){
				if(var.getName().equals(searchVar)){
					return var;
				}
			}
			pointer = pointer.getParent();
		}
		return null;
	}

	/**
	 * @return true if this is the root scope. false otherwise 
	 */
	public boolean isRoot (){
		
		return parent == null? true : false ;
	}
	
	/**
	 * @param newVars - list of variables to add to the scope
	 * @throws ScopeException if one of the variable names already exists
	 */
	public void addVars(LinkedList<Variable> newVars) throws ScopeException {
		
		for (Variable v : newVars) {
			this.addVar(v);
		}
	}
}
