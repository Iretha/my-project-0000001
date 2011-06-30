/**
 * 
 */
package mbd.creational.singleton;

import mbd.utils.ServicesManager;

/**
 * <b>Design Pattern Name: </b> Singleton Pattern <br>
 * <b>Summary: </b> Singleton Pattern is used when we need only one instance for
 * a class at specific time. <br>
 * Usually singletons are used for centralized management of internal or
 * external resources and they provide a global point of access to themselves. <br>
 * <b>Main purposes: </b> <br>
 * <li>Ensure that only one instance of a class is created. <br> <li>Provide a
 * global point of access to the object. <br>
 * <b>Applicability & Examples </b><br> <li>Logger Classes <li>Configuration
 * Classes <li>Accesing resources in shared mode <li>Factories implemented as
 * Singletons
 * 
 * @author developer0024
 */
public class MySingleton {

	/**
	 * Single instance for the class
	 */
	private static MySingleton instance = null;

	/**
	 * Thread-safe implementation for multi-threading use.
	 * 
	 * @return MySingleton
	 */
	public static synchronized MySingleton getInstance() {
		if (instance == null) {
			instance = new MySingleton();
		}
		return instance;
	}

	/**
	 * Hidden default constructor
	 */
	private MySingleton() {
		super();
		ServicesManager.getInstance().getLog()
				.info(getClass().getSimpleName() + ": First time instantiation.");
	}

	/**
	 * Do Specific Business
	 * 
	 * @return random long
	 */
	public long doMySpecificStuff() {
		/*
		 * Example implementations: -Logger Classes -Configuration Classes
		 * -Accesing resources in shared mode -Factories implemented as
		 * Singletons
		 */
		return Math.round(Math.random() * 1000000000L);
	}

}
