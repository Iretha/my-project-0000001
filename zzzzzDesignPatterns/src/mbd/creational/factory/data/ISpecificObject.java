/**
 * TODO
 */
package mbd.creational.factory.data;


/**
 * @author MBD
 *
 */
public interface ISpecificObject {
	/**
	 * Връща инстанция на обекта
	 * 
	 * @return SpecificObject
	 */
	public abstract ISpecificObject createClassInstance();
}
