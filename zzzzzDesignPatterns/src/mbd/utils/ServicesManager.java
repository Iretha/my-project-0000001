/**
 * 
 */
package mbd.utils;

import org.apache.log4j.Logger;

/**
 * @author developer0024
 * 
 */
public class ServicesManager {

	private static ServicesManager instance = null;

	private static Logger log = null;

	/**
	 * Thread-safe implementation for multi-threading use.
	 * 
	 * @return MySingleton
	 */
	public static synchronized ServicesManager getInstance() {
		if (instance == null) {
			instance = new ServicesManager();
		}
		return instance;
	}

	/**
	 * Hidden Constructor
	 */
	private ServicesManager() {
		super();
		log = Logger.getLogger(getClass());
	}

	/**
	 * 
	 * @return Logger
	 */
	public Logger getLog() {
		return log;
	}

}
