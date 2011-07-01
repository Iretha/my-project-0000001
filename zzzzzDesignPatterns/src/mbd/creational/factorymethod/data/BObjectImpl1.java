/**
 * TODO
 */
package mbd.creational.factorymethod.data;

/**
 * @author MBD
 * 
 */
public class BObjectImpl1 implements IBObject {

	/**
	 * @see mbd.creational.factorymethod.data.IBObject#getName()
	 */
	@Override
	public String getName() {
		return getClass().getSimpleName();
	}
}
