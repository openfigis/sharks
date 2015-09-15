/**
 * 
 */
package org.sharks.config;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
public class ConfigurationProducerTest {

	@Inject
	ConfigurationProducer p;

	/**
	 * 
	 * This test only works with the eclipse run config configured with this env variable:
	 * 
	 * SHARKS_CONFIG
	 * 
	 * ..\..\sharks-deploy\conf\dev\sharks.properties
	 */

	@Ignore
	@Test
	public void buildConfiguration() {

		Configuration config = p.buildConfiguration();
		assertNotNull(config);

	}

}
