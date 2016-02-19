package clids.ex2.filescript;

import clids.ex2.filescript.sections.Section;

/**
 * A class that manages the parsing of a command file, using a parser and sections.

 */
public class Manager {
	Parser parser;
	String sourceDir;
	
	/**
	 * Manager constructor.
	 * @param sourceDir The source directory to use.
	 * @param commandFile The command file to parse.
	 */
	public Manager(String sourceDir, String commandFile) {
		parser = new Parser(commandFile, sourceDir);
		this.sourceDir = sourceDir;
	}
	
	/**
	 * Execute the actions in the command file on filtered files in the source directory.
	 * @throws Exception An exception occured when opening or parsing the command file.
	 */
	public void execute() throws Exception {
		parser.parse();
		
		Section [] sections = parser.getSections();
		
		for (Section section : sections) {
			section.execute(sourceDir);
		}
	}
}
