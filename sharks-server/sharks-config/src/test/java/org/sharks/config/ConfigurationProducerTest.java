package org.sharks.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
@AdditionalClasses(ConfigurationProducer.class)
public class ConfigurationProducerTest {

	static String confFile = "src/main/resources/dev_config.properties";

	@Inject
	Configuration configuration;

	static { // System.setenv
		System.out.println(System.getenv(ConfigurationProducer.CONFIG_LOCATION_PROPERTY_NAME));

		System.getProperties().setProperty(ConfigurationProducer.CONFIG_LOCATION_PROPERTY_NAME, confFile);
		// assertEquals(confFile, System.getenv(ConfigurationProducer.CONFIG_LOCATION_PROPERTY_NAME));
		System.out.println(System.getProperty(ConfigurationProducer.CONFIG_LOCATION_PROPERTY_NAME));
		System.out.println(System.getenv(ConfigurationProducer.CONFIG_LOCATION_PROPERTY_NAME));
	}

	@Test
	public void testBuildConfiguration() {

		// System.getProperties().setProperty(ConfigurationProducer.CONFIG_LOCATION_PROPERTY_NAME, confFile);
		assertEquals(confFile, System.getenv(ConfigurationProducer.CONFIG_LOCATION_PROPERTY_NAME));

		assertTrue(configuration instanceof ConfigurationImpl);
	}

}
