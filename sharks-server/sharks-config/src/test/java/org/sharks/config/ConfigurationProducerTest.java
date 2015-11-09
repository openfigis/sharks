/**
 * 
 */
package org.sharks.config;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
public class ConfigurationProducerTest {

	@Inject
	ConfigurationProducer p;

	@Before
	public void before() {
		System.setProperty(ConfigurationProducer.CONFIG_LOCATION_PROPERTY_NAME,
				"..\\..\\sharks-deploy\\conf\\dev\\sharks.properties");
	}

	/**
	 * 
	 * This test only works with the eclipse run config configured with this env variable:
	 * 
	 * 
	 */
	@Test
	public void buildConfiguration() {
		Configuration config = p.buildConfiguration();
		assertNotNull(config);
	}

}
