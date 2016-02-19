package clids.ex4.toolbox;

/**
 * Represents a line of code with its line number.
 */
public class NumberedLine {
	private String line;
	private int lineNumber;
	
	/**
	 * NumberedLine constructor
	 * @param line The code line
	 * @param lineNumber The line number
	 */
	public NumberedLine(String line, int lineNumber) {
		this.line = line;
		this.lineNumber = lineNumber;
	}
	
	/**
	 * Gets the line's content
	 * @return The line's content
	 */
	public String getLine() {
		return line;
	}
	
	/**
	 * Gets the line's number
	 * @return The line's number
	 */
	public int getLineNumber() {
		return lineNumber;
	}
}
