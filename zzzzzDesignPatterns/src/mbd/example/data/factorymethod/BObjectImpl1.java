/**
 * TODO
 */
package mbd.example.data.factorymethod;

/**
 * @author MBD
 * 
 */
public class BObjectImpl1 implements IBObject {

	/**
	 * @see mbd.example.data.factorymethod.IBObject#getName()
	 */
	@Override
	public String getName() {
		return getClass().getSimpleName();
	}
}
