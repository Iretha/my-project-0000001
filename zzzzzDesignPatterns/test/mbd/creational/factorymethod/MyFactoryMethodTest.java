package mbd.creational.factorymethod;

import org.junit.Test;

import mbd.base.BaseTestCase;
import mbd.creational.factorymethod.data.BObjectImpl1;
import mbd.creational.factorymethod.data.BObjectImpl2;

/**
 * @author MBD
 */
public class MyFactoryMethodTest extends BaseTestCase {

	@Test
	public void testFactoryMethod() {
		AMyFactoryMethod creator1 = new BObjectImpl1Creator();
		String res1 = creator1.anOperation();
		assertNotNull(res1);
		assertEquals(res1, BObjectImpl1.class.getSimpleName());
		AMyFactoryMethod creator2 = new BObjectImpl2Creator();
		String res2 = creator2.anOperation();
		assertNotNull(res2);
		assertEquals(res2, BObjectImpl2.class.getSimpleName());
	}
}
