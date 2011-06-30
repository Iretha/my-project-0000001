/**
 * 
 */
package mbd.creational.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import mbd.creational.factory.data.SpecificObject;
import mbd.creational.factory.data.SpecificObjectsEnum;
import mbd.utils.MyExamplesExeption;

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

	private static MyFactory instance = null;

	private Map<String, SpecificObject> registeredClasses = null;

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
	 * Hidden constructor
	 * 
	 * @throws MyExamplesExeption
	 */
	private MyFactory() throws MyExamplesExeption {
		super();
		this.registeredClasses = new HashMap<String, SpecificObject>();
	}

	/**
	 * 
	 * @param specificObj
	 * @return SpecificObject
	 * @throws MyExamplesExeption
	 */
	public SpecificObject createInstanceUsingReflection(SpecificObjectsEnum specificObj)
			throws MyExamplesExeption {

		if (specificObj == null) {
			return null;
		}
		
		SpecificObject specificObjectIntance = null;
		try {
			Constructor objConstructor = specificObj.getClazz().getDeclaredConstructor();
			specificObjectIntance = (SpecificObject) objConstructor.newInstance();
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

	public SpecificObject createInstance(SpecificObjectsEnum specificObj) {
		if (specificObj == null) {
			return null;
		}
		return this.registeredClasses.get(specificObj.getKey()).createClassInstance();
	}

	public void registerClass(String key, SpecificObject specObj) {
		this.registeredClasses.put(key, specObj);
	}
}
