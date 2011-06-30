/**
 * 
 */
package mbd.creational.factory.data;

import mbd.utils.ServicesManager;

/**
 * @author developer0024
 */
public abstract class SpecificObject {

	/**
	 * Do specific business
	 */
	public void doSpecificStuff() {
		ServicesManager.getInstance().getLog().info(getClass().getSimpleName());
	}
}
