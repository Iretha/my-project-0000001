/**
 * 
 */
package mbd.creational.factory.data;

/**
 * Енумерация със специфични обекти
 * 
 * @author developer0024
 */
public enum SpecificObjectsEnum {

	/**
	 * Специфичен обект 1
	 */
	SpecObj1("SpecificObject1", SpecificObject1.class),

	/**
	 * Специфичен обект 2
	 */
	SpecObj2("SpecificObject2", SpecificObject2.class);

	Class clz = null;

	String key = null;

	/**
	 * КОнструктор
	 * 
	 * @param key
	 * @param clz
	 */
	private SpecificObjectsEnum(String key, Class clz) {
		this.key = key;
		this.clz = clz;
	}

	/**
	 * Клас на обекта
	 * 
	 * @return Class
	 */
	public Class getClazz() {
		return this.clz;
	}

	/**
	 * Ключ
	 * 
	 * @return String
	 */
	public String getKey() {
		return this.key;
	}

}
