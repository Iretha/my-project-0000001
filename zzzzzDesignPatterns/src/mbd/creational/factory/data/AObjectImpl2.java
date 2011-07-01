/**
 * 
 */
package mbd.creational.factory.data;

import mbd.creational.factory.MyFactory;
import mbd.utils.MyExamplesExeption;

/**
 * @author developer0024
 */
public class AObjectImpl2 implements IAObject {

	static {
		try {
			if (!MyFactory.getInstance().registerClass(AObjectsEnum.SpecObj2.getKey(),
					new AObjectImpl2())) {
				throw new MyExamplesExeption("Class " + AObjectsEnum.SpecObj2.getKey()
						+ " is not registered.");
			}
		} catch (MyExamplesExeption e) {
			// its already logged.
		}
	}

	/**
	 * Constructor
	 */
	public AObjectImpl2() {
		super();
	}

	/**
	 * Creates new instance.
	 * 
	 * @see mbd.creational.factory.data.IAObject#createClassInstance()
	 */
	@Override
	public IAObject createClassInstance() {
		return new AObjectImpl2();
	}
}
