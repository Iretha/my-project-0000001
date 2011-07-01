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
			MyFactory.getInstance().registerClass(AObjectsEnum.SpecObj2.getKey(),
					new AObjectImpl2());
		} catch (MyExamplesExeption e) {
			// TODO
		}
	}

	public AObjectImpl2() {
		super();
	}

	@Override
	public IAObject createClassInstance() {
		return new AObjectImpl2();
	}
}
