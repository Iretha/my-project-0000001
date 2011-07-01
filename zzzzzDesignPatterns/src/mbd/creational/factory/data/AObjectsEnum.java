package mbd.creational.factory.data;

/**
 * Енумерация със специфични обекти за инстанциране
 * 
 * @author developer0024
 */
public enum AObjectsEnum {

	/**
	 * Обект 1
	 */
	SpecObj1(AObjectImpl1.class.getSimpleName(), AObjectImpl1.class),

	/**
	 * Обект 2
	 */
	SpecObj2(AObjectImpl2.class.getSimpleName(), AObjectImpl2.class);

	/**
	 * Клас на обекта
	 */
	private Class<?> clz = null;

	/**
	 * Ключ
	 */
	private String key = null;

	/**
	 * Конструктор
	 * 
	 * @param key
	 *            - ключ
	 * @param clz
	 *            - клас на обекта
	 */
	private AObjectsEnum(String key, Class<?> clz) {
		this.key = key;
		this.clz = clz;
	}

	/**
	 * Клас на обекта
	 * 
	 * @return Class
	 */
	public Class<?> getClazz() {
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
