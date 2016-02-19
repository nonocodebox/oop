package clids.ex2.filescript.sections;

import java.io.File;
import java.util.LinkedList;
import java.util.TreeSet;

import clids.ex2.filescript.actions.Action;
import clids.ex2.filescript.filters.DefaultFilter;
import clids.ex2.filescript.filters.Filter;
import clids.ex2.filescript.orders.AbsolutePathOrder;
import clids.ex2.filescript.orders.Order;

/**
 * A class which represents a single command file section.

 */
public class Section {
	/*
	 * Holds an action and a source line together.
	 */
	private class ActionSourceLine {
		Action action;
		int sourceLine;
		
		/*
		 * ActionSourceLine constructor.
		 */
		public ActionSourceLine(Action action, int sourceLine) {
			this.action = action;
			this.sourceLine = sourceLine;
		}
		
		/*
		 * Returns the action.
		 */
		public Action getAction() {
			return action;
		}
		
		/*
		 * Returns the source line.
		 */
		public int getSourceLine() {
			return sourceLine;
		}
	};
	
	private LinkedList<String> warnings;
	private LinkedList<String> comments;
	private Filter filter;
	private LinkedList<ActionSourceLine> actions;
	private Order order;
	
	/**
	 * Section default constructor.
	 */
	public Section() {
		// Create linked lists
		warnings = new LinkedList<String>();
		comments = new LinkedList<String>();
		actions = new LinkedList<ActionSourceLine>();
		
		// Set default order to AbsolutePathOrder
		order = new AbsolutePathOrder();
		
		// Set default filter to DefaultFilter
		filter = new DefaultFilter();
	}
	
	/**
	 * Sets this section's filter.
	 * @param filter The filter to use.
	 */
	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	
	/**
	 * Sets this section's order.
	 * @param order The order to use.
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	
	/**
	 * Adds an action.
	 * @param action The action to add.
	 * @param sourceLine The line number of the action in the commands file.
	 */
	public void addAction(Action action, int sourceLine) {
		this.actions.add(new ActionSourceLine(action, sourceLine));
	}
	
	/**
	 * Adds a warning.
	 * @param warning The warning to add.
	 */
	public void addWarning(String warning) {
		this.warnings.add(warning);
	}
	
	/**
	 * Adds a comment.
	 * @param comment The comment to add.
	 */
	public void addComment(String comment) {
		this.comments.add(comment);
	}
	
	/**
	 * Executes this section's actions and prints warnings and comments.
	 * @param sourceDir The source directory on which to execute. 
	 */
	public void execute(String sourceDir) {
		for (String warning : warnings) {
			System.out.println(warning);
		}
		
		for (String comment : comments) {
			System.out.println(comment);
		}
		
		TreeSet<File> orderTree = new TreeSet<File>(order);
		File dir = new File (sourceDir);
		File [] filteredFiles = dir.listFiles(filter);
		
		for (int i = 0; i < filteredFiles.length; i++) {
			orderTree.add(filteredFiles[i]);
		}
		
		RecursiveFiles(dir.listFiles(), orderTree);
		
		for (ActionSourceLine action : actions) {
			for (File file : orderTree) {
				try {
					action.getAction().doAction(file);
				}
				catch (Exception ex) {
					System.out.println("Action failed in line " + action.getSourceLine());
				}
			}
		}
		
	}

	/*
	 * Filters files recursively.
	 * allFiles - All the files and directories to filter.
	 * orderTree - A TreeSet containing the filtered files.
	 */
	private void RecursiveFiles(File[] allFiles, TreeSet<File> orderTree) {
		for (int i = 0; i < allFiles.length; i++) {
			if (allFiles[i].isDirectory()) {
				File [] files =  allFiles[i].listFiles(filter);
				
				for (int j = 0; j < files.length; j++) {
					orderTree.add(files[j]);
				}
				
				RecursiveFiles(allFiles[i].listFiles(), orderTree);
			}
		}
	}
}
