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
	 * Testing
	 * {@link MyFactory#createInstanceUsingReflection(SpecificObjectsEnum e)}
	 */
	@Test
	public void testCreateInstanceUsingReflection() {
		try {
			assertNull(MyFactory.getInstance().createInstanceUsingReflection(null));
			SpecificObject o11 = MyFactory.getInstance().createInstanceUsingReflection(
					SpecificObjectsEnum.SpecObj1);
			assertNotNull(o11);
			assertEquals(SpecificObjectsEnum.SpecObj1.getKey(), o11.getClass().getSimpleName());
			SpecificObject o12 = MyFactory.getInstance().createInstanceUsingReflection(
					SpecificObjectsEnum.SpecObj1);
			assertNotNull(o12);
			assertEquals(SpecificObjectsEnum.SpecObj1.getKey(), o12.getClass().getSimpleName());
			assertNotSame(o11, o12);

			SpecificObject o21 = MyFactory.getInstance().createInstanceUsingReflection(
					SpecificObjectsEnum.SpecObj2);
			assertNotNull(o21);
			assertEquals(SpecificObjectsEnum.SpecObj2.getKey(), o21.getClass().getSimpleName());
			SpecificObject o22 = MyFactory.getInstance().createInstanceUsingReflection(
					SpecificObjectsEnum.SpecObj2);
			assertNotNull(o22);
			assertEquals(SpecificObjectsEnum.SpecObj2.getKey(), o22.getClass().getSimpleName());
			assertNotSame(o21, o22);

		} catch (MyExamplesExeption e) {
			fail();
		}
	}
	
	/**
	 * Testing
	 * {@link MyFactory#createInstance(SpecificObjectsEnum e)}
	 */
	@Test
	public void testCreateInstance() {
		try {
			try {
				Class.forName(SpecificObjectsEnum.SpecObj1.getClazz().getName());
				Class.forName(SpecificObjectsEnum.SpecObj2.getClazz().getName());
			} catch (ClassNotFoundException e) {
				fail(e.getMessage());
			}
			assertNull(MyFactory.getInstance().createInstance(null));
			SpecificObject o11 = MyFactory.getInstance().createInstance(
					SpecificObjectsEnum.SpecObj1);
			assertNotNull(o11);
			assertEquals(SpecificObjectsEnum.SpecObj1.getKey(), o11.getClass().getSimpleName());
			SpecificObject o12 = MyFactory.getInstance().createInstance(
					SpecificObjectsEnum.SpecObj1);
			assertNotNull(o12);
			assertEquals(SpecificObjectsEnum.SpecObj1.getKey(), o12.getClass().getSimpleName());
			assertNotSame(o11, o12);

			SpecificObject o21 = MyFactory.getInstance().createInstance(
					SpecificObjectsEnum.SpecObj2);
			assertNotNull(o21);
			assertEquals(SpecificObjectsEnum.SpecObj2.getKey(), o21.getClass().getSimpleName());
			SpecificObject o22 = MyFactory.getInstance().createInstance(
					SpecificObjectsEnum.SpecObj2);
			assertNotNull(o22);
			assertEquals(SpecificObjectsEnum.SpecObj2.getKey(), o22.getClass().getSimpleName());
			assertNotSame(o21, o22);

		} catch (MyExamplesExeption e) {
			fail();
		}
	}

}
