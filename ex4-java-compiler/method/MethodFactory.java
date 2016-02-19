package clids.ex4.method;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clids.ex4.scope.ScopeFactory;
import clids.ex4.toolbox.MethodRegex;
import clids.ex4.toolbox.NumberedLine;

/**
 * Uses to create method objects and for queries about methods
 *
 */
public class MethodFactory {
	
	/**
	 * @param lines - the lines of the code
	 * @return an array of all the methods appear in the code file
	 * @throws MethodException if the scope declaration is not valid
	 */
	public static Method[] collectMethods(LinkedList<NumberedLine> lines) throws MethodException{

		//checks what should be the length of the array, and if there is a method defined within a scope
		int methodCounter = 0, bracketCounter = 0;
		for(NumberedLine line : lines){
			bracketCounter = 	ScopeFactory.scopeStarts(line) ? bracketCounter+1 :
								ScopeFactory.scopeEnds(line) ? bracketCounter-1 : bracketCounter ;
			//if encountered a method
			if (line.getLine().contains(MethodRegex.VOID)) {
				//if method is defined within a scope
				if (bracketCounter != 0)
					throw new MethodException("Method is defined within a scope", line.getLineNumber());
			}
			methodCounter++;
		}

		Method[] methods = new Method[methodCounter];
		//if there's no methods in the file returns a 0-length array
		if (methodCounter == 0) return methods;

		Pattern p = Pattern.compile(MethodRegex.METHOD_FULL);
		Matcher m = p.matcher("");
		methodCounter = 0;
		
		//actually collect the methods
		for (NumberedLine line : lines) {
			m.reset(line.getLine());
			if (m.matches()) {
				//method found - create an object
				Method newMethod = parse(line);
				//validate that a method name is not duplicated
				for ( int i=0 ; i<methodCounter ; i++ ){
					if( methods[i].getName().equals(newMethod.getName()) ) {
						throw new MethodException("2 methods with the same name", line.getLineNumber());
					}
				}
				methods[methodCounter++] = newMethod;
			}
		}

		return methods;

	}
	
	/**
	 * @param line the line in which the method is declared
	 * @return a single method object
	 * @throws MethodException if the scope delaration is not valid
	 */
	public static Method parse(NumberedLine line) throws MethodException {
		Pattern p = Pattern.compile(MethodRegex.METHOD_FULL);
		Matcher m = p.matcher(line.getLine());
		
		if (! m.matches()) throw new MethodException("Method declaration error", line.getLineNumber());

		//name
		String name = m.group(2);

		//params
		String[] paramNames = null;
		String[] paramTypes = null;
		if (m.group(3) != null) {
			String[] params = m.group(3).split(MethodRegex.COMMA);
			paramTypes = new String[params.length];
			paramNames = new String[params.length];
			for (int i=0; i<params.length; i++) {
				//get the type of the current param
				paramTypes[i] = params[i].split(MethodRegex.MUST_SPACE)[0];
				paramNames[i] = params[i].split(MethodRegex.MUST_SPACE)[1];
			}	
		}
		return new Method (name, paramTypes, paramNames );
	}
	
	/**
	 * @param line the line where the method starts
	 * @return true if a return phrase exist for this method, else if not
	 */
	public static boolean isReturn(NumberedLine line) {
		return Pattern.matches(MethodRegex.RETURN, line.getLine());
	}

	/**
	 * @param line a suspected for a method call
	 * @return if the line is a call for a method, any method
	 */
	public static boolean isMethodCall(NumberedLine line) {
		Pattern p = Pattern.compile(MethodRegex.METHOD_CALL);
		Matcher m = p.matcher (line.getLine());
		return m.matches();
	}
	
	
	/**
	 * 
	 * @param line were is a method call
	 * @param methods all the method found in the file
	 * @return the method which the call reffers to
	 * @throws MethodException if the call is invalid or the method name is not found
	 */
	public static Method getMethodRefferd(NumberedLine line, Method[] methods) throws MethodException {
		Pattern p = Pattern.compile(MethodRegex.METHOD_CALL);
		Matcher m = p.matcher (line.getLine());
		
		if (!m.matches()) {
			throw new MethodException("Invalid method call", line.getLineNumber());
		}
		
		//gets method name
		String methodName = m.group(1);
		
		for (Method method : methods) {
			//checks on the specific method if the call to it is legal
			if ( method.getName().equals(methodName) ) return method;
		}
		
		throw new MethodException("Invalid method name", line.getLineNumber());
	}
}
