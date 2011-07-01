package mbd.example.data.factory;

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
