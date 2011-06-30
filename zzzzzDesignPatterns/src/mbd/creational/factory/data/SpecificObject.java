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
	 * Конструктор
	 */
	public SpecificObject() {
		super();
	}

	/**
	 * Do specific business
	 */
	public void doSpecificStuff() {
		ServicesManager.getInstance().getLog().info(getClass().getSimpleName());
	}

	/**
	 * Връща инстанция на обекта
	 * 
	 * @return SpecificObject
	 */
	public abstract SpecificObject createClassInstance();
}
