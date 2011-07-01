/**
 * TODO
 */
package mbd.creational.factorymethod;

import mbd.creational.factorymethod.data.BObjectImpl1;
import mbd.creational.factorymethod.data.IBObject;


/**
 * @author MBD
 *
 */
public class BObjectImpl1Creator extends AMyFactoryMethod {

	/**
	 * @see mbd.creational.factorymethod.AMyFactoryMethod#factoryMethod()
	 */
	@Override
	protected IBObject factoryMethod() {
		return new BObjectImpl1();
	}
}
