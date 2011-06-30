/**
 * 
 */
package mbd.creational.factory;

import org.junit.Test;

import mbd.base.BaseTestCase;
import mbd.creational.factory.data.SpecificObject;
import mbd.creational.factory.data.SpecificObjectsEnum;
import mbd.utils.MyExamplesExeption;

/**
 * @author developer0024
 * 
 */
public class MyFactoryTest extends BaseTestCase {

	/**
	 * Testing {@link MyFactory#getInstance()}
	 */
	@Test
	public void testGetInstance() {
		try {
			SpecificObject o = MyFactory.getInstance().createInstanceUsingReflection(
					SpecificObjectsEnum.SpecObj1);
		} catch (MyExamplesExeption e) {
			fail();
		}
	}
}
