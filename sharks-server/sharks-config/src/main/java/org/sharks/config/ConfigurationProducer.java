/**
 * 
 */
package org.sharks.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Slf4j
public class ConfigurationProducer {

	public static final String CONFIG_LOCATION_PROPERTY_NAME = "SHARKS_CONFIG";

	@Produces
	@Singleton
	public Configuration buildConfiguration() {

		String configurationFileLocation = getConfigurationFileLocation();
		log.trace("configurationFileLocation: {}", configurationFileLocation);
		if (configurationFileLocation == null)
			throw new IllegalStateException("Configuration file location not found");

		ConfigurationImpl configuration = new ConfigurationImpl();
		configuration.load(configurationFileLocation);
		return configuration;
	}

	private String getConfigurationFileLocation() {
		String location = null;

		location = System.getenv(CONFIG_LOCATION_PROPERTY_NAME);

		log.trace("env property {} = {}", CONFIG_LOCATION_PROPERTY_NAME, location);

		if (location == null) {
			location = System.getProperty(CONFIG_LOCATION_PROPERTY_NAME);
			log.trace("system property {} = {}", CONFIG_LOCATION_PROPERTY_NAME, location);
		}

		if (location == null) {
			log.error("system property {} = {}", CONFIG_LOCATION_PROPERTY_NAME, location);
			throw new RuntimeException("Location CONFIG_LOCATION_PROPERTY_NAME not specified.");
		}

		return location;
	}

}
