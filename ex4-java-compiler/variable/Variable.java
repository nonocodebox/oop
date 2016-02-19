package clids.ex4.variable;

import clids.ex4.main.Parser;

/**
 * A class that represents a variable.
 */
public class Variable {
	/**
	 * Type of variable
	 */
	public enum TypeVariable {
		CHAR,
		BOOLEAN,
		DOUBLE,
		INT,
		STRING;
		
		/**
		 * Returns the type's keyword.
		 * @return The type's keyword
		 */
		public String toString(){
			if (this == STRING){
				return "String";
			}
			else {
				return super.toString().toLowerCase();
			}
		}
	}
	
	private TypeVariable type; 
	private boolean isFinal; 
	private Object value;
	private String name;
	private boolean isInitialized;
	
	/**
	 * Constructs an initialized variable.
	 * @param type The variable's type
	 * @param isFinal Whether the variable is declared final
	 * @param value The variable's value string
	 * @param name The variable's name
	 * @throws Exception If an error occurred when creating the variable
	 */
	public Variable (TypeVariable type, boolean isFinal, String value, String name) throws Exception {
		this.isFinal = isFinal;
		this.type = type;
		this.name = name;
		this.isInitialized = false;
		
		setValue(value);
	}

	/**
	 * Constructs an uninitialized variable.
	 * @param type The variable's type
	 * @param isFinal Whether the variable is declared final
	 * @param name The variable's name
	 * @throws Exception If an error occurred when creating the variable
	 */
	public Variable (TypeVariable type, boolean isFinal, String name) {
		this.isFinal = isFinal;
		this.type = type;
		this.name = name;
		this.isInitialized = false;
		this.value = null;
	}
	
	/**
	 * Set whether the variable is initialized to a value.
	 * @param initialized Whether the variable is initialized
	 */
	public void setInitialized(boolean initialized) {
		this.isInitialized = initialized;
	}
	
	/**
	 * Parse and set the variable's value.
	 * @param value The variable's value string to parse
	 * @throws Exception If an error occurred while parsing
	 */
	public void setValue(String value) throws Exception {
		// Check the variable's type
		switch (this.type) {
		case CHAR:
			// Parse as a single character
			if (value.length() != 1) {
				throw new Exception("Invalid char value");
			}
			this.value = value.toCharArray()[0];
			break;
		case BOOLEAN:
			// Parse as boolean
			Parser.parseBoolean(value);
			break;
		case DOUBLE:
			// Parse as a double
			this.value = Double.parseDouble(value);
			break;
		case INT:
			// Parse as an integer
			this.value = Integer.parseInt(value);
			break;
		case STRING:
			// Set as a string
			this.value = value;
			break;
		default:
			break;
		}
	}

	/**
	 * Set the variable's value to another variable's value.
	 * @param var The source variable
	 */
	public void setValue(Variable var) {
		// Make sure the source variable is initialized
		if (var.isInitialized) {
			this.value = var.value;
		}
	}
	
	/**
	 * Gets the variable's value
	 * @return The variable's value
	 */
	public Object getValue() {
		return this.value;
	}

	/**
	 * Gets whether the variable is initialized to a value.
	 * @return Whether the variable is initialized
	 */
	public boolean getIsInitialized() {
		return this.isInitialized;
	}

	/**
	 * Gets the variable's name
	 * @return The variable's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the variable's type
	 * @return The variable's type
	 */
	public TypeVariable getType() {
		return this.type;
	}
	
	/**
	 * Gets whether the variable was declared final
	 * @return Whether the variable was declared final
	 */
	public boolean isFinal() {
		return this.isFinal;
	}
}
