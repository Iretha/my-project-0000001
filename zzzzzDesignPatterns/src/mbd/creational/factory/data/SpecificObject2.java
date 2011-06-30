/**
 * 
 */
package mbd.creational.factory.data;

import mbd.creational.factory.MyFactory;
import mbd.utils.MyExamplesExeption;

/**
 * @author developer0024
 */
class SpecificObject2 extends SpecificObject {

	static {
		try {
			MyFactory.getInstance().registerClass(SpecificObjectsEnum.SpecObj2.getKey(),
					new SpecificObject2());
		} catch (MyExamplesExeption e) {
			// TODO
		}
	}

	public SpecificObject2() {
		super();
	}

	@Override
	public SpecificObject createClassInstance() {
		return new SpecificObject2();
	}
}
