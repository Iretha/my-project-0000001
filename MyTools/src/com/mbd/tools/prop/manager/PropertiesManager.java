package com.mbd.tools.prop.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * Клас, който предоставя услуги за работа с конфигурационни файлове
 * 
 * @author mbd
 */
public class PropertiesManager {

	/**
	 * Зарежда конфигурационен файл
	 * 
	 * @param configFileLocation
	 * @return Properties
	 */
	public static Properties loadConfig(String configFileLocation) {
		if (configFileLocation == null) {
			return null;
		}
		InputStream is = PropertiesManager.class.getResourceAsStream(configFileLocation);
		if (is != null) {
			Properties prop = new Properties();
			try {
				prop.load(is);
				return prop;
			} catch (IOException e) {
				System.err.append("Възникна грешка при изчитане на " + configFileLocation);
			}
		} else {
			System.err.append("Не е открит файл " + configFileLocation);
		}
		return null;
	}

	/**
	 * Връща пропърти от подадения конфигурационен файл
	 * 
	 * @param propName
	 * @return String
	 */
	public static String getProperty(Properties configFile, String propKey) {
		if (configFile != null && configFile.containsKey(propKey)) {
			return configFile.getProperty(propKey);
		}
		return null;
	}

	/**
	 * Връща системните пропъртира
	 * 
	 * @return Properties
	 */
	public static Properties getSystemProperties() {
		return System.getProperties();
	}

	/**
	 * Връща environment пропертитата
	 * 
	 * @return Properties
	 */
	public static Properties getEnvironmentProperties() {
		Properties prop = new Properties();
		prop.putAll(System.getenv());
		return prop;
	}

	/**
	 * Принтира съдържание на пропърти файл
	 * 
	 * @param props
	 */
	public static void printProperties(Properties props) {
		Set<Object> keys = props.keySet();
		Iterator<Object> keysIt = keys.iterator();
		Object currKey = null;
		while (keysIt.hasNext()) {
			currKey = keysIt.next();
			System.out.println(currKey.toString() + "=" + props.getProperty(currKey.toString()));
		}
	}
}
