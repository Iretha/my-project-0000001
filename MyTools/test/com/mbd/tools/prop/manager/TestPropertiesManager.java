package com.mbd.tools.prop.manager;

import java.util.Properties;

import junit.framework.TestCase;

public class TestPropertiesManager extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testLoadConfig() {
		String propFileLocation = null;
		assertNull(PropertiesManager.loadConfig(propFileLocation));
		propFileLocation = "";
		assertNotNull(PropertiesManager.loadConfig(propFileLocation));
		propFileLocation = "fakeLocation";
		assertNull(PropertiesManager.loadConfig(propFileLocation));
	}

	public void testAll() {
		Properties envProps = PropertiesManager.getEnvironmentProperties();
		PropertiesManager.printProperties(envProps);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
