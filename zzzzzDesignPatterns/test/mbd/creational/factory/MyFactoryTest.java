/**
 * 
 */
package mbd.creational.factory;

import org.junit.Test;

import mbd.base.BaseTestCase;
import mbd.creational.factory.data.IAObject;
import mbd.creational.factory.data.AObjectsEnum;
import mbd.utils.MyExamplesExeption;

/**
 * @author developer0024
 * 
 */
public class MyFactoryTest extends BaseTestCase {

	/**
	 * Testing
	 * {@link MyFactory#createInstanceUsingReflection(AObjectsEnum e)}
	 */
	@Test
	public void testCreateInstanceUsingReflection() {
		try {
			assertNull(MyFactory.getInstance().createInstanceUsingReflection(null));
			IAObject o11 = MyFactory.getInstance().createInstanceUsingReflection(
					AObjectsEnum.SpecObj1);
			assertNotNull(o11);
			assertEquals(AObjectsEnum.SpecObj1.getKey(), o11.getClass().getSimpleName());
			IAObject o12 = MyFactory.getInstance().createInstanceUsingReflection(
					AObjectsEnum.SpecObj1);
			assertNotNull(o12);
			assertEquals(AObjectsEnum.SpecObj1.getKey(), o12.getClass().getSimpleName());
			assertNotSame(o11, o12);

			IAObject o21 = MyFactory.getInstance().createInstanceUsingReflection(
					AObjectsEnum.SpecObj2);
			assertNotNull(o21);
			assertEquals(AObjectsEnum.SpecObj2.getKey(), o21.getClass().getSimpleName());
			IAObject o22 = MyFactory.getInstance().createInstanceUsingReflection(
					AObjectsEnum.SpecObj2);
			assertNotNull(o22);
			assertEquals(AObjectsEnum.SpecObj2.getKey(), o22.getClass().getSimpleName());
			assertNotSame(o21, o22);

		} catch (MyExamplesExeption e) {
			fail();
		}
	}
	
	/**
	 * Testing
	 * {@link MyFactory#createInstance(AObjectsEnum e)}
	 */
	@Test
	public void testCreateInstance() {
		try {
			try {
				Class.forName(AObjectsEnum.SpecObj1.getClazz().getName());
				Class.forName(AObjectsEnum.SpecObj2.getClazz().getName());
			} catch (ClassNotFoundException e) {
				fail(e.getMessage());
			}
			assertNull(MyFactory.getInstance().createInstance(null));
			IAObject o11 = MyFactory.getInstance().createInstance(
					AObjectsEnum.SpecObj1);
			assertNotNull(o11);
			assertEquals(AObjectsEnum.SpecObj1.getKey(), o11.getClass().getSimpleName());
			IAObject o12 = MyFactory.getInstance().createInstance(
					AObjectsEnum.SpecObj1);
			assertNotNull(o12);
			assertEquals(AObjectsEnum.SpecObj1.getKey(), o12.getClass().getSimpleName());
			assertNotSame(o11, o12);

			IAObject o21 = MyFactory.getInstance().createInstance(
					AObjectsEnum.SpecObj2);
			assertNotNull(o21);
			assertEquals(AObjectsEnum.SpecObj2.getKey(), o21.getClass().getSimpleName());
			IAObject o22 = MyFactory.getInstance().createInstance(
					AObjectsEnum.SpecObj2);
			assertNotNull(o22);
			assertEquals(AObjectsEnum.SpecObj2.getKey(), o22.getClass().getSimpleName());
			assertNotSame(o21, o22);

		} catch (MyExamplesExeption e) {
			fail();
		}
	}

}
