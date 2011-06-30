/**
 * 
 */
package mbd.creational.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

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

	/**
	 * Singleton pattern for creation.
	 * 
	 * @return MyFactory
	 */
	public static synchronized MyFactory getInstance() {
		if (instance == null) {
			instance = new MyFactory();
		}
		return instance;
	}

	private HashMap<String, Object> registeredClasses = new HashMap<String, Object>();

	/**
	 * Hidden constructor
	 */
	private MyFactory() {
		super();
		loadAllClasses();
	}

	private void loadAllClasses() {
		SpecificObjectsEnum[] classesList = SpecificObjectsEnum.values();
		for (SpecificObjectsEnum curr : classesList) {
			try {
				registerClass(curr);
			} catch (MyExamplesExeption e) {
				// TODO
			}
		}
	}

	/**
	 * 
	 * @param specificObj
	 * @return SpecificObject
	 * @throws MyExamplesExeption
	 */
	public SpecificObject createInstanceUsingReflection(SpecificObjectsEnum specificObj)
			throws MyExamplesExeption {
		SpecificObject specificObjectIntance = null;
		try {
			Class clz = (Class) this.registeredClasses.get(specificObj.getKey());
			Constructor objConstructor = clz.getDeclaredConstructor(new Class[] { String.class });
			specificObjectIntance = (SpecificObject) objConstructor.newInstance(new Object[] {});

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
	 * Register new classes for instantiation.
	 * 
	 * @param specObj
	 * @throws MyExamplesExeption
	 */
	public void registerClass(SpecificObjectsEnum specObj) throws MyExamplesExeption {
		Class curr = specObj.getClazz();
		// try {
		// Class.forName(curr.getSimpleName());
		this.registeredClasses.put(specObj.getKey(), curr);
		// } catch (ClassNotFoundException e) {
		// throw new MyExamplesExeption(curr.getSimpleName() +
		// " is not loaded.", e);
		// }

	}

}
