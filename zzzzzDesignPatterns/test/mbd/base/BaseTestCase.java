/**
 * 
 */
package mbd.base;

import junit.framework.TestCase;

/**
 * Базов клас за JUnit тестове
 * 
 * @author developer0024
 */
public class BaseTestCase extends TestCase {

	@Override
	protected void setUp() {
		try {
			super.setUp();
		} catch (Exception e) {
			fail();
		}
	}

	@Override
	protected void tearDown() {
		try {
			super.tearDown();
		} catch (Exception e) {
			fail();
		}
	}
}
