/**
 * TODO
 */
package mbd.creational.factorymethod;

import mbd.creational.factorymethod.data.BObjectImpl2;
import mbd.creational.factorymethod.data.IBObject;

/**
 * @author MBD
 * 
 */
public class BObjectImpl2Creator extends AMyFactoryMethod {

	/**
	 * @see mbd.creational.factorymethod.AMyFactoryMethod#factoryMethod()
	 */
	@Override
	protected IBObject factoryMethod() {
		return new BObjectImpl2();
	}
}
