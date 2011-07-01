/**
 * TODO
 */
package mbd.creational.factorymethod;

import mbd.example.data.factorymethod.BObjectImpl1;
import mbd.example.data.factorymethod.IBObject;


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
