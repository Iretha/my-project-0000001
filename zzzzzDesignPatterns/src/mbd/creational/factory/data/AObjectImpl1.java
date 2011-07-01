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
			MyFactory.getInstance().registerClass(AObjectsEnum.SpecObj1.getKey(),
					new AObjectImpl1());
		} catch (MyExamplesExeption e) {
			// TODO
		}
	}

	public AObjectImpl1() {
		super();
	}
	
	@Override
	public IAObject createClassInstance(){
		return new AObjectImpl1();
	}

}
