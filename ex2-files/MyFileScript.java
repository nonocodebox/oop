package clids.ex2.filescript;

/**
 * A class which holds the main method.

 */
public class MyFileScript {
	/**
	 * The main method.
	 * Type 2 errors are caught and printed here.
	 * @param args The command-line program arguments
	 */
	public static void main(String[] args) {
		try {
			if (args.length != 2) {
				throw new ProgramArgumentsException("Arguments error");
			}
			
			Manager manager = new Manager(args[0], args[1]);
			
			manager.execute();
		}
		catch (Exception ex) {
			System.err.println("ERROR");
			System.exit(-1);
		}
	}

}
