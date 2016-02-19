package clids.ex2.filescript.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import clids.ex2.filescript.InvalidParameterException;
import clids.ex2.filescript.toolbox.ToolBox;

/**
 * An action that copies a file to a specified target path.
 * This class implements the Action interface.
 */
public class CopyAction implements Action {
	private static final int START_OF_BUFFER = 0;
	private static final int END_OF_FILE = -1;
	private static final int BLOCK_SIZE = 500;

	/*
	 * The target directory.
	 */
	String target;

	/*
	 * The source directory.
	 */
	String sourceDir;

	/**
	 * CopyAction constructor
	 * @param sourceDir The source directory.
	 * @param target The target directory.
	 * @throws InvalidParameterException The target directory in an absolute path.
	 */
	public CopyAction(String sourceDir, String target) throws InvalidParameterException {
		if (target.startsWith(File.separator)) {
			throw new InvalidParameterException(
					"Copy target should not be absolute");
		}

		this.sourceDir = sourceDir;
		this.target = target;
	}

	/**
	 * Copies the file to the target directory.
	 * @param file The file to copy.
	 * @throws Exception Some error occured while copying.
	 */
	public void doAction(File file) throws Exception {
		String path = ToolBox.ConcatPaths(sourceDir, target);
		path = ToolBox.ConcatPaths(path, file.getName());

		File copyFile = new File(path);

		if (!file.canRead()) {
			throw new IOException("Permissions problem");
		}

		copyFile.getParentFile().mkdirs();
		copyFile.createNewFile();

		copyData(file.getAbsolutePath(), path);
	}

	/*
	 * Copies data from the source path to the target path.
	 * sourcePath - Path to the source file
	 * targetPath - Path to the target file
	 */
	private void copyData(String sourcePath, String targetPath) throws Exception {
		FileInputStream reader = null;
		FileOutputStream writer = null;

		try {
			reader = new FileInputStream(sourcePath);
			writer = new FileOutputStream(targetPath);

			byte [] target = new byte [BLOCK_SIZE];
			int read = 0;
			while ((read = reader.read(target)) != END_OF_FILE) {
				writer.write(target, START_OF_BUFFER, read);
			}
		}
		finally {
			writer.close();
			reader.close();
		}
	}
}
