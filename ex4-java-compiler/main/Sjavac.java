package clids.ex4.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import clids.ex4.scope.LinkedScope;
import clids.ex4.scope.ScopeException;
import clids.ex4.toolbox.NumberedLine;

/**
 * The program's main class
 */
public class Sjavac {
	/**
	 * The main program entry point
	 * @param args The command line arguments passed to the program
	 */
	public static void main(String[] args) {
		// Default result is 0 (OK)
		int res=0;
		
		try {
			// Convert the code file to numbered lines
			LinkedList<NumberedLine> lines = covertFileToStrings(args[0]);
		
			// Create a root scope
			LinkedScope root = new LinkedScope(null, lines, lines.getFirst(), lines.getLast());

			// Parse the code file
			Parser.parse(lines, root);
		}
		catch (IOException e) {
			// Return 2 for IOException
			res = 2;
			System.out.println(e.toString());
		}
		catch (Exception e) {
			// Return 1 for code exception
			res = 1;
			System.out.println(e.toString());
		}
		
		System.exit(res);
	}
	
	/**
	 * Converts a file to code line strings
	 * commandFile: The file to convert
	 * returns: A linked list of the numbered code lines
	 */
	private static LinkedList<NumberedLine> covertFileToStrings(String commandFile) throws IOException, ScopeException {
		String line = null;
		int lineIndex = 0;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		// Create a new lined list
		LinkedList<NumberedLine> lines = new LinkedList<>();
		
		try {
			// Create a FileReader and BufferedReader to read the file
			fileReader = new FileReader(commandFile);
			bufferedReader = new BufferedReader(fileReader);
			
			// Read the file line by line and add the lines to the list
			while ((line = bufferedReader.readLine()) != null) {
				lineIndex++;
				lines.add(new NumberedLine(line, lineIndex));
			}
			
			// Return the lines read
			return lines;
		}
		
		finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			
			if (fileReader != null) {
				fileReader.close();
			}
		}
	}
}
