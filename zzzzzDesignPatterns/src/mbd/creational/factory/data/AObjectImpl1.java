/**
 * 
 */
package mbd.creational.factory.data;

import mbd.creational.factory.MyFactory;
import mbd.utils.MyExamplesExeption;

/**
 * @author developer0024
 */
public class AObjectImpl1 implements IAObject {

	static {
		try {
			if (!MyFactory.getInstance().registerClass(AObjectsEnum.SpecObj1.getKey(),
					new AObjectImpl1())) {
				throw new MyExamplesExeption("Class " + AObjectsEnum.SpecObj1.getKey()
						+ " is not registered.");
			}
		} catch (MyExamplesExeption e) {
			// its already logged.
		}
	}

	/**
	 * Constructor
	 */
	public AObjectImpl1() {
		super();
	}

	/**
	 * Creates new instance.
	 * 
	 * @see mbd.creational.factory.data.IAObject#createClassInstance()
	 */
	@Override
	public IAObject createClassInstance() {
		return new AObjectImpl1();
	}

}
