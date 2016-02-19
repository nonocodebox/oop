package clids.ex2.filescript;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import clids.ex2.filescript.actions.Action;
import clids.ex2.filescript.actions.ActionFactory;
import clids.ex2.filescript.filters.Filter;
import clids.ex2.filescript.filters.FilterFactory;
import clids.ex2.filescript.orders.Order;
import clids.ex2.filescript.orders.OrderFactory;
import clids.ex2.filescript.sections.Section;

/**
 * A class which parses command files.

 */
public class Parser {
	private static final String FILTER = "FILTER";
	private static final String ORDER = "ORDER";
	private static final String ACTION = "ACTION";
	private static final String COMMENT_START = "@";

	private enum ParserMode {
		FILTER_STRING,
		FILTER_DATA,
		ACTION_STRING,
		ACTION_DATA,
		ORDER_STRING,
		ORDER_DATA
	}
	
	private String commandFile;
	private String sourceDir;
	private LinkedList<Section> sections;
	
	/**
	 * Parse constructor.
	 * @param commandFile The command file to parse.
	 * @param sourceDir The source directory to use. 
	 */
	public Parser(String commandFile, String sourceDir) {
		this.commandFile = commandFile;
		this.sourceDir = sourceDir;
		this.sections = new LinkedList<Section>();
	}
	
	/**
	 * Get the parsed command file sections. Should be called after parse().
	 * @return The command file sections.
	 */
	public Section[] getSections() {
		return sections.toArray(new Section[0]);
	}
	
	/**
	 * Parses the command file and creates sections.
	 * @throws ErrorException A type 2 Error occurred.
	 * @throws IOException An error occurred when trying to read the command file.
	 */
	public void parse() throws ErrorException, IOException {
		String line = null;
		Section section = null;
		int lineIndex = 0;
		ParserMode mode = ParserMode.FILTER_STRING;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		sections.clear();
		
		try {
			fileReader = new FileReader(commandFile);
			bufferedReader = new BufferedReader(fileReader);
			
			while ((line = bufferedReader.readLine()) != null) {
				lineIndex++;
				
				if (line.startsWith(COMMENT_START)) {
					if (section == null) {
						throw new BadFileFormatException("Found a comment before the first filter");
					}
					
					section.addComment(line);
					continue;
				}
				

				if (mode == ParserMode.FILTER_DATA) {
					if (line.equals(ACTION)) {
						mode = ParserMode.ACTION_STRING;
					}
				}
				else if (mode == ParserMode.ACTION_DATA) {
					if (line.equals(ORDER)) {
						mode = ParserMode.ORDER_STRING;
					}
					else if (line.equals(FILTER)) {
						mode = ParserMode.FILTER_STRING;
					}
				}
				
				switch (mode) {
				case FILTER_STRING:
					if (line.equals(FILTER)) {// start section
						mode = ParserMode.FILTER_DATA;
						section = new Section();
						
						sections.add(section);
					}
					else {
						throw new BadSectionNameException("Bad FILTER section name");
					}
					break;
					
				case FILTER_DATA:
					try {
						Filter filter = FilterFactory.createFilter(line);
						section.setFilter(filter);
						
						mode = ParserMode.ACTION_STRING;
					}
					catch (WarningException ex) {
						section.addWarning("Warning in line " + lineIndex);
					}
					break;
					
				case ACTION_STRING:
					if (line.equals(ACTION)) {
						mode = ParserMode.ACTION_DATA;
					}
					else {
						throw new BadSectionNameException("Bad ACTION section name");
					}
					break;
					
				case ACTION_DATA:
					try {
						Action action = ActionFactory.createAction(line, sourceDir);
						section.addAction(action, lineIndex);
					}
					catch (WarningException ex) {
						section.addWarning("Warning in line " + lineIndex);
					}
					break;
					
				case ORDER_STRING:
					if (line.equals(ORDER)) {
						mode = ParserMode.ORDER_DATA;
					}
					else {
						mode = ParserMode.FILTER_STRING;
					}
					break;
					
				case ORDER_DATA:
					try {
						Order order = OrderFactory.createOrder(line); 
						section.setOrder(order);
						mode = ParserMode.FILTER_STRING;
					}
					catch (WarningException ex) {
						section.addWarning("Warning in line " + lineIndex);
					}
					break;
				}
			}
			
			if (mode == ParserMode.FILTER_DATA || mode == ParserMode.ACTION_STRING) {
				throw new BadFileFormatException("File ended too soon");
			}
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
