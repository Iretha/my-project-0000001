/**
 * 
 */
package mbd.creational.factory.data;

import mbd.creational.factory.MyFactory;
import mbd.utils.MyExamplesExeption;

/**
 * @author developer0024
 */
public class SpecificObject1 implements ISpecificObject {

	static {
		try {
			MyFactory.getInstance().registerClass(SpecificObjectsEnum.SpecObj1.getKey(),
					new SpecificObject1());
		} catch (MyExamplesExeption e) {
			// TODO
		}
	}

	public SpecificObject1() {
		super();
	}
	
	@Override
	public ISpecificObject createClassInstance(){
		return new SpecificObject1();
	}

}
