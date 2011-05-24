/**
 * TODO
 */
package com.mbd.tools.geronimo;

import static com.mbd.tools.geronimo.GeronimoProjectCleaner.*;
import com.mbd.tools.geronimo.exc.CleanerException;

;

/**
 * @author MBD
 * 
 */
public class GeronimoProjectCleanerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean rowsDeleted = false;
		boolean cacheFolderDeleted = false;
		try {
			String geronimoLocation = getEnvironmentProp(GERONIMO_HOME);
			if (geronimoLocation != null) {
				rowsDeleted = clearConfigFile(geronimoLocation);
				cacheFolderDeleted = clearRepositoryDir(geronimoLocation);
			} else {
				throw new CleanerException("Environment variable not found: " + GERONIMO_HOME + ".");
			}

			System.out.println("Done! " + "Config file cleaned: " + rowsDeleted
					+ " Cache folder cleaned: " + cacheFolderDeleted);
		} catch (CleanerException e) {
			System.err.append(e.getMessage());
		}
	}

	/**
	 * Връща environment пропертитата
	 * 
	 * @return Properties
	 */
	private static String getEnvironmentProp(String propKey) {
		if (System.getenv().containsKey(propKey)) {
			return System.getenv(propKey);
		}
		return null;
	}
}
