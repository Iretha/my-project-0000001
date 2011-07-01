package mbd.creational.factory.data;


/**
 * @author MBD
 *
 */
public interface IAObject {
	/**
	 * Връща инстанция на обекта
	 * 
	 * @return SpecificObject
	 */
	public abstract IAObject createClassInstance();
}
