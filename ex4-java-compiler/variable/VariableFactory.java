package clids.ex4.variable;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import clids.ex4.scope.LinkedScope;
import clids.ex4.toolbox.NumberedLine;
import clids.ex4.toolbox.VariableRegex;
import clids.ex4.variable.Variable.TypeVariable;

/**
 * A factory class for parsing variable declarations and assignments.
 */
public class VariableFactory {
	static private Pattern[] patterns = {
		Pattern.compile(VariableRegex.regexChar),
		Pattern.compile(VariableRegex.regexBoolean),
		Pattern.compile(VariableRegex.regexDouble),
		Pattern.compile(VariableRegex.regexInt),
		Pattern.compile(VariableRegex.regexString),
	};

	static private Pattern[] patternsName = {
		Pattern.compile(VariableRegex.regexNameChar),
		Pattern.compile(VariableRegex.regexNameBoolean),
		Pattern.compile(VariableRegex.regexNameDouble),
		Pattern.compile(VariableRegex.regexNameInt),
		Pattern.compile(VariableRegex.regexNameString),
	};
	
	static private Pattern[] patternsAssign = {
		Pattern.compile(VariableRegex.regexAssignChar),
		Pattern.compile(VariableRegex.regexAssignBoolean),
		Pattern.compile(VariableRegex.regexAssignDouble),
		Pattern.compile(VariableRegex.regexAssignInt),
		Pattern.compile(VariableRegex.regexAssignString),
	};

	static Pattern patternName = Pattern.compile(VariableRegex.VAR_NAME);
	
	/*
	 * Tries to create variables from a code line
	 * pattern: The pattern used to parse the whole line of variable declarations
	 * patternSingleVar: The pattern used to parse a single variable declaration
	 * line: The code line to parse
	 * scope: The code line's scope
	 * numberLine: The code line's number
	 * returns: A linked list of parsed variables
	 */
	private static LinkedList<Variable> createVariable(Pattern pattern, Pattern patternSingleVar, 
			String line, int typeInt, LinkedScope scope, int numberLine) throws Exception {
		Matcher matcher = pattern.matcher(line);
		LinkedList<Variable> vars = null;
		TypeVariable type = TypeVariable.values()[typeInt];
		
		// Check if the line matches the pattern
		if (matcher.matches()) {
			// Check if the declaration is final by checking that the first group is not empty
			boolean isFinal = (matcher.group(1) != null && !matcher.group(1).equals(""));
			
			// Create a linked list of variables
			vars = new LinkedList<Variable>();
			
			// Create a single variable name matcher
			matcher = patternSingleVar.matcher(matcher.group(2));
			
			// Loop over the variable declarations
			while (matcher.find()) {
				// Get the variable name
				String varName = matcher.group(1);
				
				// Create a variable object and add it to the list
				Variable var = new Variable(type, isFinal, varName);
				vars.add(var);
				
				// Check if the variable is initialized
				if (matcher.groupCount() >= 2 && matcher.group(2) != null) {
					// The variable is initialized to a constant value, parse it
					var.setInitialized(true);
					var.setValue(matcher.group(2));
				}
				else if (matcher.groupCount() >= 3 && matcher.group(3) != null) {
					// The variable is initialized to another variable
					String toVarName = matcher.group(3);
					Variable toVar = scope.getVar(toVarName);
					
					// Check if the other variable exists
					if (toVar == null) {
						throw new VariableException("Problem with assignment variable", numberLine);
					}
					else {
						// Make sure it was initialized
						if (toVar.getIsInitialized()) {
							var.setValue(toVar);
						}
						else {
							throw new VariableException("Problem with initialization", numberLine);
						}
					}
				}
				else if (isFinal) // Check that final variables are initialized
				{
					throw new VariableException("Uninitialized final variable", numberLine);
				}
			}
		}
		
		return vars;
	}
	
	/**
	 * Parses a variable declaration line
	 * @param numberLine The code line
	 * @param scope The line's scope
	 * @return A linked list of parsed variables
	 * @throws Exception If an error occurred while parsing
	 */
	public static LinkedList<Variable> parseVariable(NumberedLine numberLine, LinkedScope scope) throws Exception {
		String  line = numberLine.getLine();
		
		LinkedList<Variable> vars = null;
		
		// Loop over all variable types
		for (int i = 0; i < TypeVariable.values().length; i++) {
			// Try creating variables of this type
			vars = createVariable(patterns[i], patternsName[i], line, i, scope, numberLine.getLineNumber());
			if (vars != null) {
				// We found the right type
				break;
			}
		}
		
		if (vars == null) {
			throw new VariableException("Could not parse variable declaration", numberLine.getLineNumber());
		}
		
		return vars;
	}
	
	/**
	 * Creates a method parameter variable
	 * @param typeName The variable's type name string
	 * @param varName The variable's name
	 * @return The created variable
	 * @throws Exception If an error occurred when creating the variable
	 */
	public static Variable methodParameter(String typeName, String varName) throws Exception {
		TypeVariable type;
		
		// Parse the type name
		switch (typeName) {
		case "char":
			type = TypeVariable.CHAR;
			break;
			
		case "boolean":
			type = TypeVariable.BOOLEAN;
			break;
			
		case "double":
			type = TypeVariable.DOUBLE;
			break;
			
		case "int":
			type = TypeVariable.INT;
			break;
			
		case "String":
			type = TypeVariable.STRING;
			break;
			
		default:
			// Unknown type
			throw new Exception("Unknown method variable type");	
		}
		
		// Create a variable object
		Variable var = new Variable(type, false, varName);
		
		// Set as initialized
		var.setInitialized(true);
		
		// Return
		return var;
	}

	/**
	 * Checks if a code line is a variable declaration line
	 * @param line The line to check
	 * @return true if the line is a variable declaration, false otherwise
	 */
	public static boolean isVaraible(NumberedLine line) {
		Matcher matcher;
		
		// Loop over variable types
		for (int i = 0; i < TypeVariable.values().length; i++) {
			matcher = patterns[i].matcher(line.getLine());
			
			// Check if the type matches
			if (matcher.find()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if a code line is a variable assignment line
	 * @param line The line to check
	 * @return true if the line is a variable assignment, false otherwise
	 */
	public static boolean isAssignment(NumberedLine line) {
		Matcher matcher;
		
		// Loop over variable types
		for (int i = 0; i < patternsAssign.length; i++) {
			matcher = patternsAssign[i].matcher(line.getLine());
			
			// Check if the type matches
			if (matcher.matches()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Parses a variable assignment line
	 * @param line The line to parse
	 * @param currentScope The line's scope
	 * @throws VariableException If an error occurred while parsing
	 */
	public static void parseAssignment(NumberedLine line, LinkedScope currentScope) throws VariableException {
        Matcher matcher;
        
        // Loop over assignment patterns
        for (int i = 0; i < patternsAssign.length; i++) {
            matcher = patternsAssign[i].matcher(line.getLine());
            
            // Check if the pattern matches
            if (matcher.matches()) {
            	// Get the destination variable
                Variable first = currentScope.getVar(matcher.group(1));
                
                // Check that it exists and is not final
                if (first == null || first.isFinal()) {
                	throw new VariableException("", 0);
                }
                
                // Check that the variable's type matches the pattern's type
                if (first.getType().ordinal() != i) {
                	continue;
                }
                
                if (matcher.group(2) != null) {
                	// This is an assignment of a constant value, nothing else to check
                	// (the pattern checks the value's validity)
                	return;
                }
                else if (matcher.group(3) != null) {
                	// This is an assignment of another variable
                	// Get the source variable
                	String secondVarName = matcher.group(3);
					Variable second = currentScope.getVar(secondVarName);
                    
					// Check that it exists and its type matches
					if (second == null || second.getType() != first.getType()) {
                    	throw new VariableException("Bad variable assignment", line.getLineNumber());
                    }
					
					// Done
                    return;
                }
                
                throw new VariableException("Unknown variable assignment", line.getLineNumber());
            }
        }

    	throw new VariableException("Unknown variable assignment", line.getLineNumber());
	}
	
}
