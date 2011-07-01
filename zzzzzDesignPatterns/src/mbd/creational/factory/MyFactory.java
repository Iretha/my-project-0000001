/**
 * 
 */
package mbd.creational.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import mbd.example.data.factory.AObjectsEnum;
import mbd.example.data.factory.IAObject;
import mbd.utils.MyExamplesExeption;

import org.apache.commons.lang.StringUtils;

/**
 * <b>Design Pattern Name: </b> Factory Pattern <br>
 * <b>Summary: </b> The client needs a product, but instead of creating it
 * directly using the new operator, it asks the factory object for a new
 * product, providing the information about the type of object it needs. The
 * factory instantiates a new concrete product and then returns to the client
 * the newly created product(casted to abstract product class). The client uses
 * the products as abstract products without being aware about their concrete
 * implementation. <br>
 * <b>Main purposes: </b> <br>
 * <li>creates objects without exposing the instantiation logic to the client.
 * <br> <li>refers to the newly created object through a common interface. <br>
 * 
 * @author developer0024
 */
public class MyFactory {

	/**
	 * Single instance
	 */
	private static MyFactory instance = null;

	/**
	 * Singleton pattern for creation.
	 * 
	 * @return MyFactory
	 * @throws MyExamplesExeption
	 */
	public static synchronized MyFactory getInstance() throws MyExamplesExeption {
		if (instance == null) {
			instance = new MyFactory();
		}
		return instance;
	}

	/**
	 * Registered classes map
	 */
	private Map<String, IAObject> registeredClasses = null;

	/**
	 * Hidden constructor
	 * 
	 * @throws MyExamplesExeption
	 */
	private MyFactory() throws MyExamplesExeption {
		super();
		this.registeredClasses = new HashMap<String, IAObject>();
	}

	/**
	 * <b>Simple Factory</b> - Creates specific object instance
	 * 
	 * @param specificObj
	 * @return IAObject
	 */
	public IAObject createInstance(AObjectsEnum specificObj) {
		if (specificObj == null) {
			return null;
		}
		return this.registeredClasses.get(specificObj.getKey()).createClassInstance();
	}

	/**
	 * <b>Reflection Factory </b> - Creates specific object instance using
	 * Reflection.
	 * 
	 * @param specificObj
	 * @return IAObject
	 * @throws MyExamplesExeption
	 */
	public IAObject createInstanceUsingReflection(AObjectsEnum specificObj)
			throws MyExamplesExeption {

		if (specificObj == null) {
			return null;
		}

		IAObject specificObjectIntance = null;
		try {
			Constructor<IAObject> objConstructor = (Constructor<IAObject>) specificObj.getClazz()
					.getDeclaredConstructor();
			specificObjectIntance = objConstructor.newInstance();
		} catch (SecurityException e) {
			throw new MyExamplesExeption(e);
		} catch (NoSuchMethodException e) {
			throw new MyExamplesExeption(e);
		} catch (IllegalArgumentException e) {
			throw new MyExamplesExeption(e);
		} catch (InstantiationException e) {
			throw new MyExamplesExeption(e);
		} catch (IllegalAccessException e) {
			throw new MyExamplesExeption(e);
		} catch (InvocationTargetException e) {
			throw new MyExamplesExeption(e);
		}
		return specificObjectIntance;
	}

	/**
	 * Register classes for instantiation
	 * 
	 * @param key
	 * @param specObj
	 * @return boolean - is successfully registered
	 */
	public boolean registerClass(String key, IAObject specObj) {
		if (StringUtils.isNotEmpty(key) && specObj != null) {
			this.registeredClasses.put(key, specObj);
			return true;
		}
		return false;
	}
}
