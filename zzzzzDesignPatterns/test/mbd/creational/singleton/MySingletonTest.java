package mbd.creational.singleton;

import mbd.base.BaseTestCase;

import org.junit.Test;

/**
 * Testing {@link MySingleton}
 * 
 * @author developer0024
 */
public class MySingletonTest extends BaseTestCase {

	private MySingleton inst1 = null;

	private MySingleton inst2 = null;

	/**
	 * Testing {@link MySingleton#doMySpecificStuff()}
	 */
	@Test
	public void testDoMySpecificStuff() {
		assertNotNull(MySingleton.getInstance());
		assertEquals(MySingleton.getInstance(), MySingleton.getInstance());
		assertNotSame(this.inst1.doMySpecificStuff(), this.inst2.doMySpecificStuff());
	}

	/**
	 * Testing {@link MySingleton#getInstance()}
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(MySingleton.getInstance());
		assertEquals(this.inst1, this.inst2);
		assertEquals(MySingleton.getInstance(), MySingleton.getInstance());
	}

	@Override
	protected void setUp() {
		super.setUp();
		this.inst1 = MySingleton.getInstance();
		this.inst2 = MySingleton.getInstance();
	}
}
